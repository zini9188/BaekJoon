import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] A = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] B = new int[M];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int left = 0, right = 0;

        ArrayList<Integer> list = new ArrayList<>();
        while (left < N && right < M) {
            if (A[left] <= B[right]) {
                list.add(A[left++]);
            } else {
                list.add(B[right++]);
            }
        }

        while (left < N){
            list.add(A[left++]);
        }

        while (right < M){
            list.add(B[right++]);
        }

        for (int i = 0; i < N + M; i++) {
            sb.append(list.get(i)).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
