import java.util.*;
class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];        
        // 명예의 전당 목록의 점수의 개수 k
        // 1~마지막날까지 출연한 가수들의 점수 score
        // 매일 발표된 명예의 전당의 최하위 점수를 반환        
        PriorityQueue<Integer> ranking = new PriorityQueue<>();
        for(int i = 0; i < score.length; i++){
            int s = score[i];
            // 랭킹이 비어 있는 경우 값을 넣어준다.
            if(ranking.size() < k) ranking.add(s);     
            // 랭킹이 비어있지 않은 경우 k의 값을 확인하여 
            else if(ranking.size() == k && ranking.peek() < s){
                ranking.poll();
                ranking.add(s);                               
            }
            answer[i] = ranking.peek();
        }
        return answer;
    }
}