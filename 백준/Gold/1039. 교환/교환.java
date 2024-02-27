import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> num = new ArrayDeque<>();
        num.add(N);

        int len = String.valueOf(N).length();
        int ans = -1;
        boolean[][] visited = new boolean[1000001][K];
        for (int i = 0; i < K; i++) {
            int size = num.size();

            for (int j = 0; j < size && !num.isEmpty(); j++) {
                int n = num.poll();

                for (int k = 0; k < len - 1; k++) {
                    for (int l = k + 1; l < len; l++) {

                        int convert = convert(n, k, l);
                        if (convert == -1 || visited[convert][i]) {
                            continue;
                        }

                        num.add(convert);
                        visited[convert][i] = true;

                        if (i == K - 1 && convert > ans) {
                            ans = convert;
                        }
                    }
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int convert(int n, int i, int j) {
        char[] chars = String.valueOf(n).toCharArray();
        if (i == 0 && chars[j] == '0') {
            return -1;
        }

        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return Integer.parseInt(new String(chars));
    }
}