// 백준 13305 주유소 실버4


#include <iostream>
using namespace std;

// N 개의 도시가 있다.
// 제일 왼쪽 도시에서 오른쪽 도시로 자동차를 이용하여 이동
// 각 도시의 주유소의 기름 가격과, 각 도시를 연결하는 도로의 길이를
// 입력으로 받아 이동하는 가격의 최소 비용 계산하는 프로그램

int main() {
    int n;
    long long int price[100000];
    long long int dist[100000];
    cin >> n;  // 도시 개수
    for (int i = 0; i < n - 1; i++)
        cin >> dist[i];
    for (int i = 0; i < n; i++)
        cin >> price[i];

    // 첫 도시에서 무조건 첫 거리만큼의 기름은 넣어야 함
    // 두번째 도시에 도착했을 때의 가격이 더 낮으면 교체, 그렇지 않으면 그대로
    // 마지막 도시의 가격은 필요 없으니 n-1까지
    long long int low = price[0];
    long long int total = low * dist[0];
    for (int i = 1; i < n - 1; i++) {
        low = low > price[i] ? price[i] : low;  // 최솟값 비교
        total += low * dist[i];
    }
    cout << total;
}