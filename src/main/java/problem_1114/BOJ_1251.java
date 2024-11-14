package problem_1114;

import java.util.*;
import java.io.*;

public class BOJ_1251 {

  static String str;
  static ArrayList<String> words = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    str = br.readLine();
    for(int i = 1; i < str.length(); i++){
      for(int j = i + 1; j < str.length(); j++){
        StringBuilder str1 = new StringBuilder(str.substring(0,i));
        StringBuilder str2 = new StringBuilder(str.substring(i,j));
        StringBuilder str3 = new StringBuilder(str.substring(j));
        StringBuilder sb = new StringBuilder();

        sb.append(str1.reverse()).append(str2.reverse()).append(str3.reverse());
        words.add(sb.toString());
      }
    }
    Collections.sort(words);

    System.out.println(words.get(0));
  }

}
