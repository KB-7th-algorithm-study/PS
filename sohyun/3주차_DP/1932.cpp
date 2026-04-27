#include <iostream>
#include <vector>
using namespace std;

int main() 
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    int n = 0, tmp = 0, max = 0;
    cin >> n;
    vector<vector<int>> triangle(n);
    for(int i = 0; i < n; i++){
      for(int j = 0; j < i+1; j++){
        int sum = 0;
        cin >> tmp;
        if(i == 0){ // 제일 꼭대기 
           triangle[i].push_back(tmp);
           break;
        }
        if(j == 0){
          sum = tmp + triangle[i-1][j];
        }else if(j == i){
          sum = tmp + triangle[i-1][j-1];
        }else{
          sum = (triangle[i-1][j-1] > triangle[i-1][j]) ? triangle[i-1][j-1] : triangle[i-1][j];
          sum += tmp;
        }
        triangle[i].push_back(sum);
      }
    }
    
    // 최대값 찾기
    for(int i = 0; i < n; i++){
      if(triangle[n-1][i] > max)
        max = triangle[n-1][i];
    }
    
    cout << max << '\n';
    return 0;
}