import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] cnt = new int[1000001];
        int[] arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            cnt[arr[i]]++;
        }

        Stack<Pair> stack = new Stack<>();
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().cnt < cnt[arr[i]]) {
                ans[stack.pop().idx] = i;
            }
            stack.add(new Pair(i, cnt[arr[i]]));
        }

        for (int i = 0; i < ans.length; i++) {
            sb.append(ans[i] == 0 ? -1 : arr[ans[i]]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Pair {
        int idx, cnt;

        public Pair(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}