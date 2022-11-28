#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<int> card(500001);

    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> card[i];
    }    
    sort(card.begin(), card.begin() + N);

    int M, number;
    cin >> M;
    for (int i = 0; i < M; i++) {
        cin >> number;
        int left = 0, right = N - 1, mid, flag = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (card[mid] == number) {
                flag = 1;
                break;
            }else if(card[mid] > number){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        cout << flag << " ";
    }
}