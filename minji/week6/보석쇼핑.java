package PS.minji.week6;
import java.util.*;

public class 보석쇼핑 {
    class Solution {
        public int[] solution(String[] gems) {
            int left = 0;
            int minLen = gems.length + 1;

            int[] answer = new int[2];

            Set<String> gemTypes = new HashSet<>();

            for(String gem : gems) {
                gemTypes.add(gem);
            }

            int totalTypeCount = gemTypes.size();

            Map<String, Integer> gemsMap = new HashMap<>();

            for(int right=0; right<gems.length; right++) {
                String tempGem = gems[right];

                // right가 가리키는 보석을 map에 추가 (중복이면 숫자를 올리기)
                gemsMap.put(tempGem, gemsMap.getOrDefault(tempGem, 0) +1);

                // map에 들어있는 보석의 개수와 전체 보석의 종류 개수가 같아지면
                while(gemsMap.size() == totalTypeCount) {
                    // 현재 [left, right] 는 정답 후보
                    int length = right - left + 1;

                    // 길이를 minLen와 비교
                    if(minLen > length) {
                        minLen = length;
                        // 길이가 더 작다면 minLen를 갱신하고 정답도 갱신
                        answer[0] = left+1;
                        answer[1] = right+1;
                    }

                    String leftGem = gems[left];
                    gemsMap.put(leftGem, gemsMap.get(leftGem) - 1);

                    if(gemsMap.get(leftGem) == 0) {
                        gemsMap.remove(leftGem);
                    }

                    left++;

                }
            }
            return answer;
        }
    }
}
