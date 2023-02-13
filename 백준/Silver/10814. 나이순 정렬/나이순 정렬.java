import java.io.*;
import java.util.*;

public class Main {
    static class Member {
        int age;
        String name;

        public Member(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    static int N;
    static ArrayList<Member> members;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        members = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            members.add(new Member(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken()));
        }
        members.sort(Comparator.comparingInt(o -> o.age));
        StringBuilder builder = new StringBuilder();
        for (Member member : members) {
            builder.append(member.age)
                    .append(" ")
                    .append(member.name)
                    .append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}