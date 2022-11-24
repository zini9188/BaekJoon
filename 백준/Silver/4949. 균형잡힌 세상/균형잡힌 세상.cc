
#include <iostream>
#include <stack>
using namespace std;

bool isMatch(string t) {
    stack<char> s;
    int len = t.length();
    char c;
    for (int i = 0; i < len; i++) {
        c = t[i];

        if (c == '(' || c == '[') {  // c가 (또는 [인 경우 스택에 추가
            s.push(c);
        }

        if (c == ')') {                       // ) 일 때
            if (s.empty() || s.top() == '[')  // [ 이거나 비어있으면 false
                return false;
            s.pop();
        }
        if (c == ']') {                       // ] 일 때
            if (s.empty() || s.top() == '(')  // ( 이거나 비어있으면 false
                return false;
            s.pop();
        }
    }
    return s.empty();
}

int main() {
    string t;
    while (1) {
        getline(cin, t);
        if (t == "." && t.length() == 1)
            break;
        if (isMatch(t))
            cout << "yes\n";
        else
            cout << "no\n";
    }
}
