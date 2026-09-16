#include <string>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

map<string, int> courseMap[11]; 
int maxCnt[11];

void dfs(string& order, string combStr, int idx, int depth){
    if (depth >= 2) {
        courseMap[depth][combStr]++;
        maxCnt[depth] = max(maxCnt[depth], courseMap[depth][combStr]);
    }
    
    for (int i = idx; i < order.length(); i++) {
        dfs(order, combStr + order[i], i + 1, depth + 1);
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    for (string order : orders) {
        sort(order.begin(), order.end()); 
        dfs(order, "", 0, 0);
    }
    
    for (int len : course) {
        if (maxCnt[len] < 2) continue; 
        
        for (auto it : courseMap[len]) {
            if (it.second == maxCnt[len]) {
                answer.push_back(it.first);
            }
        }
    }
    sort(answer.begin(), answer.end());
    return answer;
}