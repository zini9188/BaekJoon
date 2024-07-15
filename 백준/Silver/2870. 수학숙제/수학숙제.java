import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("\\d+");
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String num = matcher.group().replaceAll("^0+", "");
                answer.add(num.length() == 0 ? "0" : num);
            }
        }
        answer.sort((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2)
                : o1.length() - o2.length());

        for (String s : answer) {
            sb.append(s).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}