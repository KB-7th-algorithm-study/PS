from collections import deque

def solution(n, vertex):
    graph = [[] for _ in range(n + 1)]

    for a, b in vertex:
        graph[a].append(b)
        graph[b].append(a)

    dist = [-1] * (n + 1)
    dist[1] = 0

    q = deque([1])

    while q:
        now = q.popleft()

        for nxt in graph[now]:
            if dist[nxt] == -1:
                dist[nxt] = dist[now] + 1
                q.append(nxt)

    max_dist = max(dist)

    return dist.count(max_dist)