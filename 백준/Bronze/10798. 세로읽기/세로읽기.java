import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        char[][] board = new char[5][15];
        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[j].length > i)
                    sb.append(board[j][i]);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
