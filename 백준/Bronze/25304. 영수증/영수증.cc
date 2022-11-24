#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int X, N, a, b;

    cin >> X >> N;
    int answer = X;
    for (int i = 0; i < N; i++) {
        cin >> a >> b;
        answer -= (a * b);
    }

    if(answer == 0){
        cout << "Yes";
    }else{
        cout << "No";
    }
}