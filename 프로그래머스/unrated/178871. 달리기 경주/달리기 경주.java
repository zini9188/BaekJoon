import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        Map<String, Integer> ranks = new HashMap<>();
        for(int i = 0; i < players.length; i++){
            ranks.put(players[i], i);
        }
        
        for(int i = 0; i < callings.length; i++){
            String calling = callings[i];
            int back = ranks.get(calling);
            String front = players[back - 1];     
            
            swap(players, back - 1, back);
            
            ranks.put(front, back);
            ranks.put(calling, back - 1);
        }
        
        for(int i = 0; i < players.length; i++){
            answer[i] = players[i];
        }
        
        return answer;
    }
    
    private void swap(String[] players, int front, int back){
        String temp = players[front];
        players[front] = players[back];
        players[back] = temp;
    }
}