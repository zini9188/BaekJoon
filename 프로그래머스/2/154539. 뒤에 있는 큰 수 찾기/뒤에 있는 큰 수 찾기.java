import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        Arrays.fill(answer, -1);
        Stack<Integer> maximum = new Stack<>();
        for(int i = numbers.length - 1; i >= 0; i--){
            while(!maximum.isEmpty() && maximum.peek() <= numbers[i]){
                maximum.pop();
            }
            
            if(!maximum.isEmpty() && maximum.peek() > numbers[i]){
                answer[i] = maximum.peek();
            }
            maximum.add(numbers[i]);
        }        
        
        return answer;
    }
}