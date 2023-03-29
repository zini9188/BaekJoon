import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] switches = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(tokenizer.nextToken());
        }
        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(tokenizer.nextToken());
            int switchCount = Integer.parseInt(tokenizer.nextToken());
            if (gender == 1) {
                maleDo(switchCount, switches);
            } else {
                femaleDo(switchCount, switches);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            builder.append(switches[i]).append(" ");
            if (i % 20 == 0) {
                builder.append("\n");
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void femaleDo(int count, int[] switches) {
        int left = count - 1, right = count + 1;
        changeSwitchState(switches, count);
        while (left > 0 && right < switches.length && switches[left] == switches[right]) {
            changeSwitchState(switches, left--);
            changeSwitchState(switches, right++);
        }
    }

    private static void maleDo(int count, int[] switches) {
        for (int i = count; i < switches.length; i += count) {
            changeSwitchState(switches, i);
        }
    }

    private static void changeSwitchState(int[] switches, int index) {
        switches[index] = switches[index] == 1 ? 0 : 1;
    }
}