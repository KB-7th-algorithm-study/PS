import java.util.*;

// [PGS] 42895 N으로 표현
class Solution {
    public int solution(int N, int number) {
        // dp.get(i): N을 i번 사용해서 만들 수 있는 수의 집합
        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(new HashSet<>()); // 0번 사용 (사용하지 않음)

        int repeated = 0;
        for (int i = 1; i <= 8; i++) {
            Set<Integer> cur = new HashSet<>();
            repeated = repeated * 10 + N;
            cur.add(repeated); // N, NN, NNN, ...

            // i번 = j번으로 만든 수 (연산) (i-j)번으로 만든 수
            for (int j = 1; j < i; j++) {
                for (int a : dp.get(j)) {
                    for (int b : dp.get(i - j)) {
                        cur.add(a + b);
                        cur.add(a - b);
                        cur.add(a * b);
                        if (b != 0) cur.add(a / b);
                    }
                }
            }

            if (cur.contains(number)) return i;
            dp.add(cur);
        }
        return -1; // 최솟값이 8보다 큼
    }
}
