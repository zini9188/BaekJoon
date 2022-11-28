#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M, answer = 0;
    cin >> N >> M;

    map<string, bool> S;
    string str;
    for (int i = 0; i < N; i++) {
        cin >> str;
        S[str] = true;
    }

    for(int i = 0 ; i < M; i++){
        cin >> str;
        if(S[str]){
            answer++;
        }
    }
    cout << answer;
}