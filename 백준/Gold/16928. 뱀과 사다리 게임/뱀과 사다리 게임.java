import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] ladderAndSnake = new int[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        
        for (int i = 0; i < N + M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            ladderAndSnake[from] = to;
        }
        
        solution();
        br.close();
    }

    private static void solution() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 1});
        boolean[] visited = new boolean[101];
        visited[1] = true;
        
        while (!queue.isEmpty()) {          
            int[] temp = queue.poll();
            int count = temp[0];
            int pos = temp[1];
            
            if (pos == 100) {
                System.out.println(count);
                return;
            }
            
            for (int i = 1; i < 7; i++) {
                int np = pos + i;
                if (np > 100) continue;
                if (visited[np]) continue;
                visited[np] = true;
                
                if (ladderAndSnake[np] == 0) {
                    queue.add(new int[]{count + 1, np});
                } else {
                    if (!visited[ladderAndSnake[np]]) {
                        visited[ladderAndSnake[np]] = true;
                        queue.add(new int[]{count + 1, ladderAndSnake[np]});
                    }
                }
            }
        }
    }
}
