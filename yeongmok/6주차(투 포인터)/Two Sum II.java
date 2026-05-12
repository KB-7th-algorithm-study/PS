class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] answer = new int[2];
        int sum = 0;
        int right = 0;
        int left = 0;

        Loop1: for(right = 1; right<numbers.length; right++){
            sum = 0;
            sum += numbers[right];

            for(left = 0;left<right; left++){
                sum += numbers[left];
                if (sum == target){
                    answer[0] = left+1;
                    answer[1] = right+1;
                    break Loop1;
                }
                sum -= numbers[left];
            }  
        }
        return answer;
    }
}