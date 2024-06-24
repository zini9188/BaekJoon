import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Alphabet> alphabets;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        alphabets = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphabets.add(new Alphabet(i, 0));
        }

        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
            find(inputs.get(i));
        }

        Collections.sort(alphabets);

        int digit = 9;
        for (Alphabet alphabet : alphabets) {
            alphabet.digit = digit--;
        }

        alphabets.sort(Comparator.comparingInt(o -> o.alpha));

        int ans = 0;
        for (String input : inputs) {
            digit = (int) Math.pow(10, input.length() - 1);
            int num = 0;
            for (int i = 0; i < input.length(); i++) {
                int n = input.charAt(i) - 'A';
                num += alphabets.get(n).digit * digit;
                digit /= 10;
            }

            ans += num;
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void find(String word) {
        int digit = (int) Math.pow(10, word.length() - 1);
        for (int i = 0; i < word.length(); i++) {
            int n = word.charAt(i) - 'A';
            alphabets.get(n).digit += digit;
            digit /= 10;
        }
    }

    static class Alphabet implements Comparable<Alphabet> {
        int alpha, digit;

        public Alphabet(int alpha, int digit) {
            this.alpha = alpha;
            this.digit = digit;
        }

        @Override
        public int compareTo(Alphabet o) {
            return o.digit - digit;
        }
    }
}