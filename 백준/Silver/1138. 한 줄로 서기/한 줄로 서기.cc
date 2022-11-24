#include <iostream>
using namespace std;
int pos[11];
int main() {
    int N, left;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> left;
        for (int j = 1; j <= N; j++) {
            if (left == 0 && pos[j] == 0) {
                pos[j] = i;
                break;
            } else if (pos[j] == 0)
                left--;
        }
    }

    for (int i = 1; i <= N; i++)
        cout << pos[i] << " ";
}