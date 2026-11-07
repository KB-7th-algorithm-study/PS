// https://school.programmers.co.kr/learn/courses/30/lessons/67257
// [프로그래머스] 수식 최대화 : C++ 

#include <string>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

long long calculate(long long a, long long b, char op) {
    if (op == '+') return a + b;
    if (op == '-') return a - b;
    if (op == '*') return a * b;
    return 0;
}

long long solution(string expression) {
    long long answer = 0;
    vector<long long> num;
    vector<char> oper;
    string n = "";
    
    // 연산자, 숫자 구분해서 벡터에 담기
    for(int i = 0; i < expression.length(); i++){
        if(expression[i] == '-' || expression[i] == '+' || expression[i] == '*'){
            num.push_back(stoi(n));
            n = "";
            oper.push_back(expression[i]);
        }else n += expression[i];
    }
    num.push_back(stoi(n));
    
    // 순열조합
    vector<char> op_types = {'*', '+', '-'};
    sort(op_types.begin(), op_types.end());
    do {
        vector<long long> temp_num = num;
        vector<char> temp_oper = oper;
        
        // 우선순위가 높은 연산자부터 처리
        for(char op : op_types) {
            for(int i = 0; i < temp_oper.size(); ) {
                if(temp_oper[i] == op) {
                    long long res = calculate(temp_num[i], temp_num[i+1], op);
                    temp_num[i] = res;
                    temp_num.erase(temp_num.begin() + i + 1);
                    temp_oper.erase(temp_oper.begin() + i);
                } else i++;
            }
        }
        
        // 가장 큰 절댓값으로 정답 갱신
        answer = max(answer, abs(temp_num[0]));
        
    } while(next_permutation(op_types.begin(), op_types.end()));
    return answer;
}