#include <string>
#include <vector>
using namespace std;

vector<int> solution(vector<int> sequence, int k) {
    vector<int> answer(2);
    int right = 0, left = 0;
    int curSum = 0, minLen = sequence.size() + 1;

    for (left = 0; left < sequence.size(); left++) {
        curSum += sequence[left];
        // 현재 합이 목표를 넘었을때 && right, left 조건 만족할 때
        while (curSum > k && right <= left) {
            curSum -= sequence[right];
            right++;
        }
        // 현재 합이 목표와 같을 때, 최소 길이 확인하기
        if (curSum == k) {
            int curLen = left - right;
            if (curLen < minLen) { 
                minLen = curLen;
                answer[0] = right;
                answer[1] = left;
            }
        }
    }

    return answer;
}