import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int UP = 0, FRONT = 1, DOWN = 2, BACK = 3, LEFT = 4, RIGHT = 5;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static char[][][] cube;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            tokenizer = new StringTokenizer(br.readLine());
            init();
            for (int j = 0; j < N; j++) {
                String cmd = tokenizer.nextToken();
                turn(cmd);
            }
            print();
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void init() {
        cube = new char[7][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[UP][i][j] = 'w';
                cube[FRONT][i][j] = 'r';
                cube[DOWN][i][j] = 'y';
                cube[BACK][i][j] = 'o';
                cube[LEFT][i][j] = 'g';
                cube[RIGHT][i][j] = 'b';
            }
        }
    }

    public static char[][] set(char[][] side, char clockwise) {
        char[][] temp = new char[3][3];
        if (clockwise == '+') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = side[i][j];
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = side[i][j];
                }
            }
        }
        return temp;
    }

    public static void turn(String cmd) {
        char side = cmd.charAt(0);
        char clockwise = cmd.charAt(1);
        if (side == 'U') {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[LEFT][i][2];
                    cube[LEFT][i][2] = cube[FRONT][0][i];
                    cube[FRONT][0][i] = cube[RIGHT][2 - i][0];
                    cube[RIGHT][2 - i][0] = cube[BACK][2][2 - i];
                    cube[BACK][2][2 - i] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[BACK][2][2 - i];
                    cube[BACK][2][2 - i] = cube[RIGHT][2 - i][0];
                    cube[RIGHT][2 - i][0] = cube[FRONT][0][i];
                    cube[FRONT][0][i] = cube[LEFT][i][2];
                    cube[LEFT][i][2] = tmp;
                }
            }
            cube[UP] = set(cube[UP], clockwise);
        } else if (side == 'D') {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[LEFT][2 - i][0];
                    cube[LEFT][2 - i][0] = cube[BACK][0][i];
                    cube[BACK][0][i] = cube[RIGHT][i][2];
                    cube[RIGHT][i][2] = cube[FRONT][2][2 - i];
                    cube[FRONT][2][2 - i] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[FRONT][2][2 - i];
                    cube[FRONT][2][2 - i] = cube[RIGHT][i][2];
                    cube[RIGHT][i][2] = cube[BACK][0][i];
                    cube[BACK][0][i] = cube[LEFT][2 - i][0];
                    cube[LEFT][2 - i][0] = tmp;
                }
            }
            cube[DOWN] = set(cube[DOWN], clockwise);
        } else if (side == 'F') {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[LEFT][2][2 - i];
                    cube[LEFT][2][2 - i] = cube[DOWN][0][i];
                    cube[DOWN][UP][i] = cube[RIGHT][2][2 - i];
                    cube[RIGHT][2][2 - i] = cube[UP][2][2 - i];
                    cube[UP][2][2 - i] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[UP][2][2 - i];
                    cube[UP][2][2 - i] = cube[RIGHT][2][2 - i];
                    cube[RIGHT][2][2 - i] = cube[DOWN][0][i];
                    cube[DOWN][0][i] = cube[LEFT][2][2 - i];
                    cube[LEFT][2][2 - i] = tmp;
                }
            }
            cube[FRONT] = set(cube[FRONT], clockwise);
        } else if (side == 'B') {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[LEFT][0][i];
                    cube[LEFT][0][i] = cube[UP][0][i];
                    cube[UP][0][i] = cube[RIGHT][0][i];
                    cube[RIGHT][0][i] = cube[DOWN][2][2 - i];
                    cube[DOWN][2][2 - i] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[DOWN][2][2 - i];
                    cube[DOWN][2][2 - i] = cube[RIGHT][0][i];
                    cube[RIGHT][0][i] = cube[UP][0][i];
                    cube[UP][0][i] = cube[LEFT][0][i];
                    cube[LEFT][0][i] = tmp;
                }
            }
            cube[BACK] = set(cube[BACK], clockwise);
        } else if (side == 'L') {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[DOWN][2 - i][0];
                    cube[DOWN][2 - i][0] = cube[FRONT][2 - i][0];
                    cube[FRONT][2 - i][0] = cube[UP][2 - i][0];
                    cube[UP][2 - i][0] = cube[BACK][2 - i][0];
                    cube[BACK][2 - i][0] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[BACK][2 - i][0];
                    cube[BACK][2 - i][0] = cube[UP][2 - i][0];
                    cube[UP][2 - i][0] = cube[FRONT][2 - i][0];
                    cube[FRONT][2 - i][0] = cube[DOWN][2 - i][0];
                    cube[DOWN][2 - i][0] = tmp;
                }
            }
            cube[LEFT] = set(cube[LEFT], clockwise);
        } else {
            if (clockwise == '+') {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[UP][i][2];
                    cube[UP][i][2] = cube[FRONT][i][2];
                    cube[FRONT][i][2] = cube[DOWN][i][2];
                    cube[DOWN][i][2] = cube[BACK][i][2];
                    cube[BACK][i][2] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    char tmp = cube[BACK][i][2];
                    cube[BACK][i][2] = cube[DOWN][i][2];
                    cube[DOWN][i][2] = cube[FRONT][i][2];
                    cube[FRONT][i][2] = cube[UP][i][2];
                    cube[UP][i][2] = tmp;
                }
            }
            cube[RIGHT] = set(cube[RIGHT], clockwise);
        }
    }

    public static void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cube[0][i][j]);
            }
            sb.append("\n");
        }
    }
}