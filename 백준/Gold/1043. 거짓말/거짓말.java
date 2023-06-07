import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());
        boolean[] know = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        int T = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i <= T; i++) {
            int known = Integer.parseInt(tokenizer.nextToken());
            know[known] = true;
            queue.add(known);
        }

        People[] people = new People[N + 1];
        Party[] parties = new Party[M + 1];

        for (int i = 0; i <= M; i++) {
            parties[i] = new Party();
        }
        for (int i = 0; i <= N; i++) {
            people[i] = new People();
        }

        for (int i = 1; i <= M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int visits = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < visits; j++) {
                int visitor = Integer.parseInt(tokenizer.nextToken());
                people[visitor].parties.add(i);
                parties[i].peoples.add(visitor);
            }
        }

        boolean[] fishStory = new boolean[M + 1];
        while (!queue.isEmpty()) {
            int known = queue.poll();
            for (Integer partyNumber : people[known].parties) {
                fishStory[partyNumber] = true;
                for (Integer visitPerson : parties[partyNumber].peoples) {
                    if (!know[visitPerson]) {
                        know[visitPerson] = true;
                        queue.add(visitPerson);
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < fishStory.length; i++) {
            if (!fishStory[i]) {
                answer++;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Party {
        ArrayList<Integer> peoples = new ArrayList<>();
    }

    static class People {
        ArrayList<Integer> parties = new ArrayList<>();
    }
}