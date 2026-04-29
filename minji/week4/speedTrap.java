package PS.minji.week4;
import java.util.Arrays;
public class speedTrap {
        public int solution(int[][] routes) {
            int answer = 0;

            int camera = -30001;

            // "종료시간"이 빠른 순서대로(오름차순) 정렬하고 싶다면? (람다식 활용)
            Arrays.sort(routes, (a, b) -> {
                if (a[1] == b[1]) { // 만약 종료시간이 같다면
                    return a[0] - b[0]; // 시작시간이 빠른 순으로 정렬
                }
                return a[1] - b[1]; // 기본적으로는 종료시간이 빠른 순으로 정렬
            });

            for(int[] car : routes) {
                if(car[0] > camera) {
                    answer++;
                    camera = car[1];
                }
            }
            return answer;
    }
}
