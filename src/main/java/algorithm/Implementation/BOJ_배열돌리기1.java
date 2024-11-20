package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_배열돌리기1 {

  static int n, m, r;
  static int[][] map;

  public static void main(String[] args) throws IOException {
    init();

    while (r-- > 0) {
      simulation();
    }

    answer();
  }

  public static void answer() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void simulation() {
    //정사각형 배열이 아니기 때문에 회전해야될 사각형 수를 구해줌
    int cnt = Math.min(n, m) / 2;

    for (int t = 0; t < cnt; t++) {
      int temp = map[t][t];

      for(int j = t + 1; j < m - t; j++){
        map[t][j-1] = map[t][j];
      }

      for(int j = t + 1; j < n - t; j++){
        map[j-1][m-1-t] = map[j][m-1-t];
      }

      for(int j = m-2-t; j >= t; j--){
        map[n-1-t][j+1] = map[n-1-t][j];
      }

      for(int j = n-2-t; j >= t; j--){
        map[j+1][t] = map[j][t];
      }

      map[t+1][t] = temp;
    }
  }

  public static void init() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    r = Integer.parseInt(st.nextToken());

    map = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }
}
