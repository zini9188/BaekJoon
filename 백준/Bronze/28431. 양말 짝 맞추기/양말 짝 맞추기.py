arr = [0 for i in range(10)]
for i in range(5):
    n = int(input())
    arr[n] += 1

for i in range(0, 10):
    if arr[i] > 0 and arr[i] % 2 == 1:
        print(i)
