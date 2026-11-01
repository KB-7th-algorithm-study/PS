// https://school.programmers.co.kr/learn/courses/15009/lessons/121690
// [프로그래머스] [PCCP 모의고사 2] 4번 : C++ 

#include <string>
#include <vector>
#include <queue>
using namespace std;

// 좌표+신발 사용 여부
struct State {
    int x, y, used;
};

int solution(int n, int m, vector<vector<int>> hole) {
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};
    
    // 3차원 배열: dist[x][y][신발사용여부]
    vector<vector<vector<int>>> dist(n + 1, vector<vector<int>>(m + 1, vector<int>(2, -1)));
    
    // 함정 표시
    vector<vector<int>> isHole(n + 1, vector<int>(m + 1, 0));
    for(int i = 0; i < hole.size(); i++){
        isHole[hole[i][0]][hole[i][1]] = 1;
    }

    queue<State> q;
    q.push({1, 1, 0}); // 시작점 (1,1), 신발 미사용(0)
    dist[1][1][0] = 0;

    while(!q.empty()){
        State curr = q.front();
        q.pop();

        if(curr.x == n && curr.y == m) {
            return dist[curr.x][curr.y][curr.used];
        }

        for(int i = 0; i < 4; i++){
            int nx = curr.x + dx[i];
            int ny = curr.y + dy[i];

            // 맵 안이고, 함정이 아니며, 해당 상태(used)로 방문한 적이 없을 때
            if(nx >= 1 && nx <= n && ny >= 1 && ny <= m && isHole[nx][ny] == 0) {
                if(dist[nx][ny][curr.used] == -1) {
                    dist[nx][ny][curr.used] = dist[curr.x][curr.y][curr.used] + 1;
                    q.push({nx, ny, curr.used});
                }
            }

            // 신발 사용하기
            if(curr.used == 0) {
                int jx = curr.x + dx[i] * 2;
                int jy = curr.y + dy[i] * 2;
                // 2칸 점프한 위치가 맵 안 + 함정이 아님 + 방문한 적 없을 때
                if(jx >= 1 && jx <= n && jy >= 1 && jy <= m && isHole[jx][jy] == 0) {
                    if(dist[jx][jy][1] == -1) { // 점프 후니까 used 1됨
                        dist[jx][jy][1] = dist[curr.x][curr.y][0] + 1;
                        q.push({jx, jy, 1});
                    }
                }
            }
        }
    }
    return -1;
}