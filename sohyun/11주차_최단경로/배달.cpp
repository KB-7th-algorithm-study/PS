#include <vector>
#include <queue>
using namespace std;
const int INF = 1e9;

int solution(int N, vector<vector<int> > road, int K) {
    int answer = 0;
    vector<vector<pair<int, int>>> graph(N + 1);
    // {누적 비용, 마을 번호} 형태로 넣을 때 비용이 작은 것부터 정렬되도록
    // priority_queue< [자료형], [구현체], [비교연산자] >
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    // 최단거리
    vector<int> dist(N + 1, INF);
    
    for (vector<int> r : road) {
        // graph[출발마을] = { {도착마을, 비용}, {도착마을, 비용}, ... }
        int u = r[0];
        int v = r[1];
        int w = r[2];
        graph[u].push_back({v, w});
        graph[v].push_back({u, w});
    }
    // 시작점
    dist[1] = 0;
    pq.push({0, 1});

    while (!pq.empty()) {
        int curCost = pq.top().first;
        int cur = pq.top().second;
        pq.pop();
        
        // 이미 처리된 적 O && 지금 꺼낸 비용보다 더 짧은 경로 존재하면 패스
        if (dist[cur] < curCost) continue;
        
        // 현재 마을이랑 연결된 다른 이웃 마을 확인
        for (pair<int, int>  neighbor : graph[cur]) {
            int next = neighbor.first;
            int nextCost = curCost + neighbor.second;
            
            // 더 짧은 경로를 발견한 경우
            if (nextCost < dist[next]) {
                dist[next] = nextCost;
                pq.push({nextCost, next});
            }
        }
    }
    
    for (int i = 1; i <= N; i++) {
        if (dist[i] <= K) {
            answer++;
        }
    }
    return answer;
}