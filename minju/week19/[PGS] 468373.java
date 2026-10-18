import java.util.*;

class Solution {

  static int answer = 0;
  static int n;
  static int k;

  static ArrayList<int[]>[] graph;

  public int solution(int n, int infection, int[][] edges, int k) {

    this.n = n;
    this.k = k;

    graph = new ArrayList[n + 1];

    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] e : edges) {
      int a = e[0];
      int b = e[1];
      int type = e[2];

      graph[a].add(new int[]{b, type});
      graph[b].add(new int[]{a, type});
    }

    boolean[] infected = new boolean[n + 1];
    infected[infection] = true;

    dfs(0, infected);

    return answer;
  }

  static void dfs(int depth, boolean[] infected) {

    if (depth == k) {
      int cnt = 0;

      for (int i = 1; i <= n; i++) {
        if (infected[i]) cnt++;
      }

      answer = Math.max(answer, cnt);
      return;
    }

    for (int type = 1; type <= 3; type++) {

      boolean[] copy = infected.clone();

      bfs(copy, type);

      dfs(depth + 1, copy);
    }
  }

  static void bfs(boolean[] infected, int type) {

    ArrayDeque<Integer> q = new ArrayDeque<>();

    // 현재 감염된 애들 다 넣기
    for (int i = 1; i <= n; i++) {
      if (infected[i]) {
        q.offer(i);
      }
    }

    while (!q.isEmpty()) {

      int cur = q.poll();

      for (int[] next : graph[cur]) {

        int nextNode = next[0];
        int nextType = next[1];

        // 지금 연 파이프 아니면 패스
        if (nextType != type) continue;

        if (!infected[nextNode]) {
          infected[nextNode] = true;
          q.offer(nextNode);
        }
      }
    }
  }
}
