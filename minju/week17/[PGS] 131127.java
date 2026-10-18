import java.util.*;

class Solution {
  //Map끼리 수량이 맞는지 체크
  public boolean isCorrect(Map<String,Integer> wantMap, Map<String,Integer> discountMap){


    for (Map.Entry<String,Integer> entry : wantMap.entrySet()){
      String key = entry.getKey();
      int value = entry.getValue();

      if (!discountMap.containsKey(key) || discountMap.get(key) < value) return false;
    }

    return true;
  }

  public int solution(String[] want, int[] number, String[] discount) {

    //정현이의 원하는 제품과 수량 Map
    Map<String,Integer> wantMap = new HashMap<>();
    for (int i = 0; i < want.length; i++){
      wantMap.put(want[i], number[i]);
    }

    Map<String, Integer> discountMap = new HashMap<>();

    int start = 0;
    int end = 9;

    int result = 0;

    for (int i = start; i <= end; i++){
      discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
    }

    if (isCorrect(wantMap,discountMap)) result++;

    while(end < discount.length - 1){

      //슬라이딩 윈도우
      String removedProduct = discount[start++];
      String addedProduct = discount[++end];

      discountMap.put(removedProduct, discountMap.get(removedProduct) - 1);
      discountMap.put(addedProduct, discountMap.getOrDefault(addedProduct, 0) + 1);

      if (isCorrect(wantMap, discountMap)) result++;

    }

    return result;
  }
}
