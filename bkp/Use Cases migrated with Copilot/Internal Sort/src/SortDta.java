import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Java translation of the COBOL program SORTDTA.
 *
 * Function: Read fixed-length 80-character records with the layout
 * BLZ(8) + KTO(10) + REST(62), sort ascending by BLZ then KTO, and
 * write the records out unchanged (still 80 characters each).
 *
 * Assumptions:
 *  - Input and output are treated as text using ISO-8859-1 to keep a 1:1
 *    mapping between characters and bytes. If your data is EBCDIC, convert
 *    it before running this program.
 *  - Lines shorter than 80 are right-padded with spaces; longer lines are
 *    truncated to 80 characters.
 */
public class SortDta {
    private static final int REC_LEN = 80;
    private static final int BLZ_LEN = 8;
    private static final int KTO_LEN = 10;
    private static final Charset CHARSET = StandardCharsets.ISO_8859_1;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java SortDta <input-file> <output-file>");
            System.exit(1);
        }
        Path in = Paths.get(args[0]);
        Path out = Paths.get(args[1]);

        List<Record> records = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(in, CHARSET)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = normalizeLine(line);
                records.add(Record.fromLine(line));
            }
        }

        // Sort ascending by BLZ then KTO (alphanumeric compare)
        records.sort(Comparator
                .comparing((Record r) -> r.blz)
                .thenComparing(r -> r.kto));

        try (BufferedWriter bw = Files.newBufferedWriter(out, CHARSET, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Record r : records) {
                bw.write(r.toLine());
                bw.newLine();
            }
        }
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

        Record(String blz, String kto, String rest) {
            this.blz = blz;
            this.kto = kto;
            this.rest = rest;
        }

        static Record fromLine(String line) {
            String blz = line.substring(0, BLZ_LEN);
            String kto = line.substring(BLZ_LEN, BLZ_LEN + KTO_LEN);
            String rest = line.substring(BLZ_LEN + KTO_LEN, REC_LEN);
            return new Record(blz, kto, rest);
        }

        String toLine() {
            // Ensure exactly 80 characters
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