#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> network(101);
bool visited[101];
int cnt = 0;

void dfs(int x)
{
  int y = 0;
  visited[x] = true;
  for(int i = 0; i < network[x].size(); i++){
    y = network[x][i];
    if(!visited[y]){
      dfs(y);
      cnt++;
    }
  }
}

int main() 
{
    int com = 0, comPair = 0, c1 = 0, c2 = 0;
    
    cin >> com >> comPair;

    for(int i = 0; i < comPair; i++){
      cin >> c1 >> c2;
      network[c1].push_back(c2);
      network[c2].push_back(c1);
    }
    dfs(1);
    cout << cnt << '\n';
    return 0;
}