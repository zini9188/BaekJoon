#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(NULL);
  int n, a = 0, b = 0;
  cin >> n;
  for (int i = 0; i < n; i++) {
    char c;
    cin >> c;
    if (c == 'D')
      a++;
    else
      b++;
    if (abs(a - b) > 1)
      break;
  }
  cout << a << ":" << b;
  return 0;
}