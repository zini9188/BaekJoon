import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> building = new Stack<>();
        
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());

            while (!building.isEmpty() && building.peek() <= height) {
                building.pop();
            }

            ans += building.size();
            building.push(height);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}