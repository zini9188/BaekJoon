import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Map<String, Integer> tree = new TreeMap<>(String::compareTo);

        String name;
        double[] distribution = new double[10001];
        int index = 0;
        int count = 0;
        while ((name = br.readLine()) != null) {
            if (!tree.containsKey(name)) {
                tree.put(name, index++);
            }
            distribution[tree.get(name)]++;
            count++;
        }
        
        for (String key : tree.keySet()) {
            double n = (distribution[tree.get(key)] / count) * 100;
            sb.append(String.format("%s %.4f\n", key, n));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}