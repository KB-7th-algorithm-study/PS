def solution(sequence, k):
    left = 0
    total = 0
    min_len = len(sequence)
    answer = []

    for right in range(len(sequence)):
        total += sequence[right]

        while total > k:
            total -= sequence[left]
            left += 1

        if total == k:
            if right - left < min_len:
                min_len = right - left
                answer = [left, right]

    return answer