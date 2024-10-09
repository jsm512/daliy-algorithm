package algorithm.Implementation;

import java.util.*;
import java.io.*;

public class BOJ_사다리타기 {

    static int n, k;
    static char[][] arr;
    static char[] top;
    static int target;
    static char[] ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        arr = new char[n][k-1];
        ans = new char[k];
        top = new char[k];

        String str = br.readLine();
        for(int i = 0; i < str.length(); i++){
            ans[i] = str.charAt(i);
            top[i] = (char)('A' + i);
        }

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++){
                if(s.charAt(j) == '?'){
                    target = i;
                    Arrays.fill(arr[i],'?');
                    continue;
                }
                arr[i][j] = s.charAt(j);
            }
        }
//        System.out.println(Arrays.deepToString(arr));

        for(int i = 0; i < target; i++){
            next(i);
        }
        for(int i = n - 1; i > target; i--){
            prev(i);
        }

//        System.out.println(Arrays.toString(top));
//        System.out.println(Arrays.toString(ans));
        result();
    }
    public static void result(){
        for(int i = 0; i < k - 1; i++){
            if(top[i] != ans[i]){ //서로 다르고
                if(top[i] != ans[i+1]){ //뒤에도 다르면 사다리 하나로 못하는 경우임 바로 종료
                    sb.delete(0, sb.length());
                    for(int j = 0; j < k - 1; j++){
                        sb.append("x");
                    }
                    break;
                }
                //앞뒤로 바뀌면 성립하는 경우 사다리를 놔줌
                sb.append("-");
                //뒤에는 자동으로 *이므로 i++로 증가시켜서 다음건 무효
                i++;
                if(i < k - 1){
                    sb.append("*");
                }
                continue;
            }
            //-가 아니면 *임 or 위에서 - 추가했으면 *를 붙여줌
            sb.append("*");
        }
        System.out.println(sb.toString());
    }

    public static void next(int idx){
        for(int i = 0; i < k - 1; i++){
            char ch = arr[idx][i];
            if(ch == '-'){
                char tmp = top[i+1];
                top[i+1] = top[i];
                top[i] = tmp;
            }
        }
    }
    public static void prev(int idx){
        for(int i = 0; i < k - 1; i++){
            char ch = arr[idx][i];
            if(ch == '-'){
                char tmp = ans[i];
                ans[i] = ans[i+1];
                ans[i+1] = tmp;
            }
        }
    }
}
