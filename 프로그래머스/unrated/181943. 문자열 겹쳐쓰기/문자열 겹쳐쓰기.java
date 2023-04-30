import java.lang.*;
class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        StringBuilder builder = new StringBuilder();
        builder.append(my_string.substring(0, s));
        builder.append(overwrite_string);
        builder.append(my_string.substring(s + overwrite_string.length()));
        return builder.toString();
    }
}