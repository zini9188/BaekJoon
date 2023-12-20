import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static Set<String> set;
    static int N, M;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = new LinkedHashSet<>();
        arr = new int[N];
        temp = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        find(0, 0);
        for (String s : set) {
            sb.append(s).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void find(int depth, int index) {
        if (depth == M) {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < M; i++) {
                b.append(temp[i]).append(" ");
            }
            set.add(b.toString());
            return;
        }

        for (int i = index; i < N; i++) {
            temp[depth] = arr[i];
            find(depth + 1, i);
            temp[depth] = 0;
        }
    }
}