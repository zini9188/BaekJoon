import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());        
        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
                String cmd = tokenizer.nextToken();
                int n = Integer.parseInt(tokenizer.nextToken());
                if (cmd.equals("I")) {
                    treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
                } else {
                    if (treeMap.isEmpty()) 
                        continue;
                    
                    if (n == 1) {
                        int num = treeMap.get(treeMap.lastKey());
                        if (num == 1) {
                            treeMap.remove(treeMap.lastKey());
                        } else {
                            treeMap.put(treeMap.lastKey(), num - 1);
                        }
                    } else {
                        int num = treeMap.get(treeMap.firstKey());
                        if (num == 1) {
                            treeMap.remove(treeMap.firstKey());
                        } else {
                            treeMap.put(treeMap.firstKey(), num - 1);
                        }
                    }
                }
            }
            if (treeMap.isEmpty()) {
                builder.append("EMPTY").append("\n");
            } else {
                builder.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}