import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        Map<String, Integer> yearnPeople = new HashMap<>();
        
        for(int i = 0; i < yearning.length; i++){
            yearnPeople.put(name[i], yearning[i]); 
        }
        int index = 0;
        for(String[] target : photo){
            int score = 0;
            for(String people : target){
                score += yearnPeople.getOrDefault(people, 0);
            }
            answer[index++] = score;
        }
        
        return answer;
    }
}