// https://school.programmers.co.kr/learn/courses/30/lessons/42895
// [프로그래머스] N으로 표현 : Java
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // 5 1개 -> 5
        // 5 2개 -> 55, 5+5, 5-5, 5*5, 5/5
        // 5 3개 -> 555,
        //          55+5, 55-5, 55*5, 55/5,
        //          (5+5)+5, (5+5)-5, (5+5)*5, (5+5)/5
        //          (5-5)+5, (5-5)-5, (5-5)*5, (5-5)/5
        //          (5*5)+5, (5*5)-5, (5*5)*5, (5*5)/5
        //          (5/5)+5, (5/5)-5, (5/5)*5, (5/5)/5
        //          5+(), 5-(), 5*(), 5/()
        // ...

        List<Set<Integer>> availableNums = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            availableNums.add(new HashSet<>());
        }

        // N을 1개 ~ 8개 사용할 때 만들 수 있는 숫자들을 저장
        int repeatedN = 0;
        for (int i = 1; i < 9; i++) {
            Set<Integer> numSet = availableNums.get(i);
            repeatedN = repeatedN * 10 + N;
            numSet.add(repeatedN);

            for (int j = 1; j < i; j++) {
                for (int num1: availableNums.get(j)) {
                    for (int num2: availableNums.get(i - j)) {
                        numSet.add(num1 + num2);
                        numSet.add(num1 - num2);
                        numSet.add(num1 * num2);

                        if (num2 != 0) {
                            numSet.add(num1 / num2);
                        }
                    }
                }
            }

            // N의 개수를 순차적으로 늘려가면서 현재 set에 number가 존재하는지 확인
            if (numSet.contains(number)) {
                return i;
            }
        }

        return -1;
    }
}