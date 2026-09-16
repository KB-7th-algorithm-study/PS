import java.util.*;

class Solution {
    List<List<Integer>> answer;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        answer = new ArrayList<>();
        dfs(candidates,target,new ArrayList<>(),0);
        return answer;       
    }
    void dfs(int[] candidates, int target, List<Integer> cur,int start){
        int sum=0;
        for(int i : cur){
            sum+=i;
        }
        if(sum==target){
            answer.add(new ArrayList<>(cur));
            return;
        }

        for(int i=start;i<candidates.length;i++){
            if(sum+candidates[i]<=target){
                cur.add(candidates[i]);
                dfs(candidates,target,cur,i);
                cur.remove(cur.size()-1);
            }
        }

        return;
    }
}
