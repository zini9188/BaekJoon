import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int digit = 1;

        while (digit * 10 <= n) {
            digit *= 10;
        }

        int result = 0;
        int count = String.valueOf(digit).length();
        while (n > 1) {
            int target = n - digit + 1;
            result += target * count--;
            n -= target;
            digit /= 10;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}