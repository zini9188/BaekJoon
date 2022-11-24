#include <iostream>
using namespace std;
int n, m;
// 24 10 -> 24!의 5개수 - 14! 10!의 5개수

int mydiv(int num, int div_num) {
    // 5의 몇승까지 인지를 구함
    int cnt = 0;
    if (num < div_num)  // 5보다 작은 팩토리얼이면 0리턴
        return 0;
    for (long long int i = div_num; i <= num; i *= div_num) {
        cnt += num / i;
    }
    return cnt;
}
int count(int top, int bot) {
    int cnt_fiv = 0;
    int cnt_two = 0;
    cnt_fiv = mydiv(top, 5) - mydiv(top - bot, 5) - mydiv(bot, 5);
    cnt_two = mydiv(top, 2) - mydiv(top - bot, 2) - mydiv(bot, 2);
    int cnt = min(cnt_fiv, cnt_two);
    return cnt;
}

int main() {
    cin >> n >> m;

    cout << count(n, m);
}