import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * External-merge-sorting version of SortDta for very large inputs (e.g., ~100 GB).
 * Sorts by BLZ (8) then KTO (10) and preserves fixed 80-char record layout.
 */
public class SortDtaExternal {
    private static final int REC_LEN = 80;
    private static final int BLZ_LEN = 8;
    private static final int KTO_LEN = 10;
    private static final Charset CHARSET = StandardCharsets.ISO_8859_1;

    // Tunables (can be adjusted by editing the code or future CLI flags)
    private static final int TARGET_RECORDS_PER_CHUNK = 2_000_000; // tune per heap size
    private static final int IO_BUFFER_SIZE = 1 << 20;             // 1 MiB
    private static final String TEMP_PREFIX = "sortdta_run_";

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java SortDta <input-file> <output-file>");
            System.exit(1);
        }
        Path in = Paths.get(args[0]);
        Path out = Paths.get(args[1]);
        Path tempDir = Files.createTempDirectory("sortdta_tmp_");

        List<Path> runs = createSortedRuns(in, tempDir);
        mergeRuns(runs, out);

        // Cleanup temp
        for (Path p : runs) try { Files.deleteIfExists(p); } catch (Exception ignore) {}
        try { Files.deleteIfExists(tempDir); } catch (Exception ignore) {}
    }

    /** Phase 1: create sorted runs on disk. */
    private static List<Path> createSortedRuns(Path input, Path tempDir) throws IOException {
        List<Path> runs = new ArrayList<>();
        List<Record> buffer = new ArrayList<>(TARGET_RECORDS_PER_CHUNK);

        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(input, CHARSET), IO_BUFFER_SIZE)) {
            String line;
            while ((line = br.readLine()) != null) {
                buffer.add(Record.fromLine(normalizeLine(line)));
                if (buffer.size() >= TARGET_RECORDS_PER_CHUNK) {
                    runs.add(spillRun(buffer, tempDir));
                    buffer.clear();
                }
            }
        }
        if (!buffer.isEmpty()) {
            runs.add(spillRun(buffer, tempDir));
            buffer.clear();
        }
        return runs;
    }

    /** Sorts the buffer and writes it as a run file. */
    private static Path spillRun(List<Record> records, Path tempDir) throws IOException {
        records.sort(Comparator.comparing((Record r) -> r.blz).thenComparing(r -> r.kto));
        Path runFile = Files.createTempFile(tempDir, TEMP_PREFIX, ".txt");
        try (BufferedWriter bw = new BufferedWriter(
                Files.newBufferedWriter(runFile, CHARSET, StandardOpenOption.TRUNCATE_EXISTING),
                IO_BUFFER_SIZE)) {
            for (Record r : records) {
                bw.write(r.toLine());
                bw.newLine();
            }
        }
        return runFile;
    }

    /** Phase 2: k-way merge run files to output. */
    private static void mergeRuns(List<Path> runs, Path output) throws IOException {
        List<RunReader> readers = new ArrayList<>(runs.size());
        for (Path p : runs) readers.add(new RunReader(p));

        PriorityQueue<HeapNode> heap = new PriorityQueue<>(Comparator
                .comparing((HeapNode n) -> n.record.blz)
                .thenComparing(n -> n.record.kto));

        for (int i = 0; i < readers.size(); i++) {
            Record r = readers.get(i).next();
            if (r != null) heap.add(new HeapNode(i, r));
        }

        try (BufferedWriter bw = new BufferedWriter(
                Files.newBufferedWriter(output, CHARSET, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING),
                IO_BUFFER_SIZE)) {
            while (!heap.isEmpty()) {
                HeapNode n = heap.poll();
                bw.write(n.record.toLine());
                bw.newLine();
                Record next = readers.get(n.readerIndex).next();
                if (next != null) heap.add(new HeapNode(n.readerIndex, next));
            }
        } finally {
            for (RunReader rr : readers) try { rr.close(); } catch (Exception ignore) {}
        }
    }

    private static class HeapNode {
        final int readerIndex; final Record record;
        HeapNode(int idx, Record r) { this.readerIndex = idx; this.record = r; }
    }

    private static class RunReader implements Closeable {
        final BufferedReader br;
        RunReader(Path p) throws IOException {
            this.br = new BufferedReader(Files.newBufferedReader(p, CHARSET), IO_BUFFER_SIZE);
        }
        Record next() throws IOException {
            String line = br.readLine();
            return (line == null) ? null : Record.fromLine(normalizeLine(line));
        }
        public void close() throws IOException { br.close(); }
    }

    private static String normalizeLine(String line) {
        if (line.length() == REC_LEN) return line;
        if (line.length() > REC_LEN) return line.substring(0, REC_LEN);
        StringBuilder sb = new StringBuilder(REC_LEN);
        sb.append(line);
        while (sb.length() < REC_LEN) sb.append(' ');
        return sb.toString();
    }

    private static final class Record {
        final String blz; // X(08)
        final String kto; // X(10)
        final String rest; // X(62)

        Record(String blz, String kto, String rest) { this.blz = blz; this.kto = kto; this.rest = rest; }

        static Record fromLine(String line) {
            String blz = line.substring(0, BLZ_LEN);
            String kto = line.substring(BLZ_LEN, BLZ_LEN + KTO_LEN);
            String rest = line.substring(BLZ_LEN + KTO_LEN, REC_LEN);
            return new Record(blz, kto, rest);
        }

        String toLine() {
            String s = blz + kto + rest;
            if (s.length() == REC_LEN) return s;
            if (s.length() > REC_LEN) return s.substring(0, REC_LEN);
            StringBuilder sb = new StringBuilder(REC_LEN);
            sb.append(s);
            while (sb.length() < REC_LEN) sb.append(' ');
            return sb.toString();
        }
    }
}