// https://school.programmers.co.kr/learn/courses/30/lessons/160585
// [프로그래머스] 혼자서 하는 틱택토 : C++ 
#include <string>
#include <vector>

using namespace std;

int solution(vector<string> board) {
    int answer = 1, o = 0, x = 0, oWin = 0, xWin = 0;

    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
            if(board[i][j] == 'O'){
                o++;
            } else if(board[i][j] == 'X'){
                x++;
            }
        }
    }
    
    // 가로 췍
    for(int i = 0; i < 3; i++) {
        if ((board[i][0] != '.') && (board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
            if (board[i][0] == 'X')
                xWin++;
            else
                oWin++;
        }
    }
    // 세로 췍
    for(int i = 0; i < 3; i++) {
        if ((board[0][i] != '.') && (board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
            if (board[0][i] == 'X')
                xWin++;
            else
                oWin++;
        }
    }
    
    // 대각선 췍
     if (board[0][0]!='.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        if (board[0][0] == 'X')
            xWin++;
        else
            oWin++;
    }
    if (board[2][0]!='.' && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
        if (board[2][0] == 'X')
            xWin++;
        else
            oWin++;
    }  
    
    // 번갈아가면서 플레이 안 했을 때
    if(x - o > 1 || o - x > 1) answer = 0;
    
    // x가 먼저 놓여졌을 때
    if(x > o) answer = 0;
    
     // 우승자가 1명이 아닐 때
    if(oWin > 0 && xWin > 0) answer = 0;

    // 선공이 이겼을때 -> x+1이 o여야함.
    if(oWin > 0 && o != x+1)
        answer=0;
    
    // 후공이 이겼을때 -> x와o의 개수가 같아야함
    if(xWin > 0 && o != x)
        answer=0;
    
    return answer;
}