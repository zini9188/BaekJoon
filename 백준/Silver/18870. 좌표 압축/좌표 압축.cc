#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    vector<long long> points;
    vector<long long> temp;
    int n;
    for (int i = 0; i < N; i++) {
        cin >> n;
        points.push_back(n);
        temp.push_back(n);
    }
    sort(temp.begin(), temp.begin() + N);
    temp.erase(unique(temp.begin(), temp.end()), temp.end());
    for (auto point: points) {
        int left = 0, right = temp.size() - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (temp[mid] == point) {
                cout << mid << " ";
                break;
            } else if (temp[mid] > point) right = mid - 1;
            else left = mid + 1;
        }
    }
}