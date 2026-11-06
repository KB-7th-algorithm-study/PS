// https://school.programmers.co.kr/learn/courses/30/lessons/67257
// [프로그래머스] 수식 최대화 : Java
import java.util.*;

class Solution {
    public long solution(String expression) {
        // 연산자의 우선순위를 자유롭게 재정의하여, 만들 수 있는 가장 큰 숫자를 제출 (절대값 기준)
        // +, -, * 로 만들 수 있는 6가지 우선순위 기준 연산 결과값 저장
        
        List<Character> operators = List.of('+', '-', '*'); // 0, 1, 2
        boolean[] visited = new boolean[operators.size()];
        List<List<Integer>> priorities = new ArrayList<>(); // [[0, 1, 2], [0, 2, 1], ...]
        dfs(new ArrayList<>(), visited, operators.size(), priorities);
        
        List<long[]> myExp = new ArrayList<>(); // [{100, 1}, {200, 2}, {300, 1}, {500, 0}, {20, -1}]
        
        int startIndex = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (i == expression.length() - 1) {
                long num = Long.parseLong(expression.substring(startIndex));
                myExp.add(new long[] {num, -1});
            }
            
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                long num = Long.parseLong(expression.substring(startIndex, i));
                myExp.add(new long[] {num, operators.indexOf(c)});
                startIndex = i + 1;
            }
        }
        
        long answer = 0;
        for (List<Integer> priority: priorities) {
            List<long[]> expCpy = new ArrayList<>(myExp);
            for (int p: priority) {
                for (int i = 0; i < expCpy.size(); i++) {
                    if (expCpy.get(i)[1] == p) {
                        long newNum = calcNum(expCpy.get(i)[0], expCpy.get(i + 1)[0], expCpy.get(i)[1]);
                        expCpy.set(i, new long[] {newNum, expCpy.get(i + 1)[1]});
                        expCpy.remove(i + 1);
                        i--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(expCpy.get(0)[0]));
        }
        
        return answer;
    }
    
    private void dfs(List<Integer> path, boolean[] visited, int targetLen, List<List<Integer>> priorities) {
        if (path.size() == targetLen) {
            priorities.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 0; i < targetLen; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(i);
                dfs(path, visited, targetLen, priorities);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    private long calcNum(long num1, long num2, long operator) {
        if (operator == 0) {
            return num1 + num2;
        }
        if (operator == 1) {
            return num1 - num2;
        }
        if (operator == 2) {
            return num1 * num2;
        }
        
        return 0;
    }
}