import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        boolean[] row = new boolean[N];
        boolean[] col = new boolean[M];

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (chars[j] == 'X') {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        
        bw.write(Math.max(getCnt2(row), getCnt2(col)) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCnt2(boolean[] array) {
        int count = 0;
        for (boolean flag : array) {
            if(!flag){
                count++;
            }
        }
        return count;
    }
}