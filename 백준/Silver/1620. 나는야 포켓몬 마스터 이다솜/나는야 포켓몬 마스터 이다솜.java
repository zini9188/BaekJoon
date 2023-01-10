import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> nameKeyDictionary = new HashMap<>();
        HashMap<Integer, String> numKeyDictionary = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            nameKeyDictionary.put(name, i);
            numKeyDictionary.put(i, name);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            String command = br.readLine();
            if (Character.isDigit(command.charAt(0))) {
                stringBuilder.append(numKeyDictionary.get(Integer.parseInt(command))).append("\n");
            } else {
                stringBuilder.append(nameKeyDictionary.get(command)).append("\n");
            }
        }

        bw.write(stringBuilder.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}
