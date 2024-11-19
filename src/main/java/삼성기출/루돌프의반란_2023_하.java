package 삼성기출;

import java.util.*;
import java.io.*;

public class 루돌프의반란_2023_하 {

  static class Node {

    int x;
    int y;
    int isStun;
    boolean status;
    int point;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int n, m, p, c, d;
  static Node rudolf;
  static Node[] santa;

  static Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
      if (o1[0] == o2[0]) {
        if (o1[1] == o2[1]) {
          return -Integer.compare(o1[2], o2[2]);
        }
        return -Integer.compare(o1[1], o2[1]);
      }

      return Integer.compare(o1[0], o2[0]);
    }
  });
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int[] er = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] ec = {-1, 0, 1, -1, 1, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    init();

    for (int i = 0; i < m; i++) {
      moveRudolf();
      moveSanta();
      if (checkEnd()) {
        break;
      }
      addScore();
    }

    for (int i = 0; i < p; i++) {
      System.out.print(santa[i].point + " ");
    }
  }

  public static void addScore() {
    for (int i = 0; i < p; i++) {
      if (santa[i].status) {
        continue;
      }
      santa[i].point++;
      santa[i].isStun = Math.max(santa[i].isStun - 1, 0);
    }
  }

  public static boolean checkEnd() {
    for (int i = 0; i < p; i++) {
      if (!santa[i].status) {
        return false;
      }
    }
    return true;
  }

  public static int getDist(Node santa) {
    return (int) (Math.pow(Math.abs(santa.x - rudolf.x), 2) + Math.pow(Math.abs(santa.y - rudolf.y),
        2));
  }

  public static int getDist(Node santa, int x, int y) {
    return (int) (Math.pow(Math.abs(santa.x - x), 2) + Math.pow(Math.abs(santa.y - y), 2));
  }

  public static int getDist(int x, int y) {
    return (int) (Math.pow(Math.abs(x - rudolf.x), 2) + Math.pow(Math.abs(y - rudolf.y), 2));
  }

  public static boolean checkSanta(int x, int y) {
    for (int i = 0; i < p; i++) {
      Node now = santa[i];
      if (now.status) {
        continue;
      }
      if (now.x == x && now.y == y) {
        return true;
      }
    }
    return false;
  }

  public static void moveSanta() {
    for (int i = 0; i < p; i++) {
      Node nowSanta = santa[i];
      if (nowSanta.status || nowSanta.isStun > 0) {
        continue;
      }

      int beforeDist = getDist(nowSanta);
      int dir = -1;
      for (int d = 0; d < 4; d++) {
        int nx = nowSanta.x + dr[d];
        int ny = nowSanta.y + dc[d];

        if (nx < 0 || ny < 0 || nx >= n || ny >= n || checkSanta(nx, ny)) {
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

      nowSanta.x += dr[dir];
      nowSanta.y += dc[dir];

      if (nowSanta.x == rudolf.x && nowSanta.y == rudolf.y) {
        nowSanta.point += d;
        nowSanta.x -= (dr[dir] * d);
        nowSanta.y -= (dc[dir] * d);

        if (nowSanta.x < 0 || nowSanta.y < 0 || nowSanta.x >= n || nowSanta.y >= n) {
          nowSanta.status = true;
          continue;
        }

        nowSanta.isStun = 2;

        boolean[] check = new boolean[p];
        check[i] = true;
        for (int j = 0; j < p; j++) {
          if (check[j]) {
            continue;
          }
          Node another = santa[j];
          if (another.status) {
            continue;
          }
          if (another.x == nowSanta.x && another.y == nowSanta.y) {
            conflictSanta(j, (dir + 2) % 4, check);
            continue;
          }
        }
      }
    }
  }

  public static void conflictSanta(int idx, int dir, boolean[] check) {
    Node nowSanta = santa[idx];
    nowSanta.x += dr[dir];
    nowSanta.y += dc[dir];
    check[idx] = true;

    if (nowSanta.x < 0 || nowSanta.y < 0 || nowSanta.x >= n || nowSanta.y >= n) {
      nowSanta.status = true;
      return;
    }

    for (int i = 0; i < p; i++) {
      if (check[i] || santa[i].status) {
        continue;
      }
      Node another = santa[i];
      if (nowSanta.x == another.x && nowSanta.y == another.y) {
        check[i] = true;
        conflictSanta(i, dir, check);
        return;
      }
    }
  }

  public static void moveRudolf() {
    q.clear();
    for (int i = 0; i < p; i++) {
      if (santa[i].status) {
        continue;
      }
      int dist = getDist(santa[i]);
      q.add(new int[]{dist, santa[i].x, santa[i].y, i});
    }

    Node selectSanta = santa[q.poll()[3]];

    int beforeDist = getDist(selectSanta);
    int wayResult = -1;
    for (int i = 0; i < 8; i++) {
      int nx = rudolf.x + er[i];
      int ny = rudolf.y + ec[i];
//      System.out.println(nx + " " + ny);
      if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
        continue;
      }
      int nowDist = getDist(selectSanta, nx, ny);
      if (beforeDist > nowDist) {
        beforeDist = nowDist;
        wayResult = i;
      }
    }

    rudolf.x += er[wayResult];
    rudolf.y += ec[wayResult];

    boolean[] check = new boolean[p];
    for (int i = 0; i < p; i++) {
      Node now = santa[i];
      if (rudolf.x == now.x && rudolf.y == now.y) {
        conflict(i, wayResult, check);
      }
    }
  }

  public static void conflict(int idx, int dir, boolean[] check) {
    Node nowSanta = santa[idx];
    check[idx] = true;
    nowSanta.point += c;
    nowSanta.isStun = 2;

    int nx = nowSanta.x + (er[dir] * c);
    int ny = nowSanta.y + (ec[dir] * c);
    nowSanta.x = nx;
    nowSanta.y = ny;
    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
      nowSanta.status = true;
      return;
    }

    for (int i = 0; i < p; i++) {
      if (check[i]) {
        continue;
      }
      Node another = santa[i];
      if (another.status) {
        continue;
      }
      if (nx == another.x && ny == another.y) {
        santaInteract(i, dir, check);
        return;
      }
    }
  }

  public static void santaInteract(int idx, int dir, boolean[] check) {
    Node nowSanta = santa[idx];
    check[idx] = true;

    int nx = nowSanta.x + er[dir];
    int ny = nowSanta.y + ec[dir];
    nowSanta.x = nx;
    nowSanta.y = ny;
    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
      nowSanta.status = true;
      return;
    }

    for (int i = 0; i < p; i++) {
      if (check[i]) {
        continue;
      }
      Node another = santa[i];
      if (another.status) {
        continue;
      }
      if (nx == another.x && ny == another.y) {
        santaInteract(i, dir, check);
        return;
      }
    }
  }


  public static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    p = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine(), " ");
    rudolf = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

    santa = new Node[p];
    for (int i = 0; i < p; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int idx = Integer.parseInt(st.nextToken()) - 1;
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;

      santa[idx] = new Node(r, c);
    }
  }
}