package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_청소년상어 {

  /*
  모든 칸에 번호와 방향을 갖는 물고기 존재
  청소년 상어 (0,0) 물고기 먹고 시작 상어는 (0,0) 물고기 방향과 같은 방향을 갖고 시작

  번호 순대로 물고기 이동 (빈칸, 다른 물고기) 45도 반시계 회전하면서 이동할 수 있는 칸을 찾음
  이동은 칸끼리 위치를 바꿈

  물고기 이동 후 상어 이동
  한 번에 여러 개의 칸을 이동? 방향에 있는 모든 물고기한테 접근할 수 있음 (고기 1개만 먹기 가능)
  물고기가 있는 칸으로만 이동할 수 있음 (방향 얻음)

  상어의 방향은 항상 먹은 물고기 방향으로 범위를 벗어나거나 방향(라인)에 물고기가 존재하지 않으면 종료

  ++ 먹힌 물고기 체크 해줘야됨
   */

  public static class Fish {

    int x, y, num, dir;
    boolean live = true;

    public Fish(int x, int y, int num, int dir, boolean live) {
      this.x = x;
      this.y = y;
      this.num = num;
      this.dir = dir;
      this.live = live;
    }
  }

  public static class Shark {

    int x, y, dir, sum;

    public Shark(int x, int y, int dir, int sum) {
      this.x = x;
      this.y = y;
      this.dir = dir;
      this.sum = sum;
    }
  }

  static int[][] map = new int[4][4];
  static ArrayList<Fish> fishes;

  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
  static int ans;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    fishes = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < 4; j++) {
        int num = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        fishes.add(new Fish(i, j, num, dir - 1, true));
        map[i][j] = num;
      }
    }
//    System.out.println(Arrays.deepToString(map));
    Collections.sort(fishes, Comparator.comparing(e -> e.num));

    Fish fish = fishes.get(map[0][0] - 1);
    Shark shark = new Shark(0, 0, fish.dir, fish.num);
    fish.live = false;
    map[0][0] = -1;

    sol(map, shark, fishes);
    System.out.println(ans);
  }

  public static void sol(int[][] map, Shark shark, ArrayList<Fish> fishes) {

    if (ans < shark.sum) {
      ans = shark.sum;
    }

    fishes.forEach(f -> move(f, map, fishes));

    for (int k = 1; k < 4; k++) {
      int nx = shark.x + dx[shark.dir] * k;
      int ny = shark.y + dy[shark.dir] * k;

      if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && map[nx][ny] > 0) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            copy[i][j] = map[i][j];
          }
        }
        ArrayList<Fish> fishCopy = fishCopy(fishes);
        copy[shark.x][shark.y] = 0;
        Fish f = fishCopy.get(map[nx][ny] - 1);
        Shark newShark = new Shark(f.x, f.y, f.dir, shark.sum + f.num);
        f.live = false;
        copy[f.x][f.y] = -1;

        sol(copy, newShark, fishCopy);
      }
    }
  }

  public static void move(Fish fish, int[][] map, ArrayList<Fish> fishes) {
    if(!fish.live) return;
    for (int i = 0; i < 8; i++) {
      int d = (fish.dir + i) % 8;
      int nx = fish.x + dx[d];
      int ny = fish.y + dy[d];

      if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && map[nx][ny] > -1) {
        map[fish.x][fish.y] = 0;

        if (map[nx][ny] == 0) {
          fish.x = nx;
          fish.y = ny;
        } else { //교환
          Fish f = fishes.get(map[nx][ny] - 1);
          f.x = fish.x;
          f.y = fish.y;
          map[fish.x][fish.y] = f.num;

          fish.x = nx;
          fish.y = ny;
        }
        map[nx][ny] = fish.num;
        fish.dir = d;
        return;
      }
    }
  }

  public static ArrayList<Fish> fishCopy(ArrayList<Fish> fishes) {
    ArrayList<Fish> tmp = new ArrayList<>();
    fishes.forEach(e -> tmp.add(new Fish(e.x, e.y, e.num, e.dir, e.live)));

    return tmp;
  }
}
