def solution(n, times):
    
    left = 1
    right = max(times) * n
    answer = right
    
    while left <= right:
        mid = (left + right) // 2
        
        # mid 시간 동안 처리 가능한 사람 수
        total = 0
        for t in times:
            total += mid // t
        
        # n명 이상 처리 가능
        if total >= n:
            answer = mid
            right = mid - 1
        
        # 아직 시간 부족
        else:
            left = mid + 1
    
    return answer