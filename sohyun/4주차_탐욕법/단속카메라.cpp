#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool compare(const vector<int>& a, const vector<int>& b) {
    return a[1] < b[1];
}

int solution(vector<vector<int>> routes) {
    int answer = 0;
    sort(routes.begin(), routes.end(), compare);
    for(int i = 0; i < routes.size(); i++){
      int start = routes[i][1];
      answer++;
      for(int j = i; j < routes.size()-1; j++){
        if(routes[i+1][0] <= start){
            i++;
            continue;
        }else break;
      }
    }
    return answer;
}