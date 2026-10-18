class Solution {
  //빙고 확인
  boolean checkBingo(String[] board, char c){

    //가로
    for (int i = 0; i < 3; i++){
      if (board[i].charAt(0) == c
          && board[i].charAt(1) == c
          && board[i].charAt(2) == c){
        return true;
      }
    }

    //세로
    for (int i = 0; i < 3; i++){
      if (board[0].charAt(i) == c
          && board[1].charAt(i) == c
          && board[2].charAt(i) == c
      ){
        return true;
      }
    }

    // 대각선
    if (board[0].charAt(0) == c &&
        board[1].charAt(1) == c &&
        board[2].charAt(2) == c) {
      return true;
    }

    if (board[0].charAt(2) == c &&
        board[1].charAt(1) == c &&
        board[2].charAt(0) == c) {
      return true;
    }

    return false;
  }

  public int solution(String[] board) {
    int countO = 0;
    int countX = 0;

    //각각 개수 먼저 세기
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (board[i].charAt(j) == 'O'){
          countO++;
        }
        else if (board[i].charAt(j) == 'X'){
          countX++;
        }
      }
    }

    boolean Obingo = checkBingo(board, 'O');
    boolean Xbingo = checkBingo(board, 'X');

    System.out.println(Obingo + " " + Xbingo);
    if (Obingo && Xbingo) return 0;

    if (Obingo && countO == countX + 1) return 1;

    if (Obingo && countO == countX) return 0;

    if (Xbingo && countO == countX) return 1;

    if (Xbingo && countO != countX) return 0;

    if (countO > countX + 1) return 0;

    if (countX > countO) return 0;

    return 1;

  }
}
