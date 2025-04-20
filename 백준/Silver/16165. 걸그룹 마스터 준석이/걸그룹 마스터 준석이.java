import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Map<String, List<String>> map = new HashMap<>();
        Map<String, String> groupMapping = new HashMap<>();
        for(int i = 0; i < N; i++) {
            String teamName = br.readLine();
            
            int count = Integer.parseInt(br.readLine());
            List<String> members = new ArrayList<>(count);
            for(int j = 0; j < count; j++) {
                String member = br.readLine();
                members.add(member);
                groupMapping.put(member, teamName);
            }
            Collections.sort(members);
            map.put(teamName, members);
        }
        
        for(int i = 0; i < M; i++) {
            String quiz = br.readLine();
            int option = Integer.parseInt(br.readLine());
            if(option == 0) {
                List<String> members = map.get(quiz);
                for(String member : members) {
                    System.out.println(member);
                }
            } else {
                System.out.println(groupMapping.get(quiz));
            }
        }
    }
}