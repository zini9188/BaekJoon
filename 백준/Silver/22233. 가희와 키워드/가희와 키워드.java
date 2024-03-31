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

        Set<String> notepad = new HashSet<>();
        for (int i = 0; i < N; i++) {
            notepad.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");

            while (st.hasMoreTokens()) {
                String next = st.nextToken();
                notepad.remove(next);
            }

            sb.append(notepad.size()).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}