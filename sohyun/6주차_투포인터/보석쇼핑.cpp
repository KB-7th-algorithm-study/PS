#include <string>
#include <vector>
#include <set>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<string> gems) {
    vector<int> answer = {1, (int)gems.size()}; 

    set<string> kinds(gems.begin(), gems.end()); // 보석 종류 파악
    int kindsCnt = kinds.size();
    unordered_map<string, int> bucket; // <보석이름, 개수>
    int left = 0, minLen = gems.size() + 1;

    for (int right = 0; right < gems.size(); right++) {
        bucket[gems[right]]++; // 일단 보석 담기
        while(bucket.size() == kindsCnt){ // 만약 보석 종류를 다 모았다면
            if(right - left < minLen){
                minLen = right - left;
                answer = {left+1, right+1};
            }
            bucket[gems[left]]--; // 일단 left보석 빼보기
            if (bucket[gems[left]] == 0) { // 만약 뺀 보석의 개수가 0이 되면
                bucket.erase(gems[left]); // 장바구니에서 아예 제거
            }
            left++;
        }
    }
    
    return answer;
}