import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            String input;
            HashMap<String, String> soundOfAnimal = new HashMap<>();
            while (!(input = br.readLine()).equals("what does the fox say?")){
                String[] split = input.split(" goes ");
                soundOfAnimal.put(split[1], split[0]);
            }

            while (st.hasMoreTokens()){
                String temp = st.nextToken();
                if(!soundOfAnimal.containsKey(temp)){
                    sb.append(temp).append(" ");
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}