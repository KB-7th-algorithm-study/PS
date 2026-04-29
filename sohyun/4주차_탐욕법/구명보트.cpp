#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0, left = 0;
    sort(people.begin(), people.end());
    
    for (int right = people.size() - 1; left <= right; right--) {
        if (people[left] + people[right] <= limit) {
            left++;
        }
        answer++;
    }
    
    return answer;
}