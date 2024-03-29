
#include <iostream>
#include <stack>
using namespace std;
int seq[1000001];
int aws[1000001];
int main() {
    int n, a;
    stack<int> s;
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> seq[i];  // n개의 배열로 받기

    for (int i = n - 1; i >= 0; i--) {
        // 스택이 비지 않았으며 seq[i]가 s.top보다 크면 pop
        while (!s.empty() && s.top() <= seq[i])
            s.pop();
        if (s.empty())  // 스택이 비어있으면 -1
            aws[i] = -1;
        else  // 정답에 스택의 top을 입력
            aws[i] = s.top();
        s.push(seq[i]);
    }
    for (int i = 0; i < n; i++)
        cout << aws[i] << " ";
}
