package PS.sanghak.week9;

import java.util.ArrayList;
import java.util.List;

class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(nums, new ArrayList<>(),0, list);
        return list;
    }

    private void backtrack(int[] nums, List<Integer> current,int start, List<List<Integer>> list) {
        list.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums,current,i+1,list);
            current.remove(current.size()-1);
        }
    }
}