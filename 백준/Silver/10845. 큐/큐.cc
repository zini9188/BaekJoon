#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, num;
    queue<int> q;
    string command;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> command;
        if (command == "push") {
            cin >> num;
            q.push(num);
        } else if (command == "front") {
            if (q.empty()) cout << "-1\n";
            else cout << q.front() << '\n';
        } else if (command == "back") {
            if (q.empty()) cout << "-1\n";
            else cout << q.back() << '\n';
        } else if (command == "size") {
            cout << q.size() << '\n';
        } else if (command == "empty") {
            cout << q.empty() << '\n';
        } else if (command == "pop") {
            if (q.empty()) cout << "-1\n";
            else {
                cout << q.front() << '\n';
                q.pop();
            }
        }
    }
}