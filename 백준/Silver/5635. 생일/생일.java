import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Birth min = new Birth("", 1, 1, 2020);
        Birth max = new Birth("", 1, 1, 1980);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Birth b = new Birth(
                    st.nextToken(), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
            );

            if (min.compareTo(b) > 0) {
                min = b;
            }
            if (max.compareTo(b) < 0) {
                max = b;
            }
        }

        sb.append(max.name).append("\n").append(min.name);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Birth implements Comparable<Birth> {

        String name;
        int month, day, year;

        public Birth(String name, int day, int month, int year) {
            this.name = name;
            this.month = month;
            this.day = day;
            this.year = year;
        }

        @Override
        public int compareTo(Birth o) {
            if (year == o.year) {
                if (month == o.month) {
                    return Integer.compare(day, o.day);
                }
                return Integer.compare(month, o.month);
            }
            return Integer.compare(year, o.year);
        }
    }
}