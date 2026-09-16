class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        dfs(0, new ArrayList<>(), answer, nums);

        return answer;
    }
    public void dfs(int start, List<Integer> curr, List<List<Integer>> answer, int[] nums){
        answer.add(new ArrayList<>(curr));

        for(int i=start; i<nums.length; i++){
            curr.add(nums[i]);
            dfs(i+1, curr, answer, nums);
            curr.remove(curr.size()-1);
        }
    }
}