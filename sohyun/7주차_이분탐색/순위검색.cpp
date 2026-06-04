#include <string>
#include <vector>
#include <unordered_map>
#include <sstream>
#include <algorithm>

using namespace std;

unordered_map<string, vector<int>> infoMap;

void makeCombinations(vector<string>& tokens, string key, int score, int idx) {
    if (idx == 4) {
        infoMap[key].push_back(score);
        return;
    }
    // 현재 인덱스의 조건을 포함하는 경우
    makeCombinations(tokens, key + tokens[idx], score, idx + 1);
    // 현재 인덱스의 조건을 '-'(상관없음)로 처리하는 경우
    makeCombinations(tokens, key + '-', score, idx + 1);
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;

    // info 파싱 및 16가지 조합 생성
    for (string& str : info) {
        stringstream ss(str);
        vector<string> tokens(4);
        int score = 0;
        
        ss >> tokens[0] >> tokens[1] >> tokens[2] >> tokens[3] >> score;
        
        makeCombinations(tokens, "", score, 0);
    }

    // 각 Key의 점수 vector를 정렬
    for (auto& pair : infoMap) {
        sort(pair.second.begin(), pair.second.end());
    }

    // query 파싱 및 조건에 맞는 지원자 수 계산
    for (string& q : query) {
        stringstream ss(q);
        string lang, job, career, food, and_word;
        int target_score = 0;
        
        ss >> lang >> and_word >> job >> and_word >> career >> and_word >> food >> target_score;
        
        // 검색용 Key 조합 생성
        string query_key = lang + job + career + food;
        // 해당 Key를 가진 지원자들의 점수 vector 가져오기
        auto& scores = infoMap[query_key];
        if (!scores.empty()) {
            // target_score 이상인 값이 처음으로 등장하는 반복자(iterator) 반환
            auto it = lower_bound(scores.begin(), scores.end(), target_score);
            // 기준 점수 이상인 사람의 수
            int count = scores.end() - it;
            answer.push_back(count);
        } else {
            // 조건에 만족하는 지원자 조합이 아예 없었던 경우
            answer.push_back(0);
        }
    }

    return answer;
}