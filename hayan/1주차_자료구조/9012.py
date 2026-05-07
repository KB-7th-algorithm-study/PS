def VPS(str):
    stack = []
    for ch in str:
        if ch == '(':
            stack.append(ch)
        else:
            if stack:
                stack.pop()
            else:
                return "NO"
    if not stack: #비어있으면
        return "YES"
    else: #남아있으면 
        return "NO"


t = int(input())
while t:
    str = input()

    answer = VPS(str)
    
    print(answer)
    t -= 1
