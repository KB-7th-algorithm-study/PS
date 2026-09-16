class Solution {

  public int calculate(int storey) {

    //0이 되면 계산 끝
    if (storey == 0) return 0;

    //자릿수
    int digit = checkDigit(storey);

    //자릿수가 2이면 10과 100 중 더 가까운 값을 확인한다.
    int compare1 = (int)Math.pow(10, digit - 1);
    int compare2 = (int)Math.pow(10, digit);

    int abs = Math.abs(storey);
    if (Math.abs(compare1 - abs) < Math.abs(compare2 - abs)){
      if (storey > 0) return 1 + calculate(storey - compare1);
      else return 1 + calculate(storey + compare1);
    }
    else {
      if (storey > 0) return 1 + calculate(storey - compare2);
      else return 1 + calculate(storey + compare2);
    }
  }

  //자릿수 체크
  public int checkDigit(int storey) {
    if ((storey / 10) == 0) return 1;

    return 1 + checkDigit(storey / 10);
  }


  public int solution(int storey) {
    return calculate(storey);
  }
}


//최적화 재시도 코드
//class Solution {
//  public int solution(int storey) {
//    int answer = 0;
//
//    while (storey > 0) {
//      int current = storey % 10;       // 현재 자리
//      int next = (storey / 10) % 10;   // 다음 자리
//
//      if (current < 5) {
//        // 아래로 내려가는 게 이득
//        answer += current;
//        storey /= 10;
//
//      } else if (current > 5) {
//        // 위로 올라가는 게 이득
//        answer += 10 - current;
//        storey = storey / 10 + 1;
//
//      } else {
//        // current == 5
//        answer += 5;
//
//        if (next >= 5) {
//          storey = storey / 10 + 1;
//        } else {
//          storey /= 10;
//        }
//      }
//    }
//
//    return answer;
//  }
//}
