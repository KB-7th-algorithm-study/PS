#include <string>
#include <vector>
using namespace std;

string solution(string new_id) {
    string answer = "";
    // 1단계
    for(int i = 0; i < new_id.length(); i++){
        answer += tolower(new_id[i]);
    }
    // 2단계
    string s2 = "";
    for(char c : answer) {
        if(islower(c) || isdigit(c) || c == '-' || c == '_' || c == '.') {
            s2 += c;
        }
    }
    answer = s2;
    // 3단계
    string s3 = "";
    for (char c : answer) {
        if (c == '.') {
            if (!s3.empty() && s3.back() == '.') {
                continue;
            }
        }
        s3 += c;
    }
    answer = s3;
    // 4단계
    if (!answer.empty() && answer.front() == '.') {
        answer.erase(0, 1);
    }
    if (!answer.empty() && answer.back() == '.') {
        answer.pop_back();
    }
    // 5단계
    if(answer.empty()){
      answer = "a";
    }
    // 6단계
    if (answer.length() >= 16) {
        answer = answer.substr(0, 15);
    }
    if (!answer.empty() && answer.back() == '.') {
        answer.pop_back();
    }
    // 7단계
    while (answer.length() < 3) {
        answer += answer.back();
    }
    return answer;
}