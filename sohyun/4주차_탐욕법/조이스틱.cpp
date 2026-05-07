#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(string name) {
    int answer = 0;
    int n = name.length();
    int minMove = n - 1;

    for (int i = 0; i < n; i++) {
        // 정방향, 역방향의 알파벳 변경 횟수 중 최소값을 이동횟수에 더하기
        answer += min(name[i] - 'A', 'Z' - name[i] + 1);
        
        // 여기부터 커서 이동횟수 구하기
        int next = i + 1; // i는 현재위치, next는 다음으로 갈 'A'가 아닌 지점의 인덱스 위치
        while (next < n && name[next] == 'A') {
            next++;
        }
        // i까지 갔다가 뒤로 돌아가는 경우와 바로 역방향으로 가는 경우 비교
        minMove = min(minMove, i + n - next + min(i, n - next));
    }

    return answer + minMove;
}