// https://school.programmers.co.kr/learn/courses/30/lessons/155651
// [프로그래머스] 호텔 대실 : C++ 
#include <string>
#include <vector>
#include <algorithm>
#include <cstdio>
using namespace std;

// "HH:MM" -> 분(int)으로 변환
int to_min(string s) {
    return stoi(s.substr(0, 2)) * 60 + stoi(s.substr(3, 2));
}

int solution(vector<vector<string>> book_time) {
    // 종료시간에 +10하기
    vector<pair<int, int>> times;
    for(int i = 0; i < book_time.size(); i++){
        int start = to_min(book_time[i][0]);
        
        int h = stoi(book_time[i][1].substr(0, 2));
        int m = stoi(book_time[i][1].substr(3, 2));
        int total_min = h * 60 + m + 10;
        times.push_back({start, total_min});
    }
    sort(times.begin(), times.end());
    
    // 현재 사용 중인 방들의 청소까지 완료한 시간을 저장할 벡터
    vector<int> rooms;
    
    for(int i = 0; i < times.size(); i++){
        int cur_start = times[i].first;
        int cur_end = times[i].second;
        
        bool assigned = false;
        
        // 기존에 사용 중인 방들 중에서 이어서 쓸 수 있는 방 있는지 확인
        for(int j = 0; j < rooms.size(); j++){
            if(rooms[j] <= cur_start){
                rooms[j] = cur_end;
                assigned = true;
                break;
            }
        }
        
        // 재사용할 수 있는 방이 없다면 새로운 방 개설
        if(!assigned){
            rooms.push_back(cur_end);
        }
    }
    
    return rooms.size();
}