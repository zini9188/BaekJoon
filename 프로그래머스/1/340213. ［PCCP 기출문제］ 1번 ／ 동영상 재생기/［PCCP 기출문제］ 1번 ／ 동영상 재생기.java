class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int t = convertToInt(pos);
        System.out.println(t);
        int f = convertToInt(op_start);
        int e = convertToInt(op_end);
        int m = convertToInt(video_len);
        for(String command : commands) {
            t = checkRange(f, e, t);
            if(command.equals("prev")) {
                t -= 10;
                t = t <= 0 ? 0 : t;
            } else {
                t += 10;
                t = t >= m ? m : t;
            }
            t = checkRange(f, e, t);
        }
        
        return convertIntToString(t);
    }
    
    public static int checkRange(int f, int e, int t) {
        if(f <= t && t <= e) {
            return e;
        }
        return t;
    }
    
    public static int convertToInt(String t) {
        String[] split = t.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
    
    public static String convertIntToString(int t) {
        String m = (t / 60) + "";
        String s = (t % 60) + "";
        
        return String.format("%s:%s", m.length() == 1 ? "0" + m : m, s.length() == 1 ? "0" + s : s);
    }
}