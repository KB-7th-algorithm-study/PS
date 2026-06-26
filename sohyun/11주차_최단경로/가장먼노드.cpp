#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;
const int INF = 1e9;

int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    vector<vector<int>> graph(n+1);
    vector<int> dist(n + 1, INF);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    for(auto node : edge){
        int u = node[0];
        int v = node[1];
        graph[u].push_back(v);
        graph[v].push_back(u);
    }
    
    dist[1] = 0;
    pq.push({0,1});
    
    while(!pq.empty()){
        int curCost = pq.top().first;
        int cur = pq.top().second;
        pq.pop();
        if(dist[cur] < curCost) continue;
        for(int next : graph[cur]) {
            if(dist[next] > curCost + 1) {
                dist[next] = curCost + 1;
                pq.push({dist[next], next});
            }
        }
        
        
    }
    // 1번 노드 기준으로 최대 최단 거리 찾기
    int max = 0;
    for(int i = 1; i <= n; i++) {
        if(dist[i] != INF && dist[i] > max) {
            max = dist[i];
        }
    }
    
    // 최대 최단 거리를 가지는 노드 개수 카운트
    for(int i = 1; i <= n; i++) {
        if(dist[i] == max) {
            answer++;
        }
    }
    return answer;
}