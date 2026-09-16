#include <vector>
#include <algorithm>

using namespace std;

int inDegree[1000005];
int outDegree[1000005];

vector<int> solution(vector<vector<int>> edges) {
    int maxNode = 0;

    for (const auto& edge : edges) {
        int from = edge[0];
        int to = edge[1];
        
        outDegree[from]++;
        inDegree[to]++;
        
        maxNode = max({maxNode, from, to});
    }
    
    int createdNode = 0, donutCount = 0, barCount = 0, eightCount = 0;
    
    for (int i = 1; i <= maxNode; i++) {
        if (outDegree[i] >= 2 && inDegree[i] == 0) {
            createdNode = i;
        }
        else if (outDegree[i] == 0 && inDegree[i] > 0) {
            barCount++;
        }
        else if (outDegree[i] == 2 && inDegree[i] >= 2) {
            eightCount++;
        }
    }
    
    int totalGraphs = outDegree[createdNode];
    donutCount = totalGraphs - barCount - eightCount;
    
    return {createdNode, donutCount, barCount, eightCount};
}