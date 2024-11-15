package problem_1114;

import java.util.*;
import java.io.*;

public class BOJ_2023 {

  static int n;

  static ArrayList<Integer> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    sol(0, "");
    Collections.sort(list);
    for (int x : list) {
      System.out.println(x);
    }
  }

  public static void sol(int depth, String str) {
    if (depth == n) {
      if (prime(str)) {
        list.add(Integer.parseInt(str));
      }
      return;
    }

    for (int i = 1; i <= 9; i++) {
      if(prime(str+i)) sol(depth+1, str+i);
    }
  }

  public static boolean prime(String s) {
    int n = Integer.parseInt(s);
    if (n < 2) {
      return false;
    }

    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}
