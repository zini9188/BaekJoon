import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static Stack<Integer> st;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        visited = new boolean[51];
        st = new Stack<>();
        flag = false;
        String input = br.readLine();
        solution(input);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void solution(String input) {
        if (flag) {
            return;
        }

        if (input.isEmpty()) {

            ArrayList<Integer> arr = new ArrayList<>(st);
            ArrayList<Integer> arr2 = new ArrayList<>(st);
            Collections.sort(arr);
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) != i + 1) {
                    return;
                }
            }

            for (Integer i : arr2) {
                sb.append(i).append(" ");
            }

            flag = true;
            return;
        }

        int f1 = Integer.parseInt(input.substring(0, 1));
        String b1 = input.substring(1);

        if (f1 >= 1 && f1 <= 50 && !visited[f1]) {
            visited[f1] = true;
            st.push(f1);
            solution(b1);
            visited[f1] = false;
            st.pop();
        }

        if (input.length() == 1) {
            return;
        }

        int f2 = Integer.parseInt(input.substring(0, 2));
        String b2 = input.substring(2);

        if (f2 >= 1 && f2 <= 50 && !visited[f2]) {
            visited[f2] = true;
            st.push(f2);
            solution(b2);
            visited[f2] = false;
            st.pop();
        }
    }
}