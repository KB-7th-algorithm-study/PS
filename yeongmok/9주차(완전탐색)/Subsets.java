import java.util.*;

class Solution {
    List<List<Integer>> answer;
    public List<List<Integer>> subsets(int[] nums) {
        answer = new ArrayList<>();
        dfs(nums,new ArrayList<>(),0);
        return answer;
    }

    void dfs(int[] nums, List<Integer> cur, int start){
        answer.add(new ArrayList<>(cur));

        for(int i=start;i<nums.length;i++){
            cur.add(nums[i]);
            dfs(nums,cur,i+1);
            cur.remove(cur.size()-1);
        }
        return;
    }
}