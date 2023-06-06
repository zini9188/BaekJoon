import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, totalPopulation;
    static City[] cities;
    static boolean[] select;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        cities = new City[N + 1];
        select = new boolean[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cities[i] = new City(Integer.parseInt(tokenizer.nextToken()));
            totalPopulation += cities[i].population;
        }

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int adjoinArea = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < adjoinArea; j++) {
                cities[i].connected.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            combination(i, 0, 1);
        }

        bw.write((answer == Integer.MAX_VALUE ? -1 : answer) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int limit, int count, int index) {
        if (count == limit && isConnect()) {
            answer = Math.min(answer, getPopulationDiff());
            return;
        }

        for (int i = index; i <= N; i++) {
            if (!select[i]) {
                select[i] = true;
                combination(limit, count + 1, i);
                select[i] = false;
            }
        }
    }

    private static boolean isConnect() {
        ArrayList<Integer> groupA = new ArrayList<>();
        ArrayList<Integer> groupB = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (select[i]) {
                groupA.add(i);
            } else {
                groupB.add(i);
            }
        }

        return checkConnection(groupA) && checkConnection(groupB);
    }

    private static boolean checkConnection(ArrayList<Integer> group) {
        if (group.size() == 1) {
            return true;
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited[group.get(0)] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            for (Integer integer : cities[n].connected) {
                if (!visited[integer] && select[n] == select[integer]) {
                    visited[integer] = true;
                    queue.add(integer);
                    count++;
                }
            }
        }

        return count == group.size() - 1;
    }

    private static int getPopulationDiff() {
        int groupA = 0;
        for (int i = 1; i <= N; i++) {
            if (select[i]) {
                groupA += cities[i].population;
            }
        }
        int groupB = totalPopulation - groupA;
        return Math.abs(groupA - groupB);
    }

    private static class City {
        int population;
        ArrayList<Integer> connected;

        public City(int population) {
            this.population = population;
            this.connected = new ArrayList<>();
        }
    }
}