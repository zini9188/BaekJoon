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

        List<Integer> orders = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            orders.add(num);
        }

        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            int num = orders.get(i);
            if (set.contains(num) || set.size() < N && set.add(num)) {
                continue;
            }

            int max = -1, idx = -1;
            for (int s : set) {
                int tmp;
                List<Integer> sub = orders.subList(i + 1, K);
                if (sub.contains(s)) {
                    tmp = sub.indexOf(s) + 1;
                } else {
                    tmp = K - i - 1;
                }

                if (tmp > max) {
                    max = tmp;
                    idx = s;
                }
            }
            set.remove(idx);
            set.add(num);
            cnt++;
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}