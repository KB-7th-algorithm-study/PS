class Solution {
public:
    vector<vector<int>> result;
    vector<bool> visited;
    void dfs(vector<int>& nums, vector<int>& curArr) {
        result.push_back(curArr);
        for(int i = 0; i < nums.size(); i++){
            if(!visited[i]){
                if(curArr.empty() || curArr.back() < nums[i]){
                    visited[i] = true;
                    curArr.push_back(nums[i]);
                    dfs(nums, curArr);
                    curArr.pop_back();
                    visited[i] = false;
                }
            }
        }
    }
    vector<vector<int>> subsets(vector<int>& nums) {
        visited.assign(nums.size(), false);
        vector<int> curArr;
        dfs(nums, curArr);
        return result;
    }
};