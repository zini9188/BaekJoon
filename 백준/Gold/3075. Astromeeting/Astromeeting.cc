#include <bits/stdc++.h>
using namespace std;

using lld = long long;

const int INF = 0x3F3F3F3F;
const lld LINF = 0x3F3F3F3F3F3F3F3F;

int main() {
	int T;
	scanf("%d", &T);
	while (T--) {
		int n, p, q;
		scanf("%d %d %d", &n, &p, &q);

		vector<int> people(n);
		for (auto& x : people) scanf("%d", &x);

		vector<vector<lld>> D(p, vector<lld>(p, LINF));
		while (q--) {
			int i, j, d;
			scanf("%d %d %d", &i, &j, &d);
			i--, j--;
			D[i][j] = min(D[i][j], d + 0LL);
			D[j][i] = min(D[j][i], d + 0LL);
		}

		for (int i = 0; i < p; ++i) D[i][i] = 0;

		for (int k = 0; k < p; ++k) {
			for (int i = 0; i < p; ++i) { 
				if (D[i][k] == LINF) continue;
				for (int j = 0; j < p; ++j) {
					if (D[k][j] == LINF) continue;
					D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}

		int planet;
		lld ans = LINF;
		// 은하를 하나 잡고
		for (int i = 0; i < p; ++i) {
			// 나머지 사람들과의 거리
			lld dist = 0LL;
			bool find = true;
			for (auto& p : people) {
				if (D[i][p - 1] >= INF) {
					find = false;
					break;
				}
				dist += D[i][p - 1] * D[i][p - 1];
			}

			if (find && ans > dist) {
				ans = dist;
				planet = i + 1;
			}
		}
		printf("%d %lld\n", planet, ans);
	}
	return 0;
}