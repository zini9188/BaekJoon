import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    static boolean[] visited;
    static int N, M;
    static LinkedHashSet<String> hashSet = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(arr);

        perm(0, "");
        
        for (String str : new ArrayList<>(hashSet)) {
            bw.write(str + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void perm(int depth, String str) {
        if (depth == M) {
            hashSet.add(str.trim());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm(depth + 1, str + " " + arr[i]);
                visited[i] = false;
            }
        }
    }
}