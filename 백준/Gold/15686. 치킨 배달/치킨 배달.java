import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] city;
    static ArrayList<Point> chickens;
    static ArrayList<Point> houses;
    static ArrayList<Point> selectedChickens;
    static boolean[][] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        city = new int[N][N];
        visited = new boolean[N][N];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        selectedChickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (city[i][j] == 1) houses.add(new Point(i, j));
                if (city[i][j] == 2) chickens.add(new Point(i, j));
            }
        }
        solution();
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        findChickenStore(0, 0);
    }

    private static void findChickenStore(int index, int count) {
        if (count == M) {
            answer = Math.min(getChickenDistance(), answer);
            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            int x = chickens.get(i).x;
            int y = chickens.get(i).y;
            if (!visited[x][y]) {
                visited[x][y] = true;
                selectedChickens.add(new Point(x, y));
                findChickenStore(i + 1, count + 1);
                selectedChickens.remove(selectedChickens.size() - 1);
                visited[x][y] = false;
            }
        }
    }

    private static int getChickenDistance() {
        int result = 0;
        for (Point house : houses) {
            int sum = Integer.MAX_VALUE;
            for (Point selectedChicken : selectedChickens) {
                int dist = Math.abs(house.x - selectedChicken.x) + Math.abs(house.y - selectedChicken.y);
                sum = Math.min(dist, sum);
            }
            result += sum;
        }
        return result;
    }
}