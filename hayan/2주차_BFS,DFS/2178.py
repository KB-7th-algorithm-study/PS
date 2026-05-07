from collections import deque

n, m = map(int,input().split())
board = []
dist = [[0]*m for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(n):
    board.append(list(map(int, input())))

q = deque()
q.append((0, 0))
dist[0][0] += 1

while q:
    x, y = q.popleft()
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if nx<0 or nx >=n or ny<0 or ny >=m: continue
        if board[nx][ny]==1 and dist[nx][ny]==0:
            q.append((nx,ny))
            dist[nx][ny] = dist[x][y]+1

print(dist[n-1][m-1])