package PS.minji.week9;
import java.util.*;

public class 메뉴_리뉴얼 {
    class Solution {
        // 빈 맵 만들기
        Map<String, Integer> count = new HashMap<>();
        // path 만들기
        StringBuilder path = new StringBuilder();
        // answer
        List<String> answer = new ArrayList<>();

        public String[] solution(String[] orders, int[] course) {
            // 주문을 돌면서
            for(String order : orders) {
                // 주문 정렬
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                String sorted = new String(arr);

                // course 길이를 돌면서
                for(int k : course) {
                    // 함수 호출 (정렬된 문자열, 만들 조합 길이, start)
                    combine(sorted, k, 0);
                }

            }

            for(int k : course) {
                int maxCnt = 0;
                for(String key : count.keySet()) {
                    if(key.length()==k) {
                        maxCnt = Math.max(maxCnt, count.get(key));
                    }
                }

                if(maxCnt < 2) {
                    continue;
                }

                for(String key : count.keySet()) {
                    if(key.length() == k && count.get(key)==maxCnt) {
                        answer.add(key);
                        Collections.sort(answer);
                    }
                }

            }
            return answer.toArray(new String[0]);
        }

        public void combine(String sorted, int k, int start) {
            // 종료 조건
            if(path.length() == k) {
                // getOrDefault 사용
                String key = path.toString();
                count.put(key, count.getOrDefault(key,0) +1);
                return;
            }

            for(int i=start; i<sorted.length(); i++) {
                path.append(sorted.charAt(i));
                combine(sorted, k, i+1);
                path.deleteCharAt(path.length()-1);
            }
        }


    }
}
