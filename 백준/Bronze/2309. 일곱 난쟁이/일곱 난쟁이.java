import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] dwarf = new int[9];
        int total = 0;
        for (int i = 0; i < 9; i++) {
            dwarf[i] = read();
            total += dwarf[i];
        }
        Arrays.sort(dwarf);        
        solution(dwarf, total);        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution(int[] dwarf, int total) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    continue;
                }

                if (total - dwarf[i] - dwarf[j] == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == j || k == i) {
                            continue;
                        }

                        sb.append(dwarf[k]).append("\n");
                    }
                    return;
                }
            }
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}