def solution(n, computers):
    parent = list(range(n))
    
    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]
    
    def union(a, b):
        ra = find(a)
        rb = find(b)
        
        if ra != rb:
            parent[rb] = ra
    
    for i in range(n):
        for j in range(n):
            if i != j and computers[i][j] ==1:
                union(i,j)
                
    return len(set(find(i) for i in range(n)))
    
    
    
    