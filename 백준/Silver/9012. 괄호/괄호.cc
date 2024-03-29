#include <iostream>
#include <stack>
using namespace std;

bool isVps(string ps) {
    stack<char> s;
    int len;
    len = ps.length();
    char c;
    if (ps.front() == ')')  // 첫 글자가 )이면 false 리턴;
        return false;

    for (int i = 0; i < len; i++) {
        c = ps[i];
        if (c == '(')  // (이면 스택에 추가
            s.push(c);
        else                 // )이면 스택의 탑이 (인지 확인
            if (!s.empty())  // 스택이 비어있지 않으면 pop
                s.pop();
            else  // 비어있으면 false
                return false;
    }

    return s.empty();
}

int main() {
    string ps;
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> ps;  // ps 입력 받기
        if (isVps(ps))
            cout << "YES\n";
        else
            cout << "NO\n";
    }
}