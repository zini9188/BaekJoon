import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] table;
    static ArrayList<String> rotate;
    static HashSet<String> dict;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        table = new char[R][C];
        rotate = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            table[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < C; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < R; j++) {
                builder.append(table[j][i]);
            }
            rotate.add(builder.toString());
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
        int left = 1, right = R - 1;
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
        for (int i = 0; i < C; i++) {
            String temp = rotate.get(i).substring(start);
            if (dict.contains(temp)) {
                return false;
            }
            dict.add(temp);
        }
        return true;
    }
}