import java.util.*;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> array = new ArrayList<>();
        util(candidates, target, 0, 0, array);
        return answer;
    }
    void util(int[] candidates, int target, int n, int current, List<Integer> array){
        if(n > target) return;
        else if(n == target){
            answer.add(array);
            return;
        }else{
            for(int i = 0; i < candidates.length; i++){
                int now = candidates[i];
                if(now >= current){
                    array.add(now);
                    util(candidates, target, n + now, now, new ArrayList(array));
                    array.remove(array.size() - 1);
                }
            }
        }
    }
}