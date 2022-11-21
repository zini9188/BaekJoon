
#include <iostream>
#include <queue>
#include <stack>
using namespace std;

int seq_left(queue<int> q, int n) {
    int cnt = 0;
    while (q.front() != n) {
        q.push(q.front());
        q.pop();
        cnt++;
    }
    return cnt;
}
int seq_right(queue<int> q, int n) {
    stack<int> s;
    int cnt = 0;
    while (!q.empty()) {
        s.push(q.front());
        q.pop();
    }

    while (s.top() != n) {
        s.pop();
        cnt++;
    }
    cnt++;
    return cnt;
}

int main() {
    // 덱을 구현하기.
    int n, m, x, cnt = 0, left, right, size;
    queue<int> deque;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        deque.push(i);  // 1~n으로 이루어진 덱 만들기
    }
    int start = 1;
    for (int i = 0; i < m; i++) {  // m개의 위치를 찾기
        cin >> x;

        left = seq_left(deque, x);           // 왼쪽으로 돌았을 때 개수
        right = seq_right(deque, x);         // 오른쪽으로 돌았을 때 개수
        size = left < right ? left : right;  // 크면 left 작으면 right를 저장
        cnt += size;                         // 해당 개수만큼 카운트 증가

        while (1) {  // 해당 x 큐에서 제거
            if (deque.front() == x) {
                deque.pop();
                break;
            }
            deque.push(deque.front());
            deque.pop();
        }
    }
    cout << cnt;
}
