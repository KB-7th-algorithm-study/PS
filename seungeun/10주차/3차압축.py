def solution(msg):
    res = []
    dic = {chr(i + 64): i for i in range(1, 27)}
    next_index = 27
    w=""
    for c in msg:
        if w+c in dic:
            w = w+c
        else:
            res.append(dic[w])
            dic[w+c] = next_index
            next_index += 1
            w = c
    if w:
        res.append(dic[w])

    return res