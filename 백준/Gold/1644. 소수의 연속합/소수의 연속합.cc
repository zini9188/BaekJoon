#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> eratos(int N) { // 에라토스테네스의 체
    vector<int> prime(N + 1); // N개의 숫자가 들어갈 수 있는 벡터 생성

    for (int i = 2; i <= N; i++) // 벡터에 2~의 숫자를 넣어줌
        prime[i] = i;

    for (int i = 2; i * i <= N; i++) {
        if (prime[i]) { // 아직 지워지지 않은 수라면
            for (int j = i * i; j <= N; j += i) { // 해당 값에 n배수를 전부 제거해준다.
                prime[j] = 0;
            }
        }
    }

    return prime;
}

int main() {
    int N;
    cin >> N;

    vector<int> connect;
    vector<int> prime(N + 1);
    prime = eratos(N);

    for (int i = 2; i <= N; i++) { // N 이하 소수들 전부 벡터에 넣어준다
        if (prime[i]) {
            connect.push_back(i);
        }
    }

    int left = 0, right = 0;
    int sum = 2;
    int answer = 0;
    while (right < connect.size() && left <= right) { // 투 포인터 사용
        if (sum == N) {
            answer++;
            sum += connect[++right];
        } else if (sum < N) {
            sum += connect[++right];
        } else {
            sum -= connect[left++];
        }
    }

    cout << answer;
}
