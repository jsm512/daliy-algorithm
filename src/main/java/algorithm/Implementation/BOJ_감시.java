package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_감시 {

  /*
  각 cctv 감시 영역이 최대인 방향만 골라서 체크 x 경우에 따라 달라짐..
  백트래킹으로 전체 조합 검사
  각 cctv가 검사할 수 있는 방향 전체 검사
   */
  static int n, m;
  static int[][] map;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  static int[][][] mode = {{{0}}, {{0}, {1}, {2}, {3}}, {{0, 2}, {1, 3}},
      {{0, 1}, {1, 2}, {2, 3}, {0, 3}}, {{0, 1, 2}, {1, 2, 3}, {0, 2, 3}, {0, 1, 3}},
      {{0, 1, 2, 3}}};

  static int ans = 81;
  static ArrayList<CC> cctv;

  public static class CC {

    int x;
    int y;
    int type;

    public CC(int x, int y, int type) {
      this.x = x;
      this.y = y;
      this.type = type;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    map = new int[n][m];
    cctv = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        int num = Integer.parseInt(st.nextToken());
        map[i][j] = num;
        if (num != 0 && num != 6) {
          cctv.add(new CC(i, j, num));
        }
      }
    }

    sol(0, map);
    System.out.println(ans);
  }

  public static void sol(int depth, int[][] map) {
    if (depth == cctv.size()) {
      int cnt = check(map);
      ans = Math.min(ans, cnt);
      return;
    }

    int type = cctv.get(depth).type;
    int x = cctv.get(depth).x;
    int y = cctv.get(depth).y;

    for (int i = 0; i < mode[type].length; i++) {
      int[][] copy = new int[n][m];
      for (int j = 0; j < n; j++) {
        copy[j] = map[j].clone();
      }

      for (int k = 0; k < mode[type][i].length; k++) {
        int dir = mode[type][i][k];

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (true) {
          if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            break;
          }
          if (map[nx][ny] == 6) {
            break;
          }
          copy[nx][ny] = -1;
          nx += dx[dir];
          ny += dy[dir];
        }
      }

      sol(depth+1, copy);
    }

  }

  public static int check(int[][] map) {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 0) {
          cnt++;
        }
      }
    }
    return cnt;
  }
}
