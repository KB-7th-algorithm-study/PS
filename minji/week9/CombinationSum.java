package PS.minji.week9;
import java.util.*;

public class CombinationSum {
    class Solution {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            // candidates, target 을 매개변수로 넘겨서 조합을 찾아오기
            combination(candidates, target, 0, 0);
            return answer;
        }

        public void combination(int[] candidates, int target, int start, int sum) {
            // 종료 조건1 : 타겟값이 된 경우 정답에 현재 조합을 추가
            if(sum == target) {
                answer.add(new ArrayList<>(path));
                return;
            }

            // 종료 조건2 : sum이 target보다 커지면 더 이상 합을 더 더할 필요가 없음
            if(sum > target) {
                return;
            }

            for(int i=start; i<candidates.length; i++) {
                path.add(candidates[i]);
                combination(candidates, target, i, sum + candidates[i]);
                path.remove(path.size()-1);
            }
        }
    }
}
