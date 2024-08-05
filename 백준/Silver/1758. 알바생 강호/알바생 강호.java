import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            customers.add(new Customer(i + 1, read()));
        }
        Collections.sort(customers);

        long ans = 0;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            int tip = customer.tip - (i);
            if (tip >= 0) {
                ans += tip;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Customer implements Comparable<Customer> {

        int idx;
        int tip;

        public Customer(int idx, int tip) {
            this.idx = idx;
            this.tip = tip;
        }

        @Override
        public int compareTo(Customer o) {
            if (tip == o.tip) {
                return idx - o.idx;
            }
            return o.tip - tip;
        }
    }
}