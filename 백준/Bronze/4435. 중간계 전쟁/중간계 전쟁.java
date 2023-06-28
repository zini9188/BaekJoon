import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;

        int[] gandlf = {1, 2, 3, 3, 4, 10};
        int[] sauron = {1, 2, 2, 2, 3, 5, 10};
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            builder.append("Battle ").append(i).append(": ");
            tokenizer = new StringTokenizer(br.readLine());
            int gandlfPower = 0;
            int sauronPower = 0;
            for (int species = 0; species < 6; species++) {
                gandlfPower += gandlf[species] * Integer.parseInt(tokenizer.nextToken());
            }

            tokenizer = new StringTokenizer(br.readLine());
            for (int species = 0; species < 7; species++) {
                sauronPower += sauron[species] * Integer.parseInt(tokenizer.nextToken());
            }

            String output = gandlfPower > sauronPower ? "Good triumphs over Evil" :
                    gandlfPower == sauronPower ? "No victor on this battle field" : "Evil eradicates all trace of Good";
            builder.append(output).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}