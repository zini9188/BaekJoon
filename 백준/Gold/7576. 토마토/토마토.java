import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] farm;
    static Queue<int[]> startPosition;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;
    static int result = 0;
    static int zeroCount = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        startPosition = new LinkedList<>();
        farm = new int[M][N];
        visit = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                farm[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (farm[i][j] == 1) {
                    startPosition.add(new int[]{i, j, 0});
                    visit[i][j] = true;
                }else if(farm[i][j] == 0){
                    zeroCount ++;
                }
            }
        }
        
        solution();
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        while (!startPosition.isEmpty()) {
            int[] position = startPosition.poll();
            int x = position[0];
            int y = position[1];
            int day = position[2];
            result = Math.max(day, result);
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isPointInFarm(nx, ny) && !visit[nx][ny] && farm[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    startPosition.add(new int[]{nx, ny, day + 1});
                    zeroCount--;
                }
            }
        }
        if(zeroCount > 0){
            result = -1;
        }
    }

    private static boolean isPointInFarm(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < M && ny < N;
    }
}
