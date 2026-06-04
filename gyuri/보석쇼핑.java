import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] gems) {
        int range = Integer.MAX_VALUE; // 가장 짧은 범위
        int start = 0, end = 0; // 인덱스
        int answerStart = 0, answerEnd = 0; // 정답 인덱스

        Set<String> set = new HashSet<>();

        // 보석 종류
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        // 구간에 포함된 보석과 개수 저장
        Map<String, Integer> map = new HashMap<>();
        int type = 0;

        while(true) {
            // 보석 개수 모두 포함할 경우
            if(map.size() == set.size()) {
                // 현재 구간이 더 짧은 경우
                if(range > end - start) {
                    // 범위 갱신
                    range = end - start;

                    // 인덱스 새로 저장
                    answerStart = start;
                    answerEnd = end;
                }

                // 왼쪽 보석 제거
                map.put(gems[start], map.get(gems[start]) - 1);

                // 보석 개수가 0이면 map에서 제거
                if(map.get(gems[start]) == 0) map.remove(gems[start]);

                start++;

            } else {
                if(end == gems.length) break;

                // 보석 종류가 부족하면 오른쪽 보석 추가
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
                end++;
            }
        }

        return new int[]{answerStart+1, answerEnd};
    }
}