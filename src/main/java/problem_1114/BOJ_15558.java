package problem_1114;

import java.util.*;
import java.io.*;

public class BOJ_15558 {

  static int n, k;
  static int[][] arr;
  static boolean[][] visited;
  static int ans = 0;
  static Queue<int[]> q = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    arr = new int[2][n];
    visited = new boolean[2][n];

    for (int i = 0; i < 2; i++) {
      String str = br.readLine();
      for (int j = 0; j < n; j++) {
        arr[i][j] = str.charAt(j) - '0';
      }
    }

    bfs();
    System.out.println(ans);
  }

  public static void bfs() {
    q.offer(new int[]{0, 0, 0});
    visited[0][0] = true;

    while (!q.isEmpty()) {
      int[] now = q.poll();
      int line = now[0];
      int pos = now[1];
      int time = now[2];

      if (tryMove(line, pos + 1, time + 1)) {
        return;
      }

      if (tryMove(line, pos - 1, time + 1)) {
        return;
      }

      if (tryMove(1 - line, pos + k, time + 1)) {
        return;
      }
    }
  }

  private static boolean tryMove(int nextLine, int nextPos, int time) {
    if (nextPos >= n) {
      ans = 1;
      return true;
    }
    if (nextPos >= 0 && !visited[nextLine][nextPos] && arr[nextLine][nextPos] == 1
        && nextPos >= time) {

      visited[nextLine][nextPos] = true;
      q.offer(new int[]{nextLine, nextPos, time});
    }
    return false;
  }
}