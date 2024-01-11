import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int roomIdx = 1;
        Queue<Integer> index = new PriorityQueue<>();
        Queue<Meet> meets = new PriorityQueue<>();
        Queue<Room> rooms = new PriorityQueue<>();
        index.add(roomIdx);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            meets.add(new Meet(s, e));
        }

        while (!meets.isEmpty()) {
            Meet cur = meets.poll();

            while (!rooms.isEmpty() && rooms.peek().end <= cur.s) {
                Room room = rooms.poll();
                index.add(room.idx);
            }

            if (!index.isEmpty()) {
                rooms.add(new Room(index.poll(), cur.e));
            } else {
                rooms.add(new Room(++roomIdx, cur.e));
            }
        }

        sb.append(roomIdx);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Meet implements Comparable<Meet> {

        int s, e;

        public Meet(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Meet o) {
            return s - o.s;
        }
    }

    static class Room implements Comparable<Room> {

        int idx, end;

        public Room(int idx, int end) {
            this.idx = idx;
            this.end = end;
        }

        @Override
        public int compareTo(Room o) {
            return end - o.end;
        }
    }
}