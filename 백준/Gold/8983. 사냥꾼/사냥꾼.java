import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M, N, L;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] point = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(point);

        List<Point> animals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b > L) {
                continue;
            }
            animals.add(new Point(a, b));
        }

        int cnt = 0;
        for (Point animal : animals) {
            int left = 0, right = M - 1;

            while (left <= right) {
                int mid = (left + right) >> 1;

                if (point[mid] < animal.x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

                if (Math.abs(point[mid] - animal.x) + animal.y <= L) {
                    cnt++;
                    break;
                }
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}