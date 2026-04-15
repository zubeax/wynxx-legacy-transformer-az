// src/PayGen.java
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class PayGen {
    private static final int LRECL = 200;
    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final Pattern DIGITS8 = Pattern.compile("^\\d{8}$");

    public static void main(String[] args) throws Exception {
        Map<String, String> p = parseArgs(args);

        String min = required(p, "min");
        String max = required(p, "max");
        String countStr = required(p, "count");
        String accountsPath = p.getOrDefault("accounts", "data/INACCTS.txt");
        String purposesPath = p.getOrDefault("purposes", "data/INPURP.txt");
        String outPath = p.getOrDefault("out", "out/unsorted.txt");
        String seedStr = p.get("seed"); // optional

        if (!DIGITS8.matcher(min).matches() || !DIGITS8.matcher(max).matches()) {
            System.err.println("ERROR: MIN and MAX must be in YYYYMMDD format.");
            usageAndExit();
        }

        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException nfe) {
            System.err.println("ERROR: COUNT must be a positive integer.");
            usageAndExit();
            return;
        }
        if (count < 1) {
            System.err.println("ERROR: COUNT must be >= 1.");
            System.exit(12);
        }

        LocalDate minDate, maxDate;
        try {
            minDate = LocalDate.parse(min, YYYYMMDD);
            maxDate = LocalDate.parse(max, YYYYMMDD);
        } catch (Exception e) {
            System.err.println("ERROR: Invalid MIN or MAX date.");
            System.exit(12);
            return;
        }
        if (minDate.isAfter(maxDate)) {
            LocalDate tmp = minDate; minDate = maxDate; maxDate = tmp;
        }

        List<Account> accounts = loadAccounts(accountsPath);
        if (accounts.size() < 2) {
            System.err.println("ERROR: Need at least 2 accounts in INACCTS to form debtor/creditor pairs.");
            System.exit(12);
        }
        List<String> purposes = loadPurposes(purposesPath);
        if (purposes.isEmpty()) {
            System.err.println("ERROR: Need at least 1 purpose in INPURP.");
            System.exit(12);
        }

        Files.createDirectories(Paths.get("out"));
        try (BufferedWriter w = Files.newBufferedWriter(Paths.get(outPath), StandardCharsets.UTF_8)) {
            Random r = (seedStr != null) ? new Random(Long.parseLong(seedStr)) : new Random();

            long minEpoch = minDate.toEpochDay();
            long maxEpoch = maxDate.toEpochDay();
            long range = maxEpoch - minEpoch + 1;

            int outN = 0;
            for (int t = 0; t < count; t++) {
                int ci = r.nextInt(accounts.size());
                int di;
                do { di = r.nextInt(accounts.size()); } while (di == ci);

                int pi = r.nextInt(purposes.size());
                long dayOffset = Math.floorMod(r.nextLong(), range); // uniform in [0, range)
                LocalDate d = LocalDate.ofEpochDay(minEpoch + dayOffset);
                String exDate = d.format(YYYYMMDD);

                int euros = r.nextInt(1000) + 1; // [1..1000]
                int cents = r.nextInt(100);      // [0..99]
                long amtCents = (euros * 100L) + cents;

                Account cred = accounts.get(ci);
                Account debt = accounts.get(di);
                String purpose = purposes.get(pi);

                String line = buildLine(exDate, cred.iban, cred.name, debt.iban, debt.name, amtCents, purpose);
                if (line.length() != LRECL) {
                    throw new IllegalStateException("Generated line not 200 chars (got " + line.length() + ")");
                }

                w.write(line);
                w.newLine();
                outN++;
            }
            System.out.println("Generated " + outN + " transactions to " + outPath + ".");
        }
    }

    private static String buildLine(String date, String credAcct, String credName,
                                    String debtAcct, String debtName, long amountCents, String purpose) {
        StringBuilder sb = new StringBuilder(220);

        sb.append(fixed(date, 8));                          // 1-8
        sb.append(' ');                                     // 9
        sb.append(fixedLeft(credAcct, 34));                 // 10-43
        sb.append(fixedLeft(credName, 30));                 // 44-73
        sb.append(fixedLeft(debtAcct, 34));                 // 74-107
        sb.append(fixedLeft(debtName, 30));                 // 108-137
        sb.append(padLeft(Long.toString(amountCents), 10, '0')); // 138-147
        sb.append("EUR");                                   // 148-150
        sb.append(fixedLeft(purpose, 50));                  // 151-200

        return ensureLength(sb.toString(), 200);
    }

    private static String fixedLeft(String s, int width) {
        if (s == null) s = "";
        s = s.length() > width ? s.substring(0, width) : s;
        int pad = width - s.length();
        if (pad > 0) return s + repeat(' ', pad);
        return s;
    }
    private static String fixed(String s, int width) {
        if (s == null) s = "";
        if (s.length() != width) {
            s = (s.length() > width) ? s.substring(0, width) : s + repeat(' ', (width - s.length()));
        }
        return s;
    }
    private static String padLeft(String s, int width, char fill) {
        if (s == null) s = "";
        if (s.length() > width) return s.substring(s.length() - width);
        int pad = width - s.length();
        return repeat(fill, pad) + s;
    }
    private static String ensureLength(String s, int width) {
        if (s.length() < width) return s + repeat(' ', (width - s.length()));
        if (s.length() > width) return s.substring(0, width);
        return s;
    }
    private static String repeat(char c, int n) {
        if (n <= 0) return "";
        char[] a = new char[n];
        Arrays.fill(a, c);
        return new String(a);
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> m = new LinkedHashMap<>();
        for (String a : args) {
            if (a.startsWith("--")) {
                int eq = a.indexOf('=');
                if (eq > 2) {
                    m.put(a.substring(2, eq).toLowerCase(Locale.ROOT), a.substring(eq + 1));
                } else {
                    m.put(a.substring(2).toLowerCase(Locale.ROOT), "true");
                }
            }
        }
        return m;
    }
    private static String required(Map<String, String> p, String key) {
        String v = p.get(key);
        if (v == null) {
            System.err.println("Missing required --" + key + " parameter.");
            usageAndExit();
        }
        return v;
    }
    private static void usageAndExit() {
        System.err.println("Usage: java PayGen --min=YYYYMMDD --max=YYYYMMDD --count=N [--accounts=path] [--purposes=path] [--out=path] [--seed=long]");
        System.err.println("Example: java PayGen --min=20260101 --max=20261231 --count=1000 --accounts=data/INACCTS.txt --purposes=data/INPURP.txt --out=out/unsorted.txt");
        System.exit(12);
    }

    private static List<Account> loadAccounts(String path) throws IOException {
        List<Account> list = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;
            String iban = null, name = null;

            int idx = line.indexOf(',');
            if (idx >= 0) {
                iban = line.substring(0, idx).trim();
                name = line.substring(idx + 1).trim();
            } else {
                int semi = line.indexOf(';');
                if (semi >= 0) {
                    iban = line.substring(0, semi).trim();
                    name = line.substring(semi + 1).trim();
                } else {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length == 2) {
                        iban = parts[0].trim();
                        name = parts[1].trim();
                    } else {
                        iban = line.trim();
                        name = "";
                    }
                }
            }
            if (!iban.isEmpty() && !name.isEmpty()) {
                list.add(new Account(iban, name));
            }
        }
        return list;
    }

    private static List<String> loadPurposes(String path) throws IOException {
        List<String> list = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        for (String raw : lines) {
            String line = raw.trim();
            if (!line.isEmpty()) list.add(line);
        }
        return list;
    }

    private static class Account {
        final String iban;
        final String name;
        Account(String i, String n) { this.iban = i; this.name = n; }
    }
}
