import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static int N, K;
    static int[] dx = new int[]{1, 0};
    static int[] dy = new int[]{0, 1};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());
        ArrayList<FishBowl> fishBowls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            fishBowls.add(new FishBowl());
            int count = Integer.parseInt(tokenizer.nextToken());
            fishBowls.get(i).height.add(count);
        }

        int ans = 1;
        while (true) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(min, fishBowls.get(i).height.peek());
            }

            for (int i = 0; i < N; i++) {
                int count = fishBowls.get(i).height.peek();
                if (count == min) {
                    fishBowls.get(i).height.add(fishBowls.get(i).height.poll() + 1);
                }
            }

            fishBowls.get(1).height.add(fishBowls.get(0).height.poll());
            fishBowls.remove(0);
            do {
                firstOperation(fishBowls);
            } while (canFirstOperation(fishBowls));

            int[] secondFishBowls = getDifference(fishBowls);
            int[][] secondOperated = firstOfSecondOperation(secondFishBowls);
            secondOperated = secondOfSecondOperation(secondOperated);
            fishBowls = getDifferenceFishCountForMatrix(secondOperated);

            ans++;

            if (isEnd(fishBowls) <= K) {
                bw.write((ans - 1) + "");
                break;
            }
        }

        bw.close();
        br.close();
    }

    private static int isEnd(ArrayList<FishBowl> fishBowls) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (FishBowl fishBowl : fishBowls) {
            max = Math.max(fishBowl.height.peek(), max);
            min = Math.min(fishBowl.height.peek(), min);
        }

        return max - min;
    }

    private static ArrayList<FishBowl> getDifferenceFishCountForMatrix(int[][] original) {
        int row = original.length;
        int col = original[0].length;
        int[][] copy = new int[row][];
        for (int i = 0; i < row; i++) {
            copy[i] = original[i].clone();
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                for (int dir = 0; dir < 2; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if (isInRange(nx, ny, row, col)) {
                        subFish(original, copy, x, y, nx, ny);
                    }
                }
            }
        }

        ArrayList<FishBowl> fishBowls = new ArrayList<>();
        for (int y = 0; y < col; y++) {
            for (int[] rowInCopy : copy) {
                fishBowls.add(new FishBowl());
                fishBowls.get(fishBowls.size() - 1).height.add(rowInCopy[y]);
            }
        }

        return fishBowls;
    }

    private static void subFish(int[][] original, int[][] copy, int x, int y, int nx, int ny) {
        int sub = Math.abs(original[x][y] - original[nx][ny]) / 5;
        if (sub > 0) {
            if (original[x][y] > original[nx][ny]) {
                copy[x][y] -= sub;
                copy[nx][ny] += sub;
            } else {
                copy[nx][ny] -= sub;
                copy[x][y] += sub;
            }
        }
    }

    private static int[][] firstOfSecondOperation(int[] fishbowls) {
        int size = N / 2;
        int[][] copy = new int[2][size];
        for (int y = 0; y < size; y++) {
            copy[0][y] = fishbowls[y + size];
            copy[1][y] = fishbowls[size - y - 1];
        }

        return copy;
    }

    private static int[][] secondOfSecondOperation(int[][] fishbowls) {
        int size = N / 4;
        int[][] copy = new int[4][size];
        for (int y = 0; y < size; y++) {
            copy[0][y] = fishbowls[0][y + size];
            copy[1][y] = fishbowls[1][y + size];
            copy[2][y] = fishbowls[1][size - y - 1];
            copy[3][y] = fishbowls[0][size - y - 1];
        }

        return copy;
    }

    private static boolean canFirstOperation(ArrayList<FishBowl> fishBowls) {
        int higher = 0;
        int index = 0;
        for (; index < fishBowls.size(); index++) {
            int size = fishBowls.get(index).height.size();
            if (size < 2) {
                break;
            }
            higher = Math.max(size, higher);
        }

        return higher <= fishBowls.size() - index;
    }

    private static void firstOperation(ArrayList<FishBowl> fishbowls) {
        ArrayList<FishBowl> putFishbowl = new ArrayList<>();
        while (fishbowls.get(0).height.size() >= 2) {
            Queue<Integer> current = fishbowls.get(0).height;
            if (current.size() >= 2) {
                putFishbowl.add(new FishBowl());
                while (!current.isEmpty()) {
                    putFishbowl.get(putFishbowl.size() - 1).height.add(current.poll());
                }
                fishbowls.remove(0);
            }
        }

        for (int i = putFishbowl.size() - 1; i >= 0; i--) {
            Queue<Integer> current = putFishbowl.get(i).height;
            int size = current.size();
            for (int j = 0; j < size; j++) {
                fishbowls.get(j).height.add(current.poll());
            }
        }
    }

    private static int[] getDifference(ArrayList<FishBowl> fishbowls) {
        int row = fishbowls.size();
        int col = fishbowls.get(0).height.size();
        int[][] original = new int[row][col];
        int[][] copy = new int[row][col];
        for (int i = 0; i < row; i++) {
            Queue<Integer> current = fishbowls.get(i).height;
            for (int j = 0; j < col && !current.isEmpty(); j++) {
                original[i][j] = current.poll();
                copy[i][j] = original[i][j];
            }
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (original[x][y] > 0) {
                    for (int dir = 0; dir < 2; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if (isInRange(nx, ny, row, col) && original[nx][ny] > 0) {
                            subFish(original, copy, x, y, nx, ny);
                        }
                    }
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (copy[x][y] == 0) {
                    continue;
                }
                res.add(copy[x][y]);
            }
        }

        return res.stream().mapToInt(o -> o).toArray();
    }

    private static boolean isInRange(int x, int y, int row, int col) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class FishBowl {
        Queue<Integer> height = new ArrayDeque<>();
    }
}