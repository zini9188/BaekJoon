import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);
        solution();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        firstQ();
        secondQ();
        thirdQ();
        fourthQ();
    }

    private static void firstQ() {
        double aver = arr.stream().mapToDouble(value -> value).average().orElse(0.0);
        System.out.println(Math.round(aver));
    }

    private static void secondQ() {
        System.out.println(arr.get(arr.size() / 2));
    }

    private static void thirdQ() {
        int[] temp = new int[8001];
        int max = 0;
        for (Integer integer : arr) {
            temp[integer + 4000]++;
            max = Math.max(max, temp[integer + 4000]);
        }
        int result = 0;
        int count = 0;
        for (int i = 0; i < 8001; i++) {
            if (temp[i] == max) {
                result = i;
                count++;
            }
            if (count == 2) break;
        }
        System.out.println(result - 4000);
    }

    private static void fourthQ() {
        System.out.println(arr.get(arr.size() - 1) - arr.get(0));
    }
}