class Solution {

  //최대공약수
  int getGcd (int a, int b){
    while(b != 0){
      int r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  //최소공배수
  int getLcm(int a, int b){
    return (a / getGcd (a,b)) * b;
  }


  boolean checkYelloLight(int sec, int[] signal){
    int sum = signal[0] + signal[1] + signal[2];
    sec %= sum;

    //한 신호등의 주기를 나눈 나머지를 기준으로 계산
    if (sec <= signal[0]) return false;
    //초록불 시간보다는 크고 초록불 시간을 뺐을 때 노란색 시간보다 작아야함
    if (sec - signal[0] <= signal[1]) return true;
    return false;
  }

  public int solution(int[][] signals) {

    int lcm  = 0;

    for (int i = 1; i < signals.length; i++){

      //첫번째 두번째 신호등 주기 최소공배수 구하기
      if (lcm == 0){
        int sum1 = signals[i-1][0] + signals[i-1][1] + signals[i-1][2];
        int sum2 = signals[i][0] + signals[i][1] + signals[i][2];

        lcm = getLcm(sum1, sum2);
      }


      else {
        int sum = signals[i][0] + signals[i][1] + signals[i][2];
        lcm = getLcm(lcm, sum);
      }
    }

    //1초 ~ 모든 신호등이 다시 똑같아지는 시간 (최소공배수)
    for (int sec = 1; sec <= lcm; sec++){
      boolean check = true;
      for (int i = 0; i < signals.length; i++){
        if (!checkYelloLight(sec, signals[i])) {
          check = false;
          break;
        }
      }

      if (check) return sec;
    }
    return -1;
  }
}
