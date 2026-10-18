from collections import deque

def solution(n, infection, edges, k):
    answer = 0
    
    graph = [[] for _ in range(n+1)]
    
    for a, b, pipe_type in edges:
        graph[a].append((b, pipe_type))
        graph[b].append((a, pipe_type))
        
    infected = [False]*(n+1)
    infected[infection] = True
    
    def spread(pipe_type):
        q = deque()
        visited = [False]*(n+1);

        for i in range(1, n+1):
            if infected[i]:
                q.append(i)
                visited[i] = True

        new_infected = [] #이번 BFS에서 새롭게 감염된 노드만 따로 저장할 리스트

        while q:
            cur = q.popleft()

            for next_node, edge_type in graph[cur]:
                if edge_type != pipe_type: continue
                if visited[next_node]: continue

                new_infected.append(next_node)
                infected[next_node] = True
                visited[next_node] = True
                q.append(next_node)

        return new_infected

    def dfs(depth):
        nonlocal answer 

        if depth == k:
            cnt = sum(infected) # 현재 감염된 개수 확인 후 갱신
            if cnt > answer:
                answer = cnt

            return

        for pipe_type in range(1, 4):
            new_infected = spread(pipe_type)

            dfs(depth+1)

            for node in new_infected:
                infected[node] = False
    
    dfs(0)
    
    return answer


    