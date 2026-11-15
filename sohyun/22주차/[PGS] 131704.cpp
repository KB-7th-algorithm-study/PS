// https://school.programmers.co.kr/learn/courses/30/lessons/131704
// [프로그래머스] 택배 상자 : C++ 

#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<int> order) {
    int answer = 0;
    stack<int> box;
    
    for (int i = 1; i <= order.size(); i++) {
        box.push(i);
        while (!box.empty() && box.top() == order[answer]) {
            box.pop();
            answer++;
        }
    }
    
    return answer;
}