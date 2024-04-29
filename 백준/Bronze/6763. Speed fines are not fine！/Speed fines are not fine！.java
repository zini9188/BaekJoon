import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int limit = Integer.parseInt(br.readLine());
        int speed = Integer.parseInt(br.readLine());
        if (speed <= limit) {
            sb.append("Congratulations, you are within the speed limit!");
        } else if (speed - limit <= 20) {
            sb.append("You are speeding and your fine is $100.");
        } else if (speed - limit <= 30) {
            sb.append("You are speeding and your fine is $270.");
        } else {
            sb.append("You are speeding and your fine is $500.");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}