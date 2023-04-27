import java.util.*;

class Solution {
    Map<String, HashSet<String>> reportMap = new HashMap<>();
    Map<String, Integer> result = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {        
        // id_list : 유저 리스트
        // report : 신고 리스트      
        
        int[] answer = new int[id_list.length];
        
        for(String id : id_list){
            reportMap.put(id, new HashSet<>());        
            result.put(id, 0);
        }
        
        for(String reportLine : report){
            StringTokenizer tokenizer = new StringTokenizer(reportLine);
            String reporter = tokenizer.nextToken();
            String reported = tokenizer.nextToken();
            HashSet<String> get = reportMap.get(reported);
            get.add(reporter);
            reportMap.put(reported, get);            
        }
        
        for(String id : id_list){
            HashSet<String> set = reportMap.get(id);
            if(set.size() >= k){
                Iterator<String> iter = set.iterator();
                while(iter.hasNext()){
                    String user = iter.next();
                    result.put(user, result.getOrDefault(user, 0) + 1);
                }
            }
        }
        
        int index = 0;
        for(String id : id_list){
            answer[index++] = result.get(id);            
        }        
        
        return answer;
    }
}