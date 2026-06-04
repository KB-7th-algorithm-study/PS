import java.util.*;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result;
        int s = 0;
        int e = numbers.length - 1;

        int sum = 0;
        while(true){
            sum = numbers[s] + numbers[e];
            if(sum == target) break;
            else if(sum > target) e--;
            else if(sum < target) s++;
        }

        result = new int[]{s + 1, e + 1};
        return result;
    }
}