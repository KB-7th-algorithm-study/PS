// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=2
// SW Expert Academy: 1873. 상호의 배틀필드 D3
#include <iostream>
#include <vector>
#include <string>
using namespace std;

// 상(0), 하(1), 좌(2), 우(3) 방향 배열
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };
char dirChar[4] = { '^', 'v', '<', '>' };

int getDir(char c) {
    if (c == '^') return 0;
    if (c == 'v') return 1;
    if (c == '<') return 2;
    return 3; // '>'
}


int main()
{
    int T = 0;
    cin >> T;

    for(int test_case = 1; test_case <= T; test_case++) {
        int H = 0, W = 0, N = 0, curX = -1, curY = -1;
        string str = "";
        cin >> H >> W;
        vector<string> arr(H);
        for (int i = 0; i < H; i++) {
            cin >> arr[i];
        }
        cin >> N >> str;

        // 전차 위치 찾기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (arr[i][j] == '<' || arr[i][j] == '>' || arr[i][j] == '^' || arr[i][j] == 'v') {
                    curX = i;
                    curY = j;
                    break;
                }
            }
            if (curX != -1) break;
        }
        
        // 명령 입력 받기
        for (int i = 0; i < N; i++) {
            char cmd = str[i];

            if (cmd == 'S') {
                // 현재 전차가 바라보는 방향 인덱스 가져오기
                int d = getDir(arr[curX][curY]);
                int nx = curX + dx[d];
                int ny = curY + dy[d];

                // 맵 범위 안에서 포탄 전진
                while (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                    if (arr[nx][ny] == '*') { // 벽돌 벽->부수고 평지로 만든 뒤 소멸
                        arr[nx][ny] = '.';
                        break;
                    }
                    else if (arr[nx][ny] == '#') { // 강철 벽
                        break;
                    }
                    // 평지('.')나 물('-')은 그대로 통과
                    nx += dx[d];
                    ny += dy[d];
                }
            }
            else {
                // 이동 명령 (U, D, L, R)
                int d = 0;
                if (cmd == 'U') d = 0;
                else if (cmd == 'D') d = 1;
                else if (cmd == 'L') d = 2;
                else if (cmd == 'R') d = 3;

                // 일단 방향부터 돌림
                arr[curX][curY] = dirChar[d];

                int nx = curX + dx[d];
                int ny = curY + dy[d];

                // 맵 범위 안이고 평지('.')라면 이동
                if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == '.') {
                    arr[curX][curY] = '.';      // 기존 자리는 평지로
                    arr[nx][ny] = dirChar[d];   // 새 자리에 전차 배치
                    curX = nx;
                    curY = ny;
                }
            }
        }

        // 출력
        cout << "#" << test_case << " ";
        for (int i = 0; i < H; i++) {
            cout << arr[i] << "\n";
        }
    }

    return 0;
}
