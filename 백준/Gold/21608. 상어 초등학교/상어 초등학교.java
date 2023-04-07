import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Seat implements Comparable<Seat> {
    int x;
    int y;
    int nearStudentCount;
    int nearEmptyCount;

    public Seat(int x, int y, int nearStudentCount, int nearEmptyCount) {
        this.x = x;
        this.y = y;
        this.nearStudentCount = nearStudentCount;
        this.nearEmptyCount = nearEmptyCount;
    }

    @Override
    public int compareTo(Seat nearSeat) {
        if (nearStudentCount != nearSeat.nearStudentCount) {
            return nearSeat.nearStudentCount - nearStudentCount;
        }

        if (nearEmptyCount != nearSeat.nearEmptyCount) {
            return nearSeat.nearEmptyCount - nearEmptyCount;
        }

        if (x != nearSeat.x) {
            return x - nearSeat.x;
        }

        return y - nearSeat.y;
    }
}

public class Main {
    static int[] dx = {1, 0, 0, -1}, dy = {0, 1, -1, 0};
    static int N, result = 0;
    static int[][] map;
    static int[] sequence;
    static Map<Integer, ArrayList<Integer>> students;

    public static void main(String[] args) throws IOException {
        input();

        solution();

        output();
    }
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new HashMap<>();
        sequence = new int[N * N];
        map = new int[N + 1][N + 1];
        for (int i = 0; i < N * N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(tokenizer.nextToken());
            sequence[i] = student;
            students.put(student, new ArrayList<>());
            while (tokenizer.hasMoreTokens())
                students.get(student).add(Integer.valueOf(tokenizer.nextToken()));
        }
        br.close();
    }

    private static void solution() {
        for (Integer student : sequence) {
            Seat before = findPlace(student);
            map[before.x][before.y] = student;
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (isInRange(nx, ny) && students.get(map[x][y]).contains(map[nx][ny])) {
                        count++;
                    }
                }
                if (count > 0) {
                    result += Math.pow(10, count - 1);
                }
            }
        }
    }

    private static Seat findPlace(Integer student) {
        Seat before = null;
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                if (map[x][y] > 0) {
                    continue;
                }
                Seat cur = new Seat(x, y, 0, 0);
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (isInRange(nx, ny)) {
                        if (students.get(student).contains(map[nx][ny])) {
                            cur.nearStudentCount++;
                        } else if (map[nx][ny] == 0) {
                            cur.nearEmptyCount++;
                        }
                    }
                }
                if (before == null) {
                    before = cur;
                } else if (before.compareTo(cur) > 0) {
                    before = cur;
                }
            }
        }
        return before;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= N;
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}