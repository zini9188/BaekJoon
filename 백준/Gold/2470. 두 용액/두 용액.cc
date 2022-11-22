#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N, n;
    vector<int> solution;
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> n;
        solution.push_back(n);
    }
    sort(solution.begin(), solution.end());    
    
    int left = 0, right = N - 1;
    long long sum, arrLeft = solution[left], arrRight = solution[right], closer = arrLeft + arrRight;
    
    while (left < right) {
        sum = solution[left] + solution[right];
        if (abs(closer) > abs(sum)) {
            closer = sum;
            arrLeft = solution[left];
            arrRight = solution[right];
        } else if (sum >= 0) {
            right--;
        } else {
            left++;
        }
    }
    cout << arrLeft << " " << arrRight;
}