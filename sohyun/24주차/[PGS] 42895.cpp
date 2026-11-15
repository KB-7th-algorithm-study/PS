// https://school.programmers.co.kr/learn/courses/30/lessons/42895
// [프로그래머스] N으로 표현 : C++ 
#include <string>
#include <vector>
#include <unordered_set>
using namespace std;

int solution(int N, int number) {
    int answer = 0, start = 0;
    if(N == number) return 1;
    vector<unordered_set<int> > s(8);
    
    for(int i = 0; i < 8; i++){
        start = start * 10 + N;
        s[i].insert(start);
        for(int j = 0; j < i; j++){
            for(int k = 0; k < i; k++){
                if(j+k+1 != i) continue; // j+k+1 == k 일때만
                for(auto a:s[j]){
                    for(auto b:s[k]){
                        s[i].insert(a+b);
                        if(a-b > 0)s[i].insert(a-b); // 음수 제외
                        s[i].insert(a*b);
                        if(a/b > 0) s[i].insert(a/b);
                    }
                }
            }
        }
        if(s[i].find(number) != s[i].end()) return i+1;
    }
    return -1;
}