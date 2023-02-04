import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] table;
    static String[] rotate;
    static HashSet<String> dict;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        table = new char[M][N];
        rotate = new String[M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                table[j][i] = line.charAt(j);
            }
        }
        for (int i = 0; i < M; i++) {
            String temp = Arrays.toString(table[i])
                    .replaceAll("[^a-z]", "")
                    .replaceAll(", ", "");
            rotate[i] = temp;
        }
        solution();
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        binarySearch();
    }

    private static void binarySearch() {
        int left = 1, right = N - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (findDuple(mid)) {
                left = mid + 1;
                count = mid;
            } else {
                right = mid - 1;
            }
        }
    }

    private static boolean findDuple(int start) {
        dict = new HashSet<>();
        for (int j = 0; j < M; j++) {
            dict.add(rotate[j].substring(start));
        }
        return dict.size() == M;
    }
}