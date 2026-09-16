def solution(board, moves):
    answer = 0
    basket = []

    for move in moves:
        col = move - 1

        for row in range(len(board)):
            if board[row][col] != 0:
                doll = board[row][col]

                board[row][col] = 0

                if basket and basket[-1] == doll:
                    basket.pop()
                    answer += 2
                else:
                    basket.append(doll)

                break
    return answer