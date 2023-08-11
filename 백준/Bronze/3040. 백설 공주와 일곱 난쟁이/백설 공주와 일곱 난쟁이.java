import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dwarf = new int[9];
        int score = 0;
        for (int i = 0; i < 9; i++) {
            dwarf[i] = Integer.parseInt(br.readLine());
            score += dwarf[i];
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
                    break;
                }
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}