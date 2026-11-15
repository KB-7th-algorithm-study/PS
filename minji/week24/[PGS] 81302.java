// [PGS] 81302 거리두기 확인하기
class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = isSafe(places[i]) ? 1 : 0;
        }
        return answer;
    }

    private boolean isSafe(String[] room) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (at(room, r, c) != 'P') continue;

                for (int d = 0; d < 4; d++) {
                    // 맨해튼 거리 1: 바로 옆에 앉으면 위반
                    if (at(room, r + DR[d], c + DC[d]) == 'P') return false;
                    // 맨해튼 거리 2 (직선): 사이 칸이 파티션이 아니면 위반
                    if (at(room, r + DR[d] * 2, c + DC[d] * 2) == 'P'
                            && at(room, r + DR[d], c + DC[d]) != 'X') return false;
                }

                // 맨해튼 거리 2 (대각선): 거쳐 가는 두 칸 중 하나라도 파티션이 아니면 위반
                for (int dr = -1; dr <= 1; dr += 2) {
                    for (int dc = -1; dc <= 1; dc += 2) {
                        if (at(room, r + dr, c + dc) == 'P'
                                && (at(room, r + dr, c) != 'X' || at(room, r, c + dc) != 'X')) return false;
                    }
                }
            }
        }
        return true;
    }

    // 대기실 밖은 파티션으로 취급
    private char at(String[] room, int r, int c) {
        if (r < 0 || r >= 5 || c < 0 || c >= 5) return 'X';
        return room[r].charAt(c);
    }
}
