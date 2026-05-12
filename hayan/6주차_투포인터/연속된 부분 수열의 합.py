def solution(sequence, k):
    answer = []
    
    st = 0
    ed = 0
    l = 0
    len_i = len(sequence)+1
    sum =0
    for r in range(len(sequence)):
        sum += sequence[r]
        
        while sum > k :
            sum -= sequence[l]
            l += 1
        
        if sum==k:
            if r-l+1 < len_i:
                len_i = r-l+1
                st=l
                ed = r
            
    answer.append(st)
    answer.append(ed)
        
    return answer