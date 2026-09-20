// [프로그래머스] 디스크 컨트롤러: C++
#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct vectorCmp {
    bool operator()(vector<int> v1, vector<int> v2)
    {
        return v1[1] > v2[1];
    }
};

int solution(vector<vector<int>> jobs)
{
    int answer = 0, i = 0, time = 0;
    sort(jobs.begin(), jobs.end());
    priority_queue<vector<int>, vector<vector<int>>, vectorCmp> pq;

    while (i < jobs.size() || !pq.empty()) {
        if (jobs.size() > i && time >= jobs[i][0]) {
            pq.push(jobs[i++]);
            continue;
        }
        if (!pq.empty()) {
            time += pq.top()[1];
            answer += time - pq.top()[0];
            pq.pop();
        }
        else {
            time = jobs[i][0];
        }
    }
    return answer / jobs.size();
}