#include <iostream>
#include <queue>
using namespace std;

int main() {
    // 1 ~ N 번까지 N명의 사람이 원을 이루며 앉아있음
    // 양의 정수 K(<= N)이 주어짐.
    // 순서대로 K번째 사람을 제거
    // 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
    // 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
    // 원안에서 사람들이 제거되는 순서를 N, K 요세푸스 순열이라고 한다.
    // 7,3 요세푸스 순열은 3,6,2,7,5,1,4이다.
    queue<int> q;
    int n, k, idx = 0;
    cin.tie(NULL);
    cin.sync_with_stdio(false);
    cin >> n >> k;
    for (int i = 1; i <= n; i++)
        q.push(i);  // 1~n까지 큐에 삽입
    cout << "<";
    while (!q.empty()) {               // 큐가 비어있지 않을 때까지
        for (int i = 1; i < k; i++) {  // k번 돌 때까지 큐를 돌리기
            q.push(q.front());
            q.pop();
        }
        cout << q.front();
        q.pop();

        if (!q.empty())
            cout << ", ";
    }
    cout << ">";
}
