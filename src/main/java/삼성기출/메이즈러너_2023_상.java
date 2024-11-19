package 삼성기출;

import java.util.*;
import java.io.*;

public class 메이즈러너_2023_상 {

  static class Point {

    int x;
    int y;
    int point;
    boolean status;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int n, m, k;
  static int[][] map;
  static Point exit;
  static Point[] people;

  //상하좌우
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  //선택된 사각형의 좌상단(r,c), 크기 저장할 배열
  static int[] square = new int[3];

  public static void main(String[] args) throws Exception {
    init();

    for (int i = 0; i < k; i++) {
      moveParticipant();
      if (checkEnd()) {
        break;
      }
      selectSquare();
      rotate();
      if (checkEnd()) {
        break;
      }
    }

    answer();
  }

  public static boolean checkEnd() {
    for (int i = 0; i < m; i++) {
      if (!people[i].status) {
        return false;
      }
    }

    return true;
  }

  public static void selectSquare() {
    square = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    for (int i = 0; i < m; i++) {
      if (people[i].status) {
        continue;
      }
      Point p = people[i];
      checkSmallSquare(p);
    }
  }

  public static void checkSmallSquare(Point p) {
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n - k; i++) {
        for (int j = 0; j < n - k; j++) {

          int rr = i + k;
          int rc = j + k;

          if (!isInner(p, i, rr, j, rc)) {
            continue;
          }
          if (!isInner(exit, i, rr, j, rc)) {
            continue;
          }

          minimumSquare(i, j, k);
          return;
        }
      }
    }
  }

  public static void minimumSquare(int r, int c, int size) {
    if (square[2] < size) {
      return;
    }
    if (square[2] == size) {
      if (square[0] < r) {
        return;
      }
      if (square[0] == r && square[1] < c) {
        return;
      }
    }
    square[0] = r;
    square[1] = c;
    square[2] = size;
  }

  public static boolean isInner(Point p, int tr, int rr, int tc, int rc) {
    return tr <= p.x && p.x <= rr && tc <= p.y && p.y <= rc;
  }

  public static void rotate() {
    /*
    한명 이상의 참가자와 출구를 포함한 정사각형(가장 작은)
    가장 작은 정사각형이 여러개면.. 좌상단 -> c좌표 우선순위로 사각형 선택
    선택된 정사각형 시계방향 90도 회전
    정사각형에 벽이 있으면 -> 내구도 1씩 감소
     */
    for (int i = 0; i < m; i++) {
      if (people[i].status) {
        continue;
      }
      Point p = people[i];
      changePoint(p);
    }
    changePoint(exit);

    int tr = square[0];
    int tc = square[1];
    int size = square[2];

    int[][] tmp = new int[size + 1][size + 1];
    for (int i = 0; i <= size; i++) {
      for (int j = 0; j <= size; j++) {
        tmp[j][size - i] = map[tr + i][tc + j];
        if (tmp[j][size - i] != 0) {
          tmp[j][size - i]--;
        }
      }
    }
    for (int i = 0; i <= size; i++) {
      for (int j = 0; j <= size; j++) {
        map[tr + i][tc + j] = tmp[i][j];
      }
    }
  }

  public static void changePoint(Point p) {
    int tr = square[0];
    int tc = square[1];
    int size = square[2];

    if (isInner(p, tr, tr + size, tc, tc + size)) {

      int i = p.x - tr;
      int j = p.y - tc;

      int ni = j;
      int nj = size - i;

      int r = tr + ni;
      int c = tc + nj;

      p.x = r;
      p.y = c;
    }
  }

  public static void moveParticipant() {
    /*
    모든 참가자 이동 메소드...
    상하좌우 우선순위를 가지고 있음
    현재 칸 보다 출구랑 가까운 위치로 이동
    dir = -1 이라면 -> 움직이지 않음 처리 해줘야됨
    한 칸에 여러명 참가자 가능
    (..궁금) 회전할 때도 참가자의 이동거리를 포함해야되는건가?
     */

    for (int i = 0; i < m; i++) {
      Point now = people[i];
      if (now.status) {
        continue; //이미 탈출한 참가자의 경우 패스
      }
      int beforeDist = getDist(now);
      int dir = -1;

      for (int d = 0; d < 4; d++) {
        int nx = now.x + dx[d];
        int ny = now.y + dy[d];

        //경계 / 벽
        if (boundary(nx, ny) || isWall(nx, ny)) {
          continue;
        }

        int nowDist = getDist(nx, ny);
        if (beforeDist > nowDist) {
          beforeDist = nowDist;
          dir = d;
        }
      }

      if (dir == -1) {
        continue;
      }

      now.x += dx[dir];
      now.y += dy[dir];
      now.point++;

      if (now.x == exit.x && now.y == exit.y) {
        now.status = true;
      }
    }
  }

  public static boolean isWall(int x, int y) {
    return map[x][y] > 0;
  }

  public static boolean boundary(int x, int y) {
    return x < 0 || y < 0 || x >= n || y >= n;
  }

  public static int getDist(int x, int y) {
    return (Math.abs(x - exit.x) + Math.abs(y - exit.y));
  }

  public static int getDist(Point p) {
    return (Math.abs(p.x - exit.x) + Math.abs(p.y - exit.y));
  }

  public static void answer() {
    int ans = 0;
    for (int i = 0; i < m; i++) {
      ans += people[i].point;
    }

    StringBuilder sb = new StringBuilder();
    sb.append(ans).append("\n");
    sb.append(exit.x + 1).append(" ").append(exit.y + 1);
    System.out.print(sb);
  }

  public static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    map = new int[n][n];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    people = new Point[m];
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;

      people[i] = new Point(x, y);
    }

    st = new StringTokenizer(br.readLine(), " ");
    exit = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

  }
}
