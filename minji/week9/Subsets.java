package PS.minji.week9;
import java.util.*;

public class Subsets {
    class Solution {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            subset(nums, 0);
            return answer;
        }

        public void subset(int[] nums, int index) {
            if(index == nums.length) {
                answer.add(new ArrayList<>(path));
                return;
            }

            path.add(nums[index]);
            subset(nums, index+1);
            path.remove(path.size()-1);

            subset(nums, index+1);
        }
    }
}
