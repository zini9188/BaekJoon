s1, s2 = map(int, input().split())
flag = False

for i in range(s1):
    man, test = map(int, input().split())
    if man!=test:
        print("Wrong Answer")
        exit()

for i in range(s2):
    man, test = map(int, input().split())
    if man!=test:
        print("Why Wrong!!!")
        exit()

print("Accepted")