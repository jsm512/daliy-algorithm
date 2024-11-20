package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_14499 {

  static int n, m, x, y, k;
  static int[][] map;

  //{바닥,동,서,남,북,위}
  static int[] dice = new int[7];
  //동서북남
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {1, -1, 0, 0};
  static StringBuilder sb = new StringBuilder();
  static Queue<Integer> q = new ArrayDeque<>();

  public static void main(String[] args) throws Exception {
    init();

    while (!q.isEmpty()) {
      int dir = q.poll();

      simulation(dir);
    }

    System.out.print(sb);
  }

  public static boolean isInline(int x, int y) {
    return x < 0 || y < 0 || x >= n || y >= m;
  }

  public static void simulation(int dir) {
    int nx = x + dx[dir];
    int ny = y + dy[dir];

    if (isInline(nx, ny)) {
      return;
    }

    moveDice(dir, nx, ny);
    x = nx;
    y = ny;
  }

  public static void moveDice(int dir, int x, int y) {
    int tmp = dice[2];
    switch (dir) {
      //동쪽
      case 0:
        dice[2] = dice[6];
        dice[6] = dice[4];
        dice[4] = dice[5];
        dice[5] = tmp;
        break;
      //서쪽
      case 1:
        dice[2] = dice[5];
        dice[5] = dice[4];
        dice[4] = dice[6];
        dice[6] = tmp;
        break;
      //북쪽
      case 2:
        dice[2] = dice[3];
        dice[3] = dice[4];
        dice[4] = dice[1];
        dice[1] = tmp;
        break;
      //남쪽
      case 3:
        dice[2] = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[3];
        dice[3] = tmp;
        break;
    }
    if (map[x][y] == 0) {
      map[x][y] = dice[4];
    } else {
      dice[4] = map[x][y];
      map[x][y] = 0;
    }
    sb.append(dice[2]).append("\n");
  }

  public static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    map = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < k; i++) {
      int dir = Integer.parseInt(st.nextToken()) - 1;
      q.offer(dir);
    }
  }
}
