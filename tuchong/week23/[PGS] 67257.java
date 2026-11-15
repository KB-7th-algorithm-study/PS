// https://school.programmers.co.kr/learn/courses/30/lessons/67257
// [프로그래머스] 수식 최대화 : Java
import java.util.*;

class Solution {
    public long solution(String expression) {
        // 연산자의 우선순위를 자유롭게 재정의하여, 만들 수 있는 가장 큰 숫자를 제출 (절대값 기준)
        // +, -, * 로 만들 수 있는 6가지 우선순위 기준 연산 결과값 저장
        
        String[] priorities = new String[] {"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"};
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        long num = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                nums.add(num);
                ops.add(c);
                num = 0;
            } else {
                num = num * 10 + (c - '0');
            }
        }
        nums.add(num); // 마지막 숫자
        
        long answer = 0;
        for (String priority: priorities) {
            List<Long> numsCpy = new ArrayList<>(nums);
            List<Character> opsCpy = new ArrayList<>(ops);

            for (char p: priority.toCharArray()) {
                for (int i = 0; i < opsCpy.size(); i++) {
                    if (opsCpy.get(i) == p) {
                        long newNum = calcNum(numsCpy.get(i), numsCpy.get(i + 1), p);
                        numsCpy.set(i, newNum);
                        numsCpy.remove(i + 1);
                        opsCpy.remove(i);
                        i--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(numsCpy.get(0)));
        }
        
        return answer;
    }
    
    private long calcNum(long num1, long num2, char operator) {
        if (operator == '+') {
            return num1 + num2;
        }
        if (operator == '-') {
            return num1 - num2;
        }
        if (operator == '*') {
            return num1 * num2;
        }
        
        return 0;
    }
}