import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Gear[] gears = new Gear[4];
        for (int i = 0; i < 4; i++) {
            int[] arr = new int[8];
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                arr[j] = input[j] - '0';
            }
            gears[i] = new Gear(arr);
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(tokenizer.nextToken()) - 1;
            int dir = Integer.parseInt(tokenizer.nextToken());
            
            boolean g0g1 = gears[0].direction[2] != gears[1].direction[6];
            boolean g1g2 = gears[1].direction[2] != gears[2].direction[6];
            boolean g2g3 = gears[2].direction[2] != gears[3].direction[6];            
            if (num == 0) {
                gears[0].rotate(dir);
                if (g0g1) {
                    gears[1].rotate(-dir);
                    if (g1g2) {
                        gears[2].rotate(dir);
                        if (g2g3) {
                            gears[3].rotate(-dir);
                        }
                    }
                }
            } else if (num == 1) {
                gears[1].rotate(dir);
                if (g0g1) {
                    gears[0].rotate(-dir);
                }
                if (g1g2) {
                    gears[2].rotate(-dir);
                    if (g2g3) {
                        gears[3].rotate(dir);
                    }
                }
            } else if (num == 2) {
                gears[2].rotate(dir);
                if (g2g3) {
                    gears[3].rotate(-dir);
                }
                if (g1g2) {
                    gears[1].rotate(-dir);
                    if (g0g1) {
                        gears[0].rotate(dir);
                    }
                }
            } else {
                gears[3].rotate(dir);
                if (g2g3) {
                    gears[2].rotate(-dir);
                    if (g1g2) {
                        gears[1].rotate(dir);
                        if (g0g1) {
                            gears[0].rotate(-dir);
                        }
                    }
                }
            }
        }

        int sum = (gears[0].direction[0] == 0 ? 0 : 1) +
                (gears[1].direction[0] == 0 ? 0 : 2) +
                (gears[2].direction[0] == 0 ? 0 : 4) +
                (gears[3].direction[0] == 0 ? 0 : 8);
        sb.append(sum);
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Gear {
        int[] direction;

        public Gear(int[] direction) {
            this.direction = direction;
        }

        public void rotate(int dir) {
            if (dir == 1) {
                int temp = direction[7];
                for (int i = 7; i >= 1; i--) {
                    direction[i] = direction[i - 1];
                }
                direction[0] = temp;
            } else {
                int temp = direction[0];
                for (int i = 0; i <= 6; i++) {
                    direction[i] = direction[i + 1];
                }
                direction[7] = temp;
            }
        }
    }
}