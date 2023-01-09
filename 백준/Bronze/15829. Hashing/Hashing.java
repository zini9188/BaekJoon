import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int L = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine();
        int R = 31;
        int M = 1234567891;
        long res = 0;
        long r = 1;
        for (char c : str.toCharArray()) {
            res += ((c - 'a' + 1) * r) % M;
            r = (r * R) % M;
        }
        System.out.println(res);
    }
}