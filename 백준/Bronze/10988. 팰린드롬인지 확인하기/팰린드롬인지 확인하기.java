import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String word = br.readLine();
        bw.write(isPalindrome(word) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int isPalindrome(String word) {
        int left = 0, right = word.length() - 1;
        while (left <= right) {
            if (word.charAt(left) == word.charAt(right)) {
                left++;
                right--;
            } else {
                return 0;
            }
        }
        return 1;
    }
}