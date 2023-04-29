import java.util.*;

class Solution {
    static class Process{
        String name;
        int start;
        int playtime;
        public Process(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        Stack<Process> stack = new Stack<>();
        PriorityQueue<Process> queue = new PriorityQueue<>((o1, o2) -> {
            return o1.start - o2.start;
        });
        
        // queue에 차례대로 진행할 과제를 넣는다.
        for(String[] plan : plans){
            String name = plan[0];
            String[] time = plan[1].split(":");
            int start = calculateTime(time);
            int playtime = Integer.parseInt(plan[2]);
            queue.add(new Process(name, start, playtime));
        }
        
        // queue의 값을 하나씩 빼면서 과제를 시작한다.
        int index = 0;
        while(!queue.isEmpty()){
            Process process = queue.poll();   
            // 잠시 멈춘 과제들이 현재 과제 시작 시간 이전에 끝낼 수 있는지 확인한다.
            if(!stack.isEmpty()){
                System.out.println(stack.peek().name + " " + process.name);
                while(!stack.isEmpty() && canFinish(stack.peek(), process)){
                    // 끝낼 수 있는 경우들
                    int update = stack.peek().start + stack.peek().playtime;
                    answer[index++] = stack.peek().name;
                    stack.pop();
                    if(!stack.isEmpty()){
                        stack.peek().start = update;
                    }
                }
                
                // 끝내지 못하는 경우
                if(!stack.isEmpty() && !canFinish(stack.peek(), process)){
                    stack.peek().playtime -= process.start - stack.peek().start;        
                }                
            }                            
            stack.push(process);            
        }
        
        while(!stack.isEmpty()){
            answer[index++] = stack.pop().name;
        }   
        
        return answer;
    }
    
    private int calculateTime(String[] time){
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
    
    private boolean canFinish(Process before, Process current){
        if(before.playtime <= 0){
            return true;
        }
        // 이전 과제가 현재 과제의 시작 시간까지 마칠 수 있는지
        return current.start - before.start >= before.playtime;
    }
}