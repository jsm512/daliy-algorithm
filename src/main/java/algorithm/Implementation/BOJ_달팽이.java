package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_달팽이 {
    static int t_x, t_y;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][n];

        int x = n / 2;
        int y = n / 2;

        int value = 1;
        int length = 1;
        while(true){
            for(int i = 0; i < length; i++){
                arr[x--][y] = value++;
            }
            if(value - 1 == n*n) break;

            for(int i = 0; i < length; i++){
                arr[x][y++] = value++;
            }

            length++;
            for(int i = 0; i < length; i++){
                arr[x++][y] = value++;
            }
            for(int i = 0; i < length; i++){
                arr[x][y--] = value++;
            }

            length++;
        }

//        for(int[] a : arr){
//            System.out.println(Arrays.toString(a));
//        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] == target){
                    t_x = i+1;
                    t_y = j+1;
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(t_x + " " + t_y);

    }

}
