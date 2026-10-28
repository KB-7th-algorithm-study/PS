// https://school.programmers.co.kr/learn/courses/30/lessons/92341
// [프로그래머스] 주차 요금 계산 : C++ 

#include <string>
#include <vector>
#include <map>
#include <sstream>
#include <cmath>
using namespace std;

// "HH:MM" -> 자정 기준 총 minute으로
int timeToMinutes(const string& timeStr) {
    int h, m;
    char colon;
    stringstream ss(timeStr);
    ss >> h >> colon >> m;
    return h * 60 + m;
}

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    map<string, int> totalMinute; // 차량번호, 누적 주차 시간(분)
    map<string, int> inMap;       // 차량번호, 입차 시간(분)
    
    // 내역 파싱 및 주차 시간 누적
    for (const string& record : records) {
        stringstream ss(record);
        string timeStr, carNum, status;
        ss >> timeStr >> carNum >> status;
        
        int minutes = timeToMinutes(timeStr);
        
        if (status == "IN") {
            inMap[carNum] = minutes;
        } else if (status == "OUT") {
            int parkedTime = minutes - inMap[carNum];
            totalMinute[carNum] += parkedTime;
            inMap.erase(carNum);
        }
    }
    
    // 출차 내역이 없는 차량 -> 23:59 출차
    int endTime = 23 * 60 + 59;
    for (auto& pair : inMap) {
        int parkedTime = endTime - pair.second;
        totalMinute[pair.first] += parkedTime;
    }
    
    // 요금 정산
    int defaultTime = fees[0];
    int defaultFee = fees[1];
    int unitTime = fees[2];
    int unitFee = fees[3];
    
    for (auto& pair : totalMinute) {
        int totalTime = pair.second;
        int fee = defaultFee;
        
        if (totalTime > defaultTime) {
            int extraTime = totalTime - defaultTime;
            fee += ((extraTime + unitTime - 1) / unitTime) * unitFee;
        }
        answer.push_back(fee);
    }
    
    return answer;
}