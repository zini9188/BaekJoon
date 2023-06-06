import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, totalPopulation;
    static City[] cities;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        cities = new City[N + 1];
        visited = new boolean[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cities[i] = new City(Integer.parseInt(tokenizer.nextToken()));
            totalPopulation += cities[i].population;
        }

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int nearAreaCount = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < nearAreaCount; j++) {
                cities[i].connected.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        for (int i = 1; i < N; i++) {
            combine(i, 0, 1);
        }

        bw.write((answer == Integer.MAX_VALUE ? -1 : answer) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combine(int limit, int count, int index) {
        if (count == limit && isConnection()) {
            findPeople();
            return;
        }

        for (int i = index; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combine(limit, count + 1, i);
                visited[i] = false;
            }
        }
    }

    private static boolean isConnection() {
        ArrayList<Integer> group1 = new ArrayList<>();
        ArrayList<Integer> group2 = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                group1.add(i);
            } else {
                group2.add(i);
            }
        }

        return checkConnectionArea(group1) && checkConnectionArea(group2);
    }

    private static boolean checkConnectionArea(ArrayList<Integer> area) {
        if (area.size() == 1) {
            return true;
        }

        boolean[] v = new boolean[N + 1];
        Queue<Integer> node = new LinkedList<>();
        node.add(area.get(0));
        v[area.get(0)] = true;
        int size = 0;
        while (!node.isEmpty()) {
            int n = node.poll();
            for (Integer integer : cities[n].connected) {
                if (!v[integer] && visited[n] == visited[integer]) {
                    v[integer] = true;
                    node.add(integer);
                    size++;
                }
            }
        }

        return size == area.size() - 1;
    }

    private static void findPeople() {
        int population = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                population += cities[i].population;
            }
        }
        int diff = totalPopulation - population;
        answer = Math.min(Math.abs(population - diff), answer);
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