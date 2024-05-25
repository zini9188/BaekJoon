import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String key = br.readLine();
            if(map.get(key) == null) {
                map.put(key, 1);
            }else {
                map.put(key, map.get(key) + 1);
            }
        }

        for(int i = 0; i < n-1; i++) {
            String key = br.readLine();
            map.put(key, map.get(key) - 1);
        }

        for(String key : map.keySet()) {
            if(map.get(key) != 0) {
                System.out.println(key);
                break;
            }
        }
    }
}
