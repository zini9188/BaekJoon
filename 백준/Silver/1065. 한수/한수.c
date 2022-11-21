#include<stdio.h>

int fun(int num) {
	int n=0;
	int arr[4];

	if (num < 100)
		return 1;	
	else {
		while (num != 0) {
			arr[n] = num % 10;
			num /= 10;
			n++;
		}
		if (arr[2] - arr[1] == arr[1] - arr[0])
			return 1;
		else
			return 0;
	}
}
int main() {
	int N;
	int cnt = 0;
	scanf("%d", &N);
	if (N <= 1000&&N>=1) {
		for (int i = 1; i <= N; i++) {
			if (i == 1000)
				break;
			cnt += fun(i);
		}
	}
	printf("%d\n", cnt);
}