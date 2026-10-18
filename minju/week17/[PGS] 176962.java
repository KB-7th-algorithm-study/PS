//최적화
import java.util.*;

class Solution {

  int convertTimeToMin(String time) {
    String[] t = time.split(":");
    return Integer.parseInt(t[0]) * 60
        + Integer.parseInt(t[1]);
  }

  public String[] solution(String[][] plans) {

    // 시작 시간 순 정렬
    Arrays.sort(plans, Comparator.comparing(plan -> plan[1]));

    // 중단된 과제 저장
    Deque<String[]> stack = new ArrayDeque<>();

    String[] answer = new String[plans.length];
    int idx = 0;

    for (int i = 0; i < plans.length - 1; i++) {

      String name = plans[i][0];
      int start = convertTimeToMin(plans[i][1]);
      int playTime = Integer.parseInt(plans[i][2]);

      int nextStart = convertTimeToMin(plans[i + 1][1]);

      // 다음 과제 시작 전까지 사용 가능한 시간
      int remain = nextStart - start;

      // 현재 과제를 끝낼 수 있는 경우
      if (playTime <= remain) {

        answer[idx++] = name;
        remain -= playTime;

        // 남는 시간 동안 가장 최근에 멈춘 과제부터 재개
        while (remain > 0 && !stack.isEmpty()) {

          String[] task = stack.pop();

          String stopName = task[0];
          int leftTime = Integer.parseInt(task[1]);

          // 중단 과제까지 끝낼 수 있음
          if (leftTime <= remain) {
            remain -= leftTime;
            answer[idx++] = stopName;
          }

          // 다시 중단해야 함
          else {
            stack.push(new String[]{
                stopName,
                String.valueOf(leftTime - remain)
            });

            remain = 0;
          }
        }
      }

      // 현재 과제를 끝내지 못함
      else {
        stack.push(new String[]{
            name,
            String.valueOf(playTime - remain)
        });
      }
    }

    // 마지막 과제는 방해할 다음 과제가 없으므로 무조건 완료
    answer[idx++] = plans[plans.length - 1][0];

    // 이후 중단된 과제를 최근 것부터 모두 완료
    while (!stack.isEmpty()) {
      answer[idx++] = stack.pop()[0];
    }

    return answer;
  }
}

//첫 시도
//import java.util.*;
//
//class Solution {
//
//  int convertTimeToMin(String time){
//
//    String[] t = time.split(":");
//    return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
//  }
//
//  int diffOfTime(String time1, String time2){
//    return convertTimeToMin(time1) - convertTimeToMin(time2);
//  }
//
//
//  public String[] solution(String[][] plans) {
//
//    //시작 시간 순서대로 정렬
//    Arrays.sort(plans, (o1, o2)->{
//      return o1[1].compareTo(o2[1]);
//    });
//
//    //시작 시간 순서대로 정렬
//    PriorityQueue<String[]> stopTask = new PriorityQueue<>(
//        Comparator.
//            comparing((String[] task) -> task[1])
//            .reversed()
//    );
//
//    String[] result = new String[plans.length];
//    int idx = 0;
//
//    int now = -1;
//    int next = -1;
//    int gap = -1;
//    int reqMin = -1;
//
//    for (int i = 0; i < plans.length; i++){
//
//      if (i == plans.length - 1){
//        result[idx++] = plans[i][0];
//        while(!stopTask.isEmpty()){
//          result[idx++] = stopTask.poll()[0];
//        }
//        continue;
//      }
//
//      now = convertTimeToMin(plans[i][1]);
//      next = convertTimeToMin(plans[i+1][1]);
//
//      gap = next - now;
//      reqMin = Integer.parseInt(plans[i][2]);
//
//      System.out.println(i + " " + gap);
//      //1. 만약 과제를 다 진행 가능한 상태라면
//      if (reqMin < gap){
//
//        result[idx++] = plans[i][0];
//
//        //과제 실행
//        now += reqMin;
//        gap = next - now;
//
//        //1-1. 멈춘 과제가 있다면 실행
//
//        while(!stopTask.isEmpty() && now < next){
//          String[] task = stopTask.poll();
//          int reqMin2 = Integer.parseInt(task[2]);
//
//          //멈춘 과제를 다 할 수 있는 경우
//          if (now + reqMin2 <= next){
//            result[idx++] = task[0];
//            now += reqMin2;
//            gap = next - now;
//          }
//
//          //멈춘 과제를 다 못하는 경우
//          else {
//            task[2] = String.valueOf(reqMin2 - gap);
//            stopTask.add(task);
//            now = next;
//          }
//        }
//      }
//
//      //2.시간이 딱 맞은 경우
//      else if (reqMin == gap) {
//        result[idx++] = plans[i][0];
//      }
//
//      //3.시간이 부족한 경우
//      else {
//        plans[i][2] = String.valueOf(reqMin - gap);
//        System.out.println(plans[i][0] + ""+ plans[i][2]);
//        stopTask.add(plans[i]);
//      }
//    }
//    return result;
//  }
//}
