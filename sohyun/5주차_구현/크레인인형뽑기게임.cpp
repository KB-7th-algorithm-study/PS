#include <string>
#include <vector>
#include <stack>
using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    stack<int> picked;
    for(int curMove : moves){
      for(int i = 0; i < board.size(); i++){
        if(board[i][curMove-1] != 0){
            int doll = board[i][curMove-1];
            board[i][curMove-1] = 0;
            if(!picked.empty() && (picked.top() == doll)){
                picked.pop();
                answer += 2;
            } else {
                picked.push(doll);
            }
            break; 
        }
      }

    }
    return answer;
}