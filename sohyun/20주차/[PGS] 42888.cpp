// https://school.programmers.co.kr/learn/courses/30/lessons/42888
// [프로그래머스] 오픈채팅방 : C++ 
#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    vector<string> actionArr;
    vector<string> uidArr;
    unordered_map<string, string> uidMap;
    
    for(int i = 0; i < record.size(); i++){
        int first = record[i].find(' '); // 첫번째 공백 찾기
        string action = record[i].substr(0, first);
        actionArr.push_back(action);

        string uid;
        if (action == "Leave") {
            uid = record[i].substr(first+1);
        }
        else {
            int second = record[i].find(' ', first+1); // 두번째 공백 찾기
            uid = record[i].substr(first+1, second-first-1);
            string nickname = record[i].substr(second+1);
            uidMap[uid] = nickname;
        }
        uidArr.push_back(uid);
    }
    for (int i = 0; i< record.size(); i++) {
        string result = "";
        result += uidMap[uidArr[i]]; // 현재 이름 넣기
        if (actionArr[i] == "Enter") { result +=  "님이 들어왔습니다."; }
        else if (actionArr[i] == "Leave") { result += "님이 나갔습니다."; }
        else if (actionArr[i] == "Change") { continue; }
        answer.push_back(result);
    }
    return answer;
}