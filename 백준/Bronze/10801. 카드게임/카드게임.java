import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] b = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int aWin = 0;
        int bWin = 0;
        for (int i = 0; i < 10; i++) {
            if (b[i] > a[i]) {
                bWin++;
            } else if (b[i] < a[i]) {
                aWin++;
            }
        }

        sb.append(aWin == bWin ? "D" : aWin > bWin ? "A" : "B");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}