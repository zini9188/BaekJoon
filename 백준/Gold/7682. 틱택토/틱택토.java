import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final String VALID = "valid\n";
    static final String INVALID = "invalid\n";

    public static void main(String[] args) throws IOException {
        String input;
        while (!(input = br.readLine()).equals("end")) {
            char[][] map = new char[3][3];
            int idx = 0;
            int x = 0;
            int o = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = input.charAt(idx++);
                    if (map[i][j] == 'X') {
                        x++;
                    } else if (map[i][j] == 'O') {
                        o++;
                    }
                }
            }

            int oCnt = 0;
            int xCnt = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                    if (map[i][0] == 'O') {
                        oCnt++;
                    } else if (map[i][0] == 'X') {
                        xCnt++;
                    }
                }

                if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                    if (map[0][i] == 'O') {
                        oCnt++;
                    } else if (map[0][i] == 'X') {
                        xCnt++;
                    }
                }
            }

            if (map[0][0] == map[1][1] && map[1][1] == map[2][2]
                    || map[2][0] == map[1][1] && map[1][1] == map[0][2]) {
                if (map[1][1] == 'O') {
                    oCnt++;
                } else if (map[1][1] == 'X') {
                    xCnt++;
                }
            }

            if (o + 1 == x) {
                if (x + o == 9 && oCnt == 0) {
                    sb.append(VALID);
                    continue;
                }

                if (oCnt == 0 && xCnt != 0) {
                    sb.append(VALID);
                    continue;
                }
            } else if (x == o) {
                if (xCnt == 0 && oCnt != 0) {
                    sb.append(VALID);
                    continue;
                }
            }
            sb.append(INVALID);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}