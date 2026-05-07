# 처음 코드
from collections import deque

def solution(m, n, board):
    answer = 0
    
    new_board = [list(x) for x in board]
    
    while True:
        check = [[False]*n for _ in range(m)]
        q = deque()
        flag = False

        for i in range(m-1):
            for j in range(n-1):
                if new_board[i][j]=='0': continue
                if check_four(i, j, new_board, check):
                    flag = True

        if flag == False: #이제 4개 없는 경우 
            break

        for i in range(m):
            for j in range(n):
                if check[i][j] == True:
                    answer += 1
                    check[i][j] = False
                    new_board[i][j] = '0' #빈 곳

        for j in range(n):
            for i in range(m-1, -1, -1):
                if new_board[i][j] != '0':
                    q.append(new_board[i][j])
                new_board[i][j] = '0'
            #배열에 아래부터 넣어주는거 처리 
            for i in range(m-1, -1, -1):
                if not q:
                    break
                new_board[i][j] = q.popleft()
    
    return answer

def check_four(x, y, new_board, check):
    dx = [0, 1, 1]
    dy = [1, 0, 1]
    
    val = new_board[x][y]
    
    cnt = 1
    
    for k in range(3):
        a = dx[k]
        b = dy[k]
        if new_board[x+a][y+b]==val:
            cnt += 1

    #4개 같은 경우
    if cnt == 4:
        check[x][y] = True
        for k in range(3):
            a = dx[k]
            b = dy[k]
            check[x+a][y+b] = True
        
        return True
    
    return False


# 리팩토링
def solution(m, n, board):
    answer = 0
    
    board = [list(x) for x in board]
    
    while True:
        #4개 모이면 지워질 것들 좌표 위치
        remove = set()

        for i in range(m-1):
            for j in range(n-1):
                now = board[i][j]
                if now=='0': 
                    continue
                if now == board[i][j+1] == board[i+1][j] == board[i+1][j+1]:
                    remove.update([
                        (i, j),
                        (i, j+1),
                        (i+1, j),
                        (i+1, j+1)
                    ])

        #이제 4개 없는 경우 
        if not remove:
            break

        answer += len(remove)
        for x, y in remove:
            board[x][y] = '0' #빈 곳

		#아래로 내리기
        for j in range(n):
            blocks = []
            
            for i in range(m):
                if board[i][j]!='0':
                    blocks.append(board[i][j])
            
            empty = m - len(blocks)
            
            for i in range(empty):
                board[i][j] = '0'
            
            for i in range(len(blocks)):
                board[empty+i][j] = blocks[i]
                
    return answer