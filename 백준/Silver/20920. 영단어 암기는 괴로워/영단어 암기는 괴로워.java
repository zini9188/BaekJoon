import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static HashMap<String, Integer> frequency;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        frequency = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) {
                continue;
            }

            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            set.add(word);
        }

        List<String> sortedWords = set.stream()
                .sorted(new Comp())
                .collect(Collectors.toList());

        for (String sortedWord : sortedWords) {
            sb.append(sortedWord).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Comp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (frequency.get(o1).equals(frequency.get(o2))) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return frequency.get(o2) - frequency.get(o1);
        }
    }
}