import java.util.*;

class Solution {
    public int N;   // 전체 친구 수
    public int[] giftIndex;     // 선물 지수
    public int[] resultGifts;   // 다음달에 받을 선물 수
    public int[][] arr;     // 주고받은 선물 표

    public int solution(String[] friends, String[] gifts) {
        int answer;

        N = friends.length;
        giftIndex = new int[N];
        resultGifts = new int[N];
        arr = new int[N][N];

        init(friends, gifts);   // 주고받은 선물, 선물 지수 표
        calculateNextGift();    // 다음달에 받은 선물 계산

        answer = getMax();  // 다음달 받은 선물 중 최대값 계산

        return answer;
    }

    public void init(String[] friends, String[] gifts) {
        List<String> friendsList = Arrays.asList(friends);

        for(String gift: gifts){
            String[] input = gift.split(" ");
            String str1 = input[0];
            String str2 = input[1];

            int a = friendsList.indexOf(str1);
            int b = friendsList.indexOf(str2);

            arr[a][b] += 1;
            giftIndex[a] += 1;
            giftIndex[b] -= 1;
        }
    }

    public void calculateNextGift(){
        for(int a = 0; a < N-1; a++){   // 선물 준 사람
            for(int b = a+1; b < N; b++){   // 선물 받은 사람
                if(arr[a][b] != arr[b][a]){     // 주고받은 선물 수 다를 때
                    if(arr[a][b] > arr[b][a]){
                        resultGifts[a] += 1;
                    }
                    else{
                        resultGifts[b] += 1;
                    }
                }
                else{   // 주고받은 선물 수 같을 때
                    if(giftIndex[a] > giftIndex[b]){
                        resultGifts[a] += 1;
                    }
                    else if(giftIndex[a] < giftIndex[b]){
                        resultGifts[b] += 1;
                    }
                    else{
                        continue;
                    }
                }
            }
        }
    }

    public int getMax(){
        int max = resultGifts[0];
        for(int i = 0; i < N; i++){
            max = Math.max(max, resultGifts[i]);
        }
        return max;
    }
}