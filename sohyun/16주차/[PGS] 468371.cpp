// [프로그래머스] 노란불 신호등: C++
#include <string>
#include <vector>
#include <numeric>

using namespace std;

int solution(vector<vector<int>> signals) 
{
    long long limit = 1;

    for(const auto& v : signals) {
        long long cycle = v[0] + v[1] + v[2];
        limit = std::lcm(limit, cycle);
    }

    for(long long t = 1; t <= limit; t++) {
        bool all_yellow = true;
        
        for(const auto v : signals) {
            long long g = v[0];
            long long y = v[1];
            long long cycle = g + y + v[2];
            long long rem = (t - 1) % cycle;
            
            if (!(g <= rem && rem < g + y)) {
                all_yellow = false;
                break;
            }
        }
        
        if(all_yellow) {
            return t;
        }
    }
    return -1;
}