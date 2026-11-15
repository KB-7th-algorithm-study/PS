import java.util.*;

// [PGS] 67257 수식 최대화
class Solution {
    // 연산자 우선순위 6가지 (앞에 있을수록 먼저 계산)
    private static final String[] ORDERS = {"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"};

    public long solution(String expression) {
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        // 숫자와 연산자 분리
        int num = 0;
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                nums.add((long) num);
                ops.add(c);
                num = 0;
            }
        }
        nums.add((long) num);

        long answer = 0;
        for (String order : ORDERS) {
            answer = Math.max(answer, Math.abs(calculate(nums, ops, order)));
        }
        return answer;
    }

    private long calculate(List<Long> originNums, List<Character> originOps, String order) {
        List<Long> nums = new ArrayList<>(originNums);
        List<Character> ops = new ArrayList<>(originOps);

        for (char op : order.toCharArray()) {
            // 같은 연산자는 앞에서부터 계산
            int i = 0;
            while (i < ops.size()) {
                if (ops.get(i) == op) {
                    nums.set(i, apply(nums.get(i), nums.get(i + 1), op));
                    nums.remove(i + 1);
                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }
        return nums.get(0);
    }

    private long apply(long a, long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}
