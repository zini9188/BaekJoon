import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        HashSet<String> dictionary = new HashSet<>();
        for (int i = 0; i < N; i++) {
            dictionary.add(br.readLine());
        }
        
        int result = 0;
        for (int i = 0; i < M; i++) {
            if (dictionary.contains(br.readLine())) {
                result++;
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

}