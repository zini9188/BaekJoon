import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String document = br.readLine();
        String target = br.readLine();

        int i;
        int cnt = 0;
        while ((i = document.indexOf(target)) != -1) {
            document = document.substring(i + target.length());
            cnt++;
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}