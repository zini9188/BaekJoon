import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<String> dna = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            dna.add(br.readLine());
        }

        int sum = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int max = Integer.MIN_VALUE;
            int maxIdx = 0;
            int[] arr = new int[4];
            for (int j = 0; j < N; j++) {
                if (dna.get(j).charAt(i) == 'A') {
                    arr[0]++;
                } else if (dna.get(j).charAt(i) == 'C') {
                    arr[1]++;
                } else if (dna.get(j).charAt(i) == 'G') {
                    arr[2]++;
                } else {
                    arr[3]++;
                }
            }

            for (int j = 0; j < 4; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    maxIdx = j;
                }


            }

            if (maxIdx == 0) {
                res.append('A');
            } else if (maxIdx == 1) {
                res.append('C');
            } else if (maxIdx == 2) {
                res.append('G');
            } else {
                res.append('T');
            }

            for (int j = 0; j < 4; j++) {
                if (j != maxIdx) {
                    sum += arr[j];
                }
            }
        }

        sb.append(res).append("\n").append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}