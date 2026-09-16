class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)

        parent = list(range(n + 1))

        def find(x):
            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]

        def union(a, b):
            ra = find(a)
            rb = find(b)

            if ra == rb:
                return False

            parent[rb] = ra
            return True

        for u, v in edges:
            if not union(u, v):
                return [u, v]