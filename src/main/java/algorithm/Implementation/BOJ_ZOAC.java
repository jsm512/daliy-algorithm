package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_ZOAC {
    static String str;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        check = new boolean[str.length()];

        recur(0,str.length() - 1);
        System.out.println(sb.toString());
    }

    public static void recur(int left, int right){
        if(left > right) return;

        int idx = left;

        for(int i = left; i <= right; i++){
            if(str.charAt(idx) > str.charAt(i)){
                idx = i;
            }
        }
        check[idx] = true;

        for(int i = 0; i < str.length(); i++){
            if(check[i]){
                sb.append(str.charAt(i));
            }
        }
        sb.append("\n");

        recur(idx+1, right);
        recur(left, idx-1);
    }
}
