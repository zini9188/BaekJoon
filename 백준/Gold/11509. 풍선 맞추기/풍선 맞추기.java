import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int result = 0;
        int[] arr = new int[1000002];        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (arr[num + 1] > 0) {
                arr[num + 1]--;
            }
            arr[num]++;
        }

        for (int a : arr) {
            result = a > 0 ? result + a : result;
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}