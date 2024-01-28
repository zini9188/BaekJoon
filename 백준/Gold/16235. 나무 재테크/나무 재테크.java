import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Queue<Tree> deadTrees, growingTrees;
    static Queue<Tree>[][] trees;
    static int N, M, K, cnt;
    static int[][] ground, A;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}, dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        initialize();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new PriorityQueue<>();
            }
            Arrays.fill(ground[i], 5);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(new Tree(x, y, z));
        }

        for (int i = 0; i < K; i++) {
            for (Season season : Season.values()) {
                season.doSomething();
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void initialize() {
        ground = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        trees = new Queue[N + 1][N + 1];
        deadTrees = new PriorityQueue<>();
        growingTrees = new PriorityQueue<>();
        cnt = M;
    }

    static class Tree implements Comparable<Tree> {

        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        public boolean canGrow() {
            if (ground[x][y] >= age) {
                ground[x][y] -= age++;
                return true;
            }
            return false;
        }

        public int beNourished() {
            return age / 2;
        }

        public int spread() {
            int count = 0;
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (outRange(nx, ny)) {
                    continue;
                }

                count++;
                trees[nx][ny].add(new Tree(nx, ny, 1));
            }
            return count;
        }

        private boolean outRange(int x, int y) {
            return x < 1 || y < 1 || x > N || y > N;
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }

    public enum Season {
        SPRING {
            @Override
            void doSomething() {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        int size = trees[i][j].size();
                        Queue<Tree> temp = new ArrayDeque<>();
                        for (int loop = 0; loop < size; loop++) {
                            Tree tree = trees[i][j].poll();

                            if (tree.canGrow()) {
                                temp.add(tree);
                                if (tree.age != 0 && tree.age % 5 == 0) {
                                    growingTrees.add(new Tree(tree.x, tree.y, tree.age));
                                }
                            } else {
                                int death = tree.beNourished();
                                deadTrees.add(new Tree(i, j, death));
                            }
                        }

                        trees[i][j].addAll(temp);
                    }
                }
            }
        }, SUMMER {
            @Override
            void doSomething() {
                while (!deadTrees.isEmpty()) {
                    Tree tree = deadTrees.poll();
                    ground[tree.x][tree.y] += tree.age;
                    cnt--;
                }
            }
        }, FALL {
            @Override
            void doSomething() {
                while (!growingTrees.isEmpty()) {
                    Tree tree = growingTrees.poll();
                    cnt += tree.spread();
                }
            }
        }, WINTER {
            @Override
            void doSomething() {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        ground[i][j] += A[i][j];
                    }
                }
            }
        };

        abstract void doSomething();
    }
}