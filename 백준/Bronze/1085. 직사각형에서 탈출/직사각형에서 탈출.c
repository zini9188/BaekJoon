#include<stdio.h>

int main(){
	
	int x,y,w,h;
	int	lan[5];
	int min=lan[0];
	
	scanf("%d %d %d %d",&x,&y,&w,&h);
	
	lan[0]=x;
	lan[1]=y;
	lan[2]=w-x;
	lan[3]=h-y;
	
	for(int i=0;i<4;i++)
	{
		if(min>lan[i])
			min=lan[i];
	}
	
	printf("%d",min);	
}