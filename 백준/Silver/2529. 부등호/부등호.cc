#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
int N;
vector<int> number(10);
vector<char> redirection(10);
vector<string> answer;

bool check(char oper, int a, char b) {
    return oper == '>' && a < b - '0' || oper == '<' && a > b - '0';
}

void dfs(int idx, string num) {
    if (num.size() > N) {
        answer.push_back(num);
        return;
    }
    for (int i = 0; i < 10; i++) {
        if (number[i]) continue;
        if (idx == 0) {
            number[i] = 1;
            dfs(idx + 1, to_string(i));
            number[i] = 0;
            continue;
        }
        if (check(redirection[idx - 1], i, num[idx - 1])) {
            number[i] = 1;
            dfs(idx + 1, num + to_string(i));
            number[i] = 0;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N;
    for (int i = 0; i < N; i++) cin >> redirection[i];
    dfs(0, "");
    sort(answer.begin(), answer.end());
    cout << answer.back() << '\n' << answer.front();
}
