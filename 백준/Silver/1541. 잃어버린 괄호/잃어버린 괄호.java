import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer sub = new StringTokenizer(br.readLine(), "-");
        int result = 0;
        boolean flag = false;
        while (sub.hasMoreTokens()) {
            StringTokenizer plus = new StringTokenizer(sub.nextToken(), "+");
            int sum = 0;
            while (plus.hasMoreTokens()) {
                sum += Integer.parseInt(plus.nextToken());
            }
            if(!flag){
                result += sum;
                flag = true;
            }else{
                result -= sum;
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}