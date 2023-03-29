import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int switchCount = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        switches = new int[switchCount + 1];
        for (int i = 1; i <= switchCount; i++) {
            switches[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(tokenizer.nextToken());
            int target = Integer.parseInt(tokenizer.nextToken());
            if (gender == 1) {
                toggleByMale(target, switches);
            } else {
                toggleByFemale(target, switches);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= switchCount; i++) {
            result.append(switches[i]).append(" ");
            if (i % 20 == 0) {
                result.append("\n");
            }
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void toggleByFemale(int target, int[] switches) {
        int left = target - 1, right = target + 1;
        toggleSwitch(switches, target);
        while (isInRange(switches.length, left, right) && switches[left] == switches[right]) {
            toggleSwitch(switches, left--);
            toggleSwitch(switches, right++);
        }
    }

    private static void toggleByMale(int target, int[] switches) {
        for (int i = target; i < switches.length; i += target) {
            toggleSwitch(switches, i);
        }
    }

    private static void toggleSwitch(int[] switches, int index) {
        switches[index] ^= 1;
    }

    private static boolean isInRange(int range, int left, int right) {
        return left >= 1 && right < range;
    }
}