def solution(answers):
    answer = []
    
    std1 = [1,2,3,4,5]
    std2 = [2,1,2,3,2,4,2,5]
    std3 = [3,3,1,1,2,2,4,4,5,5]
    
    cnt1 = cnt2 = cnt3 = 0
    
    n = len(answers);
    
    for i in range(n):
        if answers[i] == std1[i%len(std1)]: cnt1+=1
        if answers[i] == std2[i%len(std2)]: cnt2+=1
        if answers[i] == std3[i%len(std3)]: cnt3+=1
    
    mx = max(cnt1, cnt2, cnt3)
    if cnt1 == mx:
        answer.append(1)
    if cnt2 == mx:
        answer.append(2)
    if cnt3 == mx:
        answer.append(3)
    
    return answer