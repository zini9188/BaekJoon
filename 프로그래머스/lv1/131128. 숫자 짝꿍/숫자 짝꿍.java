class Solution {
    public String solution(String X, String Y) {        
        char[] xToChar = X.toCharArray();
        char[] yToChar = Y.toCharArray();        
        int[] xCount = new int[10];
        int[] yCount = new int[10];
        
        for(char x : xToChar){
            xCount[x - '0']++;
        }
        for(char y : yToChar){
            yCount[y - '0']++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i = 9; i >= 0; i--){
            int duplicate = Math.min(xCount[i], yCount[i]);
            if(duplicate > 0){
                for(int j = 0; j < duplicate; j++){
                    answer.append(i);
                }                
            }
        }
        
        if(answer.toString().equals("")){
            return "-1";
        }
        
        if(answer.toString().startsWith("0")){
            return "0";
        }
        
        return answer.toString();        
    }
}