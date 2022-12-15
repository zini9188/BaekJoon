#include <stdio.h>
int y(int n){
	
	if((n%4==0&&n%100!=0)||n%400==0)
		return 1;
	else 
		return 0;
}

int main(){
	
	int n;
	scanf("%d",&n);
	
	
	printf("%d",y(n));
}