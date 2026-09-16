import heapq

def solution(N, road, K):
    graph = [[] for _ in range(N + 1)]

    for a, b, c in road:
        graph[a].append((b, c))
        graph[b].append((a, c))

    INF = float('inf')
    dist = [INF] * (N + 1)
    dist[1] = 0

    pq = [(0, 1)]  # (거리, 노드)

    while pq:
        cur_dist, now = heapq.heappop(pq)

        if cur_dist > dist[now]:
            continue

        for next_node, cost in graph[now]:
            new_dist = cur_dist + cost

            if new_dist < dist[next_node]:
                dist[next_node] = new_dist
                heapq.heappush(pq, (new_dist, next_node))

    return sum(1 for d in dist if d <= K)