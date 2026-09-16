#include <string>
#include <vector>

using namespace std;
bool visited[201];

void dfs(int x, vector<vector<int>>& computers){
    visited[x] = true;
    for(int i = 0; i < computers[x].size(); i++){
        if(computers[x][i] && !visited[i]){
            dfs(i, computers);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    for(int i = 0; i < n; i++){
        if(!visited[i]) answer++;
        dfs(i, computers);
    }

    return answer;
}