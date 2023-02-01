import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int left = 1, right = N * 5;
        int result = 1;        
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = find(mid);            
            if (cnt < N) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = mid;
            }
        }
        result = find(result) == N ? result : -1;
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int mid) {
        int count = 0;
        while (mid >= 5) {
            count += mid / 5;
            mid /= 5;
        }
        return count;
    }
}