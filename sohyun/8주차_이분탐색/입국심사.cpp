#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
    long long answer = 0;
    
    long long left = 1;
    long long right = n * (long long)times.back(); // 몰라서 찾아봄
    
    while (left <= right) {
        long long mid = (left + right) / 2;
        long long total = 0;
        
        for (int i = 0; i < times.size(); i++) { // mid시간동안 각 심사관이 몇 명 심사할 수 있는지
            total += (mid / times[i]);
        }
    
        if (total >= n) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    return answer;
}