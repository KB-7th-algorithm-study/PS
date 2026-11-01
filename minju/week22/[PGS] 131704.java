import java.util.*;

class Solution {
  public int solution(int[] order) {
    Deque<Integer> stack = new ArrayDeque<>();

    int pointer = 1;
    int result = 0;

    for (int target : order){

      //원하는 상자가 나올 때까지 보조 벨트에 보관
      while (pointer <= order.length &&
          pointer < target){
        stack.push(pointer++);
      }

      //기존 벨트에서 바로 꺼낼 수 있는 경우
      if (pointer == target){
        pointer++;
      }


      //보조 벨트에서 꺼낼 수 있는 경우
      else if (!stack.isEmpty()
          && stack.peek() == target){
        stack.pop();
      }

      else {
        break;
      }

      result++;
    }

    return result;
  }
}
