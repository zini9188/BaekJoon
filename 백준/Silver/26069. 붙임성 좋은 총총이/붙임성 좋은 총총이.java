import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        
        Map<String, Boolean> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            String[] line = br.readLine().split(" ");
            String a = line[0];
            String b = line[1];
            
            if(a.equals("ChongChong") || b.equals("ChongChong")) {
                map.put("ChongChong", true);
            }
            
            if(map.containsKey("ChongChong")){
                if(map.containsKey(a) || map.containsKey(b)){
                    map.put(a, true);
                    map.put(b, true);
                }
            }            
        }
        
        int ans = 0;
        for(String key : map.keySet()){
            if(map.get(key)){
                ans++;
            }
        }
        
        System.out.println(ans);
        
    }
}
