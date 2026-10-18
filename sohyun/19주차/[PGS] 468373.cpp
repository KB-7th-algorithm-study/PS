// https://school.programmers.co.kr/learn/courses/30/lessons/468373
// [프로그래머스] 바이러스 파이프 : C++

#include <string>
#include <vector>
#include <queue>

using namespace std;

int dfs(int n, int infection, int k, vector<int>& pipes, vector<vector<pair<int, int>>>& edges) {
    int result = 0;
    if (pipes.size() == k){ // 선택한 파이프의 개수가 k개에 도달했을 때 감염 시뮬 실행
        int cur;
		vector<bool> visit(n + 1, false);
		vector<int> inf;

		visit[infection] = true;
		inf.push_back(infection);
        
        // 선택된 pipes를 순서대로 감염을 확산
		for (int i = 0; i < k; i++)
		{
			vector<int> next;
			queue<int> q;

			for (int j = 0; j < inf.size(); j++)
				q.push(inf[j]);

			while (!q.empty())
			{
				cur = q.front();
				q.pop();
				next.push_back(cur);

				for (int j = 0; j < edges[cur].size(); j++)
				{
					if ((edges[cur][j].second == pipes[i]) && !visit[edges[cur][j].first])
					{
						visit[edges[cur][j].first] = true;
						q.push(edges[cur][j].first);
					}
				}
			}
			inf = next;
		}
		return inf.size();
    }
    
    // 백트래킹으로 k개의 파이프 조합 -> 모든 경우의 수로 생성
	for (int i = 1; i < 4; i++)
	{
		pipes.push_back(i);
		result = max(result, dfs(n, infection, k, pipes, edges));
		pipes.pop_back();
	}

	return result;
}

int solution(int n, int infection, vector<vector<int>> edges, int k) {
    int answer = 0;
    
    vector<int> pipe;
	vector<vector<pair<int, int>>> edges2(n + 1);
	
	for (int i = 0; i < edges.size(); ++i) { // 입력받은 간선 정보를 인접 리스트 -> 양방향 그래프로
		edges2[edges[i][0]].push_back(make_pair(edges[i][1], edges[i][2]));
		edges2[edges[i][1]].push_back(make_pair(edges[i][0], edges[i][2]));
	}
    answer = dfs(n, infection, k, pipe, edges2);
    return answer;
}