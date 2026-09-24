/*
리팩토링
 */

import java.util.*;

class Solution {
  public int solution(int[][] jobs){
    Arrays.sort (jobs, Comparator.comparingInt(o -> o[0]));

    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (o1,o2) -> {
          if (o1[1] != o2[1]){
            return Integer.compare(o1[1], o2[1]);
          }

          if (o1[0] != o2[0]) {
            return Integer.compare(o1[0], o2[0]);
          }

          return Integer.compare(o1[2], o2[2]);
        });

    int idx = 0;
    int currentTime = 0;
    int totalTime = 0;

    while (idx < jobs.length || !pq.isEmpty()){

      while (idx < jobs.length && jobs[idx][0] <= currentTime){
        pq.offer(new int[]{
            jobs[idx][0],
            jobs[idx][1],
            idx
        });
        idx++;
      }

      //실행 가능한 작업이 없는 경우 다음 작업 요청 시간으로 이동
      if (pq.isEmpty()){
        currentTime = jobs[idx][0];
        continue;
      }

      int[] job = pq.poll();
      currentTime += job[1];
      totalTime += currentTime - job[0];
    }

    return totalTime/jobs.length;
  }
}

/*
1차 답안
 */

//import java.util.*;
//
//class Solution {
//  public class Task {
//    int num;
//    int rq_time;
//    int time;
//
//    public Task(int num, int rq_time, int time){
//      this.num = num;
//      this.rq_time = rq_time;
//      this.time = time;
//    }
//  }
//
//  public int solution(int[][] jobs) {
//    //우선순위 큐 정렬 기준 1.소요시간 2.작업 요청 시간 3.번호
//    PriorityQueue<Task> pq = new PriorityQueue<>(
//        (o1, o2) -> {
//          if (o1.time != o2.time){
//            return Integer.compare(o1.time, o2.time);
//          }
//
//          if (o1.rq_time != o2.rq_time){
//            return Integer.compare
//                (o1.rq_time, o2.rq_time);
//          }
//
//          return Integer.compare(o1.num, o2.num);
//        });
//
//    //초 순서대로 정렬
//    Arrays.sort(jobs, (o1, o2) -> {
//      return Integer.compare(o1[0],o2[0]);
//    });
//
//    //각 걸리는 시간 담을
//    int[] time = new int[jobs.length];
//
//    int idx = 0;
//    int num = 0;
//    int end_time = 0;
//
//    for (int sec = 0; ; sec++){
//
//      //더이상 실행할 작업 없음
//      if (idx >= jobs.length && pq.isEmpty()){
//        int sum = 0;
//        for (int i = 0; i < time.length; i++){
//          sum += time[i];
//        }
//        return (int)sum/time.length;
//      }
//
//      //같은 초에 들어오는 작업들 큐에 담기
//      while(idx < jobs.length && jobs[idx][0] == sec){
//        pq.add(new Task(num++, sec, jobs[idx][1]));
//        idx++;
//      }
//
//      //1. 대기 큐가 비어있지 않으면
//      //2. 다른 작업 종료 시간이 됐으면
//      //작업 하나 꺼내서 실행
//      if (!pq.isEmpty() && end_time <= sec){
//        Task task = pq.poll();
//        //작업 종료 시간 저장
//        end_time = sec + task.time;
//        time[task.num] = end_time - task.rq_time;
//        continue;
//      }
//      // //그렇지 않으면
//      // end_time++;
//    }
//  }
//}
