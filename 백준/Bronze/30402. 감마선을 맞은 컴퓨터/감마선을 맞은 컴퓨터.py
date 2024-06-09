board = []

for i in range(0, 15) :
    board = list(map(str, input().split()))
    
    if 'w' in board:
        print('chunbae')
        break
    elif 'b' in board:
        print('nabi')
        break
    elif 'g' in board:
        print('yeongcheol')
        break