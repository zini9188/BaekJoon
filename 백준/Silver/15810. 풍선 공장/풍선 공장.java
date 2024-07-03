import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Staff> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long make = Long.parseLong(st.nextToken());
            pq.add(new Staff(make, make));
        }

        int cnt = 0;
        long time = 0;
        while (!pq.isEmpty() && cnt < M) {
            Staff staff = pq.poll();

            long next = staff.next + staff.make;
            pq.add(new Staff(staff.make, next));
            cnt++;
            time = staff.next;
        }

        sb.append(time);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Staff implements Comparable<Staff> {

        long make, next;

        public Staff(long make, long next) {
            this.make = make;
            this.next = next;
        }

        @Override
        public int compareTo(Staff o) {
            return (int) (next - o.next);
        }
    }
}