class Solution {
public:
    vector<vector<int>> result;
    void makeCombination(int target, int curSum, vector<int>& curCom, vector<int>& candidates, int startIndex){
        if(target < curSum){
            return;
        }
        else if(target == curSum){
            result.push_back(curCom);
            return;
        }
        else{
            for (int i = startIndex; i < candidates.size(); i++) {
            curCom.push_back(candidates[i]);
            makeCombination(target, curSum + candidates[i], curCom, candidates, i);
            curCom.pop_back();
        }
        }
    }
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> curCom;
        makeCombination(target, 0, curCom, candidates, 0);
        return result;
    }
};