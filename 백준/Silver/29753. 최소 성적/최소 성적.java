import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static Map<String, BigDecimal> map = new LinkedHashMap<>() {{
        put("F", new BigDecimal("0.00"));
        put("D0", new BigDecimal("1.00"));
        put("D+", new BigDecimal("1.50"));
        put("C0", new BigDecimal("2.00"));
        put("C+", new BigDecimal("2.50"));
        put("B0", new BigDecimal("3.00"));
        put("B+", new BigDecimal("3.50"));
        put("A0", new BigDecimal("4.00"));
        put("A+", new BigDecimal("4.50"));
    }};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        BigDecimal X = new BigDecimal(tokenizer.nextToken());
        BigDecimal C = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            BigDecimal ci = new BigDecimal(tokenizer.nextToken());
            String gi = tokenizer.nextToken();
            C = C.add(ci);
            total = total.add(map.get(gi).multiply(ci));
        }

        BigDecimal L = new BigDecimal(br.readLine());
        C = C.add(L);
        sb.append(find(X, total, L, C));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String find(BigDecimal target, BigDecimal total, BigDecimal l, BigDecimal c) {
        for (String grade : map.keySet()) {
            BigDecimal v = map.get(grade).multiply(l);
            v = v.add(total);
            v = v.divide(c, 2, RoundingMode.DOWN);
            if(v.compareTo(target) > 0){
                return grade;
            }
        }
        return "impossible";
    }
}