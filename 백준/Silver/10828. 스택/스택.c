#include<stdio.h>
#include<string.h>
#define MAX_SIZE 10001

typedef struct stack{
	int arr[MAX_SIZE];
	int top;
}stack;

void stact_init(stack *st)
{
	st->top = -1;
}

void push(stack *st, int n)
{
		//정수 X를 스택에 넣기	
	
	st->arr[++(st->top)] = n;
}
int pop(stack *st)
{
	// 스택 가장 위 정수 빼고 출력, 없으면 -1
	if (st->top == -1)
		return -1;
	else
		return st->arr[st->top--];
}
int size(stack *st)
{
	//스택에 들어있는 정수 개수 출력
	return st->top+1;
}
int empty(stack *st)
{
	//비어있으면 1 , 아니면 0
	if (st->top == -1)
		return 1;
	else
		return 0;
}

int top(stack *st)
{
	//스택의 가장위 정수를 출력 , 없으면 -1 
	if (st->top == -1)
		return -1;
	else
		return st->arr[st->top];
}
int main()
{
	int N, num;
	char str[10];
	stack st;
	scanf("%d", &N);
	fgetc(stdin);
	stact_init(&st);
	for (int i = 0; i < N; i++)
	{
		scanf("%s", str);
		fgetc(stdin);
		if (!strcmp(str, "push"))
		{
			scanf("%d", &num);
			fgetc(stdin);
			push(&st, num);
		}

		else if (!strcmp(str, "pop"))
		{    //pop인 경우 

			printf("%d\n", pop(&st));

		}
		else if (!strcmp(str, "empty"))
		{    //empty인경우 

			printf("%d\n", empty(&st));

		}
		else if (!strcmp(str, "size"))
		{        //size인 경우  

			printf("%d\n", size(&st));

		}
		else if (!strcmp(str, "top"))
		{        //top인 경우 

			printf("%d\n", top(&st));

		}
	}

}
