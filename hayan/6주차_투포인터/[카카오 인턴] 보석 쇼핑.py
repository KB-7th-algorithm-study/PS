def solution(gems):
    answer = []
    hash = {}
    s = {}
    
    for g in gems:
        s[g] = s.get(g,0)+1
    size = len(s)
    
    l = 0
    min_len = 100010
    st = 0
    ed = 0
    # len(hash) : 종류 개수 
    for r in range(len(gems)):
        hash[gems[r]] = hash.get(gems[r], 0) + 1
        if len(hash) < size: #아직 종류가 안채워졌으면 r++
            continue
        elif len(hash) == size:
            while hash[gems[l]]>1:
                hash[gems[l]] -= 1
                l+=1
            if r-l+1 < min_len:
                min_len = r-l+1
                st = l 
                ed = r   
        
    answer.append(st+1)
    answer.append(ed+1)
        
    return answer