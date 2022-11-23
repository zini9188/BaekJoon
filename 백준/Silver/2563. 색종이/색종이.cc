#include <iostream>
#include <vector>

using namespace std;

int main() {

    vector<vector<bool>> A(100, vector<bool>(100, false));
    int N, X, Y;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> X >> Y;
        for (int row = X; row < X + 10; row++) {
            for (int col = Y; col < Y + 10; col++) {
                if (!A[row][col]) {
                    A[row][col] = true;
                }
            }
        }
    }
    int answer = 0;
    for (const auto& row: A) {
        for (auto col: row) {
            if (col) {
                answer++;
            }
        }
    }

    cout << answer;
}