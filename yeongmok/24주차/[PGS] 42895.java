import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> countList = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            countList.add(new HashSet<>());
        }

        for (int i = 1; i <= 8; i++) {
            Set<Integer> countSet = countList.get(i);
            countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));

            for (int j = 1; j < i; j++) {
                for (int left : countList.get(j)) {
                    for (int right : countList.get(i - j)) {
                        countSet.add(left + right);
                        countSet.add(left - right);
                        countSet.add(left * right);

                        if (right != 0) {
                            countSet.add(left / right);
                        }
                    }
                }
            }

            if (countSet.contains(number)) {
                return i;
            }
        }

        return -1;
    }
}