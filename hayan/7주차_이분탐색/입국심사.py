def solution(n, times):    
    l =1
    r =n*max(times)
    answer = r
    
    while l<=r:
        mid = (l+r)//2
        n_sum = 0
        
        for i in range(len(times)):
            n_sum += mid//times[i]
            
        if n_sum >= n: 
            answer = mid
            r = mid-1
        else:
            l = mid+1
            
    return answer