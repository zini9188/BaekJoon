import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (canMove(N, L, map, i)) {
                cnt++;
            }

            if (canMove2(N, L, map, i)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean canMove(int N, int L, int[][] map, int i) {
        int dist = 1;
        for (int j = 1; j < N; ) {
            int diff = Math.abs(map[i][j] - map[i][j - 1]);
            // 높이 차이가 1 보다 큼
            if (diff > 1) {
                return false;
            }

            if (diff == 1) {
                // 현재 높이가 더 크다.
                if (map[i][j] > map[i][j - 1]) {
                    if (dist < L) {
                        return false;
                    }

                    dist = 1;
                    j++;
                } else {
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N || map[i][j] != map[i][j + k]) {
                            return false;
                        }

                    }

                    dist = 0;
                    j += L;
                }
            } else {
                dist++;
                j++;
            }

        }
        return true;
    }

    private static boolean canMove2(int N, int L, int[][] map, int i) {
        int dist = 1;
        for (int j = 1; j < N; ) {
            int diff = Math.abs(map[j][i] - map[j - 1][i]);
            // 높이 차이가 1 보다 큼
            if (diff > 1) {
                return false;
            }

            if (diff == 1) {
                // 현재 높이가 더 크다.
                if (map[j][i] > map[j - 1][i]) {
                    if (dist < L) {
                        return false;
                    }
                    dist = 1;
                    j++;
                } else {
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N || map[j][i] != map[j + k][i]) {
                            return false;
                        }

                    }
                    dist = 0;
                    j += L;
                }
            } else {
                dist++;
                j++;
            }

        }
        return true;
    }
}