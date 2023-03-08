import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        StringBuilder builder = new StringBuilder();
        if (solution()) {
            for (int i = 0; i < N; i++) {
                builder.append(arr[i]).append(" ");
            }
        } else {
            builder.append("-1");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean solution() {
        int i = arr.length - 1;
        while (i > 0 && arr[i] >= arr[i - 1]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[j] >= arr[i - 1]) {
            j--;
        }
        swap(i - 1, j);
        j = arr.length - 1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
        return true;
    }

    private static void swap(int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}