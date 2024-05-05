import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Town> towns = new ArrayList<>();
        long total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            towns.add(new Town(x, a));
            total += a;
        }
        total = (total + 1) >> 1;
        Collections.sort(towns);

        long res = 0;
        for (int i = 0; i < N; i++) {
            res += towns.get(i).A;
            if ((res >= total)) {
                sb.append(towns.get(i).X);
                break;
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Town implements Comparable<Town> {

        int X, A;

        public Town(int x, int a) {
            X = x;
            A = a;
        }

        @Override
        public int compareTo(Town o) {
            return X - o.X;
        }
    }
}