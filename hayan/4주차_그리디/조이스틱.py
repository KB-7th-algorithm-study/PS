def solution(name):
    answer = 0
    
    #알파벳 처리 먼저
    for n in name:
        if n < 'N':
            answer += (ord(n)-ord('A'))
        else:
            answer += (ord('Z') - ord(n)+1)
    
    #양옆 이동
    #쭉 가는거 기본 
    min_val = len(name)-1
    for i in range(len(name)):
        next_i = i+1
        while next_i<len(name) and name[next_i]=='A':
            next_i +=1
        a = i+i+len(name)-next_i 
        b = (len(name)-next_i)*2 + i
        min_val = min(min_val, min(a, b))
        
    answer += min_val
    
    return answer