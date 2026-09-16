import java.util.*;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    boolean[] visit;
    public List<List<Integer>> subsets(int[] nums) {
        visit = new boolean[nums.length];
        List<Integer> array = new ArrayList<>();
        util(0, nums, array);
        return answer;
    }
    void util(int now, int[] nums, List<Integer> array){
        if(now > nums.length) return;
        answer.add(array);
        for(int i = now; i < nums.length; i++){
            if(!visit[i]){
                array.add(nums[i]);
                visit[i] = true;
                util(i + 1, nums, new ArrayList(array));
                array.remove(array.size() - 1);
                visit[i] = false;
            }
        }
    }
    
}