import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            people.add(new Person(Math.min(h, o), Math.max(h, o)));
        }
        int d = Integer.parseInt(br.readLine());
        Collections.sort(people);

        int ans = findMaxL(people, d);

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int findMaxL(List<Person> people, int d) {
        int ans = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (Person person : people) {
            if (person.end - person.start > d) {
                continue;
            }

            pq.add(person.start);

            while (!pq.isEmpty() && person.end - pq.peek() > d) {
                pq.poll();
            }

            ans = Math.max(ans, pq.size());
        }
        return ans;
    }

    static class Person implements Comparable<Person> {

        int start, end;

        public Person(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Person o) {
            return end - o.end;
        }
    }
}