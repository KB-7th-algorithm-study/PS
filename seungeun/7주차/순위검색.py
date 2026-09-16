from collections import defaultdict
from bisect import bisect_left

def solution(info, query):
    
    dic = defaultdict(list)
    
    # 모든 조합 저장
    for i in info:
        parts = i.split()
        conditions = parts[:-1]
        score = int(parts[-1])
        
        def dfs(depth, path):
            
            if depth == 4:
                key = ''.join(path)
                dic[key].append(score)
                return
            
            # 실제 값
            dfs(depth + 1, path + [conditions[depth]])
            # -
            dfs(depth + 1, path + ['-'])
        
        dfs(0, [])
    
    # 이분탐색 위해 정렬
    for key in dic:
        dic[key].sort()
    
    answer = []
    
    # query 처리
    for q in query:
        q = q.replace(" and ", " ")
        parts = q.split()
        key = ''.join(parts[:-1])
        target = int(parts[-1])
        scores = dic[key]
        
        # target 이상 처음 위치
        idx = bisect_left(scores, target)
        answer.append(len(scores) - idx)
    
    return answer