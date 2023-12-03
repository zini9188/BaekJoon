import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer tokenizer;
    private static StringBuilder sb = new StringBuilder();
    private static Map<String, Member> organ = new HashMap<>();
    private static Set<String> members = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int N;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            organ.clear();
            members.clear();
            String first = null;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(br.readLine(), ":|,|.");
                String name = tokenizer.nextToken();
                if (i == 0) {
                    first = name;
                }

                if (!organ.containsKey(name)) {
                    organ.put(name, new Member(IntStream.range(0, tokenizer.countTokens())
                            .mapToObj(o -> tokenizer.nextToken())
                            .collect(Collectors.toList())
                    ));
                }
            }

            sb.append(recursive(first, 0)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int recursive(String key, int count) {
        if (organ.containsKey(key)) {
            for (String child : organ.get(key).member) {
                if (!members.contains(child)) {
                    members.add(child);
                    count += recursive(child, organ.get(child) == null ? 1 : 0);
                }
            }
        }
        return count;
    }

    private static class Member {
        List<String> member;

        public Member(List<String> member) {
            this.member = member;
        }
    }
}