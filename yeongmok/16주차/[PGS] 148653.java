class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int current = storey % 10;        // 현재 자릿수
            int next = (storey / 10) % 10;   // 다음 자릿수

            if (current > 5 || (current == 5 && next >= 5)) {
                answer += 10 - current;
                storey = storey / 10 + 1; // 올림
            } else {
                answer += current;
                storey /= 10; // 내림
            }
        }

        return answer;
    }
}