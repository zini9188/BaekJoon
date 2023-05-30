import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] persons = new int[n];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            persons[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(persons);
        
        int answer = persons[0];
        for (int i = 1; i < n; i++) {
            persons[i] = persons[i - 1] + persons[i];
            answer += persons[i];
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}