package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_톱니바퀴 {

  //0번 인덱스가 12시를 가리키고 시계방향으로
  static int[][] wheel = new int[4][8];
  static int[] d;
  static int k;
  static ArrayList<int[]> list = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    init();

    for (int i = 0; i < k; i++) {
      d = new int[4];
      int num = list.get(i)[0];
      int dir = list.get(i)[1];
      d[num] = dir;
      checkWheel(num);
      simulation();
    }
    answer();
  }

  public static void answer() {
    int sum = 0;
    if (wheel[0][0] == 1) {
      sum += 1;
    }
    if (wheel[1][0] == 1) {
      sum += 2;
    }
    if (wheel[2][0] == 1) {
      sum += 4;
    }
    if (wheel[3][0] == 1) {
      sum += 8;
    }

    System.out.println(sum);
  }

  public static void simulation() {
    //checkWheel에서 d 배열에 돌아갈 수 있는 톱니를 저장했음 ( 0 이 아니면 돌아갈 수 있는 톱니)
    int tmp = 0;
    for (int i = 0; i < 4; i++) {
      if (d[i] == 1) {
        tmp = wheel[i][7];
        for (int j = 7; j > 0; j--) {
          wheel[i][j] = wheel[i][j - 1];
        }
        wheel[i][0] = tmp;
      }
      if (d[i] == -1) {
        tmp = wheel[i][0];
        for (int j = 0; j < 7; j++) {
          wheel[i][j] = wheel[i][j + 1];
        }
        wheel[i][7] = tmp;
      }
    }
  }

  public static void checkWheel(int n) {
    //선택된 톱니의 왼쪽 톱니가 돌아갈 수 있는 지 검사
    for (int i = n - 1; i >= 0; i--) {
      if (wheel[i][2] != wheel[i + 1][6]) {
        d[i] = -d[i + 1];
      } else {
        break;
      }
    }

    //선택된 톱니의 오른쪽 톱니가 돌아갈 수 있는 지 검사
    for (int i = n + 1; i < 4; i++) {
      if (wheel[i][6] != wheel[i - 1][2]) {
        d[i] = -d[i - 1];
      } else {
        break;
      }
    }
  }

  public static void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    for (int i = 0; i < 4; i++) {
      String str = br.readLine();
      for (int j = 0; j < 8; j++) {
        wheel[i][j] = str.charAt(j) - '0';
      }
    }

    k = Integer.parseInt(br.readLine());
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int n = Integer.parseInt(st.nextToken()) - 1;
      int dir = Integer.parseInt(st.nextToken());

      list.add(new int[]{n, dir});
    }
  }
}
