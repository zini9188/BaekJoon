import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>() {{
            put("NLCS", "North London Collegiate School");
            put("BHA", "Branksome Hall Asia");
            put("KIS", "Korea International School");
            put("SJA", "St. Johnsbury Academy");
        }};
        sb.append(map.get(br.readLine()));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}