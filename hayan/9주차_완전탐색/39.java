class Solution {
    List<List<Integer>> answer;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        answer = new ArrayList<>();

        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();

        dfs(0, target, list, candidates);
   
        return answer;
    }
    public void dfs(int start, int sum, List<Integer> list, int[] candidates){
        if(sum==0){
            answer.add(new ArrayList<>(list));
            return;
        }
        if(sum < 0){
            return;
        }
        for(int i=start; i<candidates.length; i++){
            list.add(candidates[i]);
            dfs(i, sum-candidates[i], list, candidates);
            list.remove(list.size()-1);
        }
    }
}