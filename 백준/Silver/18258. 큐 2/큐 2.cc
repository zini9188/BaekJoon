#include <iostream>
#include <queue>
using namespace std;

int main() {
    queue<int> q;
    int n, x;
    string cmd;
    cin >> n;
    cin.tie(NULL);
    cin.sync_with_stdio(false);
    for (int i = 0; i < n; i++) {
        cin >> cmd;
        if (cmd == "push") {
            cin >> x;
            q.push(x);
        }
        if (cmd == "front")
            if (q.empty())
                cout << "-1\n";
            else
                cout << q.front() << "\n";
        if (cmd == "back")
            if (q.empty())
                cout << "-1\n";
            else
                cout << q.back() << "\n";
        if (cmd == "size")
            if (q.empty())
                cout << "0\n";
            else
                cout << q.size() << "\n";
        if (cmd == "empty")
            if (q.empty())
                cout << "1\n";
            else
                cout << "0\n";
        if (cmd == "pop") {
            if (q.empty())
                cout << "-1\n";
            else {
                cout << q.front() << "\n";
                q.pop();
            }
        }
    }
}
