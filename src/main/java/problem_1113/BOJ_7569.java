package problem_1113;

import java.util.*;
import java.io.*;

public class BOJ_7569 {

  /*
  6방향..위 아래가 문제
  3차원 배열?
   */
  static int n, m, h;
  static int[][][] map;
  static int[] dx = {-1, 0, 1, 0, 0, 0};
  static int[] dy = {0, 1, 0, -1, 0, 0};
  static int[] dz = {0, 0, 0, 0, -1, 1};

  static Queue<int[]> q = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());

    map = new int[h][n][m];

    for (int z = 0; z < h; z++) {
      for (int x = 0; x < n; x++) {
        st = new StringTokenizer(br.readLine(), " ");
        for (int y = 0; y < m; y++) {
          map[z][x][y] = Integer.parseInt(st.nextToken());

          if (map[z][x][y] == 1) {
            q.offer(new int[]{x, y, z});
          }
        }
      }
    }
//    System.out.println(Arrays.toString(map[1][1]));
    bfs();
    System.out.println(answer());

  }

  public static void bfs() {
    while (!q.isEmpty()) {
      int[] now = q.poll();
      int x = now[0];
      int y = now[1];
      int z = now[2];

      for (int i = 0; i < 6; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        int nz = z + dz[i];

        if (check(nx, ny, nz)) {
          if (map[nz][nx][ny] == 0) {
            q.offer(new int[]{nx, ny, nz});
            map[nz][nx][ny] = map[z][x][y] + 1;
          }
        }
      }
    }
  }

  public static boolean check(int x, int y, int z) {
    boolean flag = false;
    if (x >= 0 && y >= 0 && z >= 0 && x < n && y < m && z < h) {
      return true;
    }

    return flag;
  }

  public static int answer() {
    int ans = Integer.MIN_VALUE;
    for (int z = 0; z < h; z++) {
      for (int x = 0; x < n; x++) {
        for (int y = 0; y < m; y++) {
          if (map[z][x][y] == 0) {
            return -1;
          }
          ans = Math.max(ans, map[z][x][y]);
        }
      }
    }
    if (ans == 1) {
      return 0;
    } else {
      return ans - 1;
    }
  }
}
