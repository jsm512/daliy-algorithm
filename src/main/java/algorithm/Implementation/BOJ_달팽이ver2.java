package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_달팽이ver2 {

    //상우하좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int t_x = 0, t_y = 0;
        int[][] arr = new int[n][n];

        int x = n / 2;
        int y = n / 2;

        int value = 1;
        int d = 0;
        int length = 1;
        arr[x][y] = value;
        while(true){
            x += dx[d];
            y += dy[d];

            if(x < 0 || y < 0 || x >= n || y >= n) break;

            if(d != 3) {
                if(arr[x + dx[d+1]][y + dy[d+1]] == 0) d++;
            }else{
                if(arr[x+dx[0]][y+dy[0]] == 0) d = 0;
            }
            value++;
            arr[x][y] = value;

            if(value == target){
                t_x = x + 1;
                t_y = y + 1;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(t_x + " " + t_y);
    }
}
