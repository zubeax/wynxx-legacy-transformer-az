// src/PaySort.java
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class PaySort {
    private static final int LRECL = 200;

    public static void main(String[] args) throws Exception {
        Map<String, String> p = parseArgs(args);
        String in = p.getOrDefault("in", "out/unsorted.txt");
        String out = p.getOrDefault("out", "out/sorted.txt");

        List<String> lines = Files.readAllLines(Paths.get(in), StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            if (s.length() != LRECL) {
                throw new IllegalArgumentException("Input line " + (i+1) + " is not 200 chars (" + s.length() + ").");
            }
        }

        lines.sort(Comparator
            .comparing((String s) -> s.substring(0, 8))    // date [1-8]
            .thenComparing(s -> s.substring(9, 43)));       // creditor account [10-43]

        Files.createDirectories(Paths.get("out"));
        Files.write(Paths.get(out), lines, StandardCharsets.UTF_8);
        System.out.println("Sorted " + lines.size() + " records to " + out + ".");
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> m = new LinkedHashMap<>();
        for (String a : args) {
            if (a.startsWith("--")) {
                int eq = a.indexOf('=');
                if (eq > 2) m.put(a.substring(2, eq).toLowerCase(Locale.ROOT), a.substring(eq + 1));
            }
        }
        return m;
    }
}
