// https://school.programmers.co.kr/learn/courses/30/lessons/138476
// [프로그래머스] 귤 고르기 : Java
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 개수가 많은 size부터 담으면 된다 (그리디)
        // k개의 개수만큼 채웠을 때, size 종류의 개수 구하기

        Map<Integer, Integer> sizeCntMap = new HashMap<>();
        List<Integer> sizes = new ArrayList<>();

        for (int t: tangerine) {
            if (!sizeCntMap.containsKey(t)) {
                sizes.add(t);
            }
            sizeCntMap.put(t, sizeCntMap.getOrDefault(t, 0) + 1);
        }

        Collections.sort(sizes, (a, b) -> {
            return sizeCntMap.get(b).compareTo(sizeCntMap.get(a));
        });

        int totalCnt = 0;
        int typeCnt = 0;
        while (totalCnt < k) {
            totalCnt += sizeCntMap.get(sizes.get(typeCnt));
            typeCnt++;
        }

        return typeCnt;
    }
}