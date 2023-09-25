import java.util.*;
import java.io.*;

public class Main {
    static int n, m, arr[][], tmpCnt = 0, cnt = 0, max = 0, wallCnt = 0;
    static int dir[][] = {
            { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };
    static boolean visited[][];
    static ArrayList<Node> virus = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        visited = new boolean[n][m];
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            for (int k = 0; k < m; k++) {
                arr[i][k] = Integer.parseInt(str[k]);
                if (arr[i][k] == 1)
                    wallCnt += 1;
                if (arr[i][k] == 2) {
                    virus.add(new Node(i, k));
                }
            }
        }

        for (int a = 0; a < n * m - 2; a++) {
            if (arr[a / m][a % m] != 0)
                continue;
            arr[a / m][a % m] = 1;
            for (int b = a + 1; b < n * m - 1; b++) {

                if (arr[b / m][b % m] != 0)
                    continue;
                arr[b / m][b % m] = 1;
                for (int c = b + 1; c < n * m; c++) {
                    if (arr[c / m][c % m] != 0)
                        continue;
                    arr[c / m][c % m] = 1;
                    // bfs
                    cnt = virus.size();
                    for (Node v : virus) {
                        find(v.x, v.y);
                    }
                    int tmpMax = n * m - wallCnt - cnt - 3;

                    if (max < tmpMax)
                        max = tmpMax;

                    // if (!set.contains(max)) {
                    // set.add(max);
                    // System.out.println("=========");
                    // System.out.println(max + " " + wallCnt + " " + cnt);
                    // for (int i = 0; i < n; i++) {
                    // for (int k = 0; k < m; k++) {
                    // int tmp = 0;
                    // if (arr[i][k] == 1)
                    // tmp = 1;
                    // else if (arr[i][k] == 2) {
                    // tmp = 2;
                    // } else if (visited[i][k] == true) {
                    // tmp = 2;
                    // }
                    // System.out.print(tmp + " ");
                    // }
                    // System.out.println();
                    // }
                    // }
                    for (int i = 0; i < n; i++) {
                        Arrays.fill(visited[i], false);
                    }
                    cnt = 0;
                    arr[c / m][c % m] = 0;
                }
                arr[b / m][b % m] = 0;
            }
            arr[a / m][a % m] = 0;
        }

        // Iterator<Integer> iter = set.iterator();
        // while (iter.hasNext()) {
        // System.out.print(iter.next() + " ");
        // }
        System.out.println(max);
    }

    static void find(int xx, int yy) {
        for (int i = 0; i < 4; i++) {
            int x = xx + dir[i][0];
            int y = yy + dir[i][1];

            if (x < 0 || y < 0 || x >= n || y >= m)
                continue;
            if (visited[x][y])
                continue;
            if (arr[x][y] != 0)
                continue;
            visited[x][y] = true;
            cnt++;
            find(x, y);
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}