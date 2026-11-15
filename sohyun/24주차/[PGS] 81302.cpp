// https://school.programmers.co.kr/learn/courses/30/lessons/81302
// [프로그래머스] 거리두기 확인하기 : C++ 
#include <string>
#include <vector>
#include <cstring>

using namespace std;

int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};
int N = 5;

int visit[5][5];

bool safe(int y, int x) {
    return y>=0 && x>=0 && y<N && x<N; 
}

bool dfs(vector<string> &place, int y, int x, int count) {
    if(count >= 3) return true;
    if(count > 0 && place[y][x] == 'P') return false; // 거리 2 이내에 사람 만나면 false
    
    for(int d=0; d<4; ++d) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        
        if(!safe(ny, nx) || visit[ny][nx]) continue;
        if(place[ny][nx] == 'X') continue;
        
        visit[ny][nx] = 1;
        if(!dfs(place, ny, nx, count+1)) return false;
        visit[ny][nx] = 0;    
    }
    
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    
    for(int p = 0; p < N; p++) {
        bool flag = true;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(places[p][i][j] == 'P') { // 사람 위치에서 탐색 시작
                    memset(visit, 0, sizeof(visit));
                    
                    visit[i][j] = 1;
                    if(!dfs(places[p], i, j, 0)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        
        answer.push_back(flag);
    }
    
    return answer;
}