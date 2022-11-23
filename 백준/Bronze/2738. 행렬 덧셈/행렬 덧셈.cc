#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {

    int N, M, B;
    cin >> N >> M;
    vector<vector<int>> A(N, vector<int>(M));

    for (int row = 0; row < N; row++) {
        for (int col = 0; col < M; col++) {
            cin >> A[row][col];
        }
    }
    
    for (int row = 0; row < N; row++) {
        for (int col = 0; col < M; col++) {
            cin >> B;
            A[row][col] += B;
            cout << A[row][col] << " ";
        }
        cout << endl;
    }
}