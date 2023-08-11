import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] dwarf = new int[9];
        int score = 0;
        for (int i = 0; i < 9; i++) {
            int n = Integer.parseInt(br.readLine());
            dwarf[i] = n;
            score += n;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (dwarf[i] + dwarf[j] == score - 100) {
                    for(int k = 0; k < 9; k++){
                        if(k != i && k != j){
                            builder.append(dwarf[k]).append("\n");
                        }
                    }

                    bw.write(builder.toString());
                    bw.flush();
                    bw.close();
                    br.close();
                    break;
                }
            }
        }
    }
}