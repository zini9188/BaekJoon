#include <string>
#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    stack<char> q;
    string s;
    int answer = 0;

    cin >> s;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '(') {
            q.push(s[i]);
        }else if(s[i] == ')' && s[i - 1] == '('){
            q.pop();
            answer += q.size();
        }else{
            answer++;
            q.pop();
        }
    }
    cout << answer;
}