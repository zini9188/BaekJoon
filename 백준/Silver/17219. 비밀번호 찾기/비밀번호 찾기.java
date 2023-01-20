import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N, M;
        String id, pw;
        HashMap<String, String> accounts = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        StringBuilder stringBuilder = new StringBuilder();                
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            id = tokenizer.nextToken();
            pw = tokenizer.nextToken();
            accounts.put(id, pw);
        }
        
        for (int i = 0; i < M; i++) {
            String find = br.readLine();
            stringBuilder.append(accounts.get(find)).append("\n");
        }
        
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}