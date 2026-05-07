stack = []
answer = 0

def solution(board, moves):
    
    n = len(board)
    
    for m in moves:
        for i in range(n):
            if board[i][m-1] != 0:
                stack_pop(board[i][m-1])
                board[i][m-1]=0
                break
            
    return answer

def stack_pop(t):
    global answer
    
    if stack and stack[-1] == t:
        stack.pop()
        answer += 2
    else:
        stack.append(t)