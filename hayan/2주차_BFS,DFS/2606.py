n = int(input())
k = int(input())

graph = [[]*n for _ in range(n+1)]
visit = [False]*(n+1)
cnt = 0

def dfs(x):
    global cnt
    visit[x] = True
    for i in graph[x]:
        if visit[i]: continue
        cnt += 1
        dfs(i)

while k:
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

    k -= 1

dfs(1)

print(cnt)