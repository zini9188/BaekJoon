import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static double rh, rv, sh, sv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        rh = Integer.parseInt(tokenizer.nextToken());
        rv = Integer.parseInt(tokenizer.nextToken());
        sh = Integer.parseInt(tokenizer.nextToken());
        sv = Integer.parseInt(tokenizer.nextToken());

        int n = Integer.parseInt(br.readLine());
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            result = Math.min(
                    makeMonitor(Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken())),
                    result);
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int makeMonitor(int rhi, int rvi, int shi, int svi, int price) {
        int h = (int) Math.max(Math.ceil(rh / rhi), Math.ceil(sh / shi));
        int v = (int) Math.max(Math.ceil(rv / rvi), Math.ceil(sv / svi));
        int h1 = (int) Math.max(Math.ceil(rh / rvi), Math.ceil(sh / svi));
        int v1 = (int) Math.max(Math.ceil(rv / rhi), Math.ceil(sv / shi));        
        return Math.min(price * h * v, price * h1 * v1);
    }
}