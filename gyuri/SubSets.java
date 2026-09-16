import java.io.*;
import java.util.*;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);

        return answer;
    }

    public void dfs(int start, int[] nums) {
        answer.add(new ArrayList<>(path));

        for(int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i+1, nums);
            path.remove(path.size()-1);
        }
    }
}