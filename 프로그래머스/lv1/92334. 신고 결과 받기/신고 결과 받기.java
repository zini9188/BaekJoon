import java.util.*;

class Solution {    
    public int[] solution(String[] id_list, String[] report, int k) {        
        // id_list : 유저 리스트
        // report : 신고 리스트              
        int[] answer = new int[id_list.length];
        
        Map<String, HashSet<String>> reports = new HashMap<>();
        Map<String, Integer> userId = new HashMap<>();
        
        for(int i = 0; i < id_list.length; i++){
            reports.put(id_list[i], new HashSet<>());        
            userId.put(id_list[i], i);
        }
        
        for(String content : report){
            StringTokenizer tokenizer = new StringTokenizer(content);
            String reporter = tokenizer.nextToken();
            String reported = tokenizer.nextToken();
            HashSet<String> get = reports.get(reported);
            get.add(reporter);
            reports.put(reported, get);            
        }
        
        for(String id : id_list){
            HashSet<String> set = reports.get(id);
            if(set.size() >= k){
                Iterator<String> iter = set.iterator();
                while(iter.hasNext()){
                    String user = iter.next();
                    answer[userId.get(user)]++;
                }
            }
        }
        
        return answer;
    }
}