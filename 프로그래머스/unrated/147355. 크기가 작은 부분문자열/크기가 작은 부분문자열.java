class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int plen = p.length();
        int tlen = t.length();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tlen; i++){
            builder.append(t.charAt(i));
            if(builder.length() == plen){
                if(builder.toString().compareTo(p) <= 0){
                    answer++;
                }
                builder.deleteCharAt(0);
            }
        }        
        return answer;
    }
}