package pb1118;

import java.util.*;
import java.io.*;

public class BOJ_15728 {

  /*
  -값끼리 곱하는 경우도 고려
  어떻게????

   */
  static int n, k;
  static int[] common;
  static ArrayList<Integer> team;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    common = new int[n];
    team = new ArrayList<>();

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < n; i++) {
      common[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < n; i++) {
      team.add(Integer.parseInt(st.nextToken()));
    }

//    Arrays.sort(common);
//    Collections.sort(team);

    sol();

//    System.out.println(Arrays.toString(common));
//    System.out.println(team);

    int ans = Integer.MIN_VALUE;
    for (int i : common) {
      for (int j : team) {
        ans = Math.max(ans, i * j);
      }
    }

    System.out.println(ans);
  }

  public static void sol() {
    while (k-- > 0) {
      int max = Integer.MIN_VALUE;
      int idx = -1;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < team.size(); j++) {
          if (max < common[i] * team.get(j)) {
            max = common[i] * team.get(j);
            idx = j;
          }
        }
      }
      team.remove(idx);
    }
  }
}
