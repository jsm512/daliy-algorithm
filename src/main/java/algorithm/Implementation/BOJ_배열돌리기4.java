package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_배열돌리기4 {

  static int n, m, k;
  static int[][] map;
  static ArrayList<int[]> list = new ArrayList<>();
  static boolean[] check;
  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception {

    init();
    simulation(0, map);
    System.out.print(ans);
  }

  public static void minRow(int[][] arr) {
    //simualtion에서 무작위 k 번 진행 후 -> map 배열의 최소 행을 찾는 메소드
    for (int i = 0; i < n; i++) {
      int cnt = 0;
      for (int j = 0; j < m; j++) {
        cnt += arr[i][j];
      }
      ans = Math.min(ans, cnt);
    }
  }

  public static void simulation(int depth, int[][] arr) {
    // k번 백트래킹으로 map의 배열을 완성시킬 메소드
    if (depth == k) {
      minRow(arr);
      return;
    }
    for (int i = 0; i < k; i++) {
      if (check[i]) {
        continue;
      }
      check[i] = true;
      simulation(depth + 1, rotate(i, arr));
      check[i] = false;
    }
  }

  public static int[][] rotate(int idx, int[][] arr) {
    int r = list.get(idx)[0];
    int c = list.get(idx)[1];
    int s = list.get(idx)[2];

    int[][] tmp = copyArray(arr);

    for (int len = 1; len <= s; len++) {

      int startRow = r - len;
      int startCol = c - len;
      int endRow = r + len;
      int endCol = c + len;

      for (int j = startCol; j < endCol; j++) {
        tmp[r - len][j + 1] = arr[r - len][j];
      }
      for (int i = startRow; i < endRow; i++) {
        tmp[i + 1][c + len] = arr[i][c + len];
      }
      for (int j = endCol; j > startCol; j--) {
        tmp[r + len][j - 1] = arr[r + len][j];
      }
      for (int i = endRow; i > startRow; i--) {
        tmp[i - 1][c - len] = arr[i][c - len];
      }

      arr = copyArray(tmp);
    }

    return tmp;
  }

  public static int[][] copyArray(int[][] arr) {
    int[][] tmp = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        tmp[i][j] = arr[i][j];
      }
    }

    return tmp;
  }

  public static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    map = new int[n][m];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    check = new boolean[k];
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      int s = Integer.parseInt(st.nextToken());

      list.add(new int[]{r, c, s});
    }
  }
}
