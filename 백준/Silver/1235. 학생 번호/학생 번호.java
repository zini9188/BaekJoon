import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int len = arr[0].length();
        for (int i = len; i >= 0; i--) {
            Set<String> set = new LinkedHashSet<>();
            for (int j = 0; j < N; j++) {
                if (set.contains(arr[j].substring(i))) {
                    break;
                }
                set.add(arr[j].substring(i));
            }

            if (set.size() == N) {
                System.out.println(len - i);
                break;
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}