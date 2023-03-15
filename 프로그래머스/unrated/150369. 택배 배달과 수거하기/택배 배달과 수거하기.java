class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long second = 0;
        while (n > 0) {
            while (n > 0 && deliveries[n - 1] == 0 && pickups[n - 1] == 0) {
                n--;
            }
            doSome(deliveries, n, cap);
            doSome(pickups, n, cap);
            second += (n * 2L);            
        }
        return second;
    }

    private void doSome(int[] boxes, int n, int cap) {
        long truck = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (truck + boxes[i] <= cap) {
                truck += boxes[i];
                boxes[i] = 0;
            } else {
                long canCarry = cap - truck;
                truck += canCarry;
                boxes[i] -= canCarry;
            }
            if (truck == cap) {
                break;
            }
        }
    }
}