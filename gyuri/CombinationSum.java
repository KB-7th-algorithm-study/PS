import java.io.*;
import java.util.*;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, 0);

        return answer;
    }

    public void dfs(int[] candidates, int target, int start, int sum) {
        // 성공
        if(sum == target) {
            answer.add(new ArrayList<>(path));
        }

        // 실패
        if(sum > target) {
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, target, i, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}