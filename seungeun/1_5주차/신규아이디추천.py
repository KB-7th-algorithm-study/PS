def solution(new_id):
    # 1단계
    new_id = new_id.lower()

    # 2단계
    temp = ''
    for ch in new_id:
        if ch.islower() or ch.isdigit() or ch in ['-', '_', '.']:
            temp += ch

    # 3단계
    result = ''
    prev = ''

    for ch in temp:
        if ch == '.' and prev == '.':
            continue

        result += ch
        prev = ch

    # 4단계
    if result and result[0] == '.':
        result = result[1:]

    if result and result[-1] == '.':
        result = result[:-1]

    # 5단계
    if result == '':
        result = 'a'

    # 6단계
    result = result[:15]

    if result[-1] == '.':
        result = result[:-1]

    # 7단계
    while len(result) < 3:
        result += result[-1]

    return result