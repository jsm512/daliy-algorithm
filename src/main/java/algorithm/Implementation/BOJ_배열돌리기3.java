package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_배열돌리기3 {
    static int n,m,r;
    static int[][] init_arr;
    static int[][] copy_arr;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        init_arr = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                init_arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        while(r --> 0){
            int num = Integer.parseInt(st.nextToken());
            switch (num){
                case 1:
                    rotationA(init_arr.length, init_arr[0].length);
                    break;
                case 2:
                    rotationB(init_arr.length, init_arr[0].length);
                    break;
                case 3:
                    rotationC(init_arr.length, init_arr[0].length);
                    copyArray();
                    break;
                case 4:
                    rotationD(init_arr.length, init_arr[0].length);
                    copyArray();
                    rotationA(init_arr.length, init_arr[0].length);
                    break;
                case 5:
                    rotationE(init_arr.length, init_arr[0].length);
                    break;
                case 6:
                    rotationF(init_arr.length, init_arr[0].length);
                    break;

            }
        }
        for(int[] arr : init_arr){
            for(int x : arr){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
    public static void copyArray(){
        init_arr = new int[copy_arr.length][copy_arr[0].length];
        for(int i = 0; i < copy_arr.length; i++){
            System.arraycopy(copy_arr[i], 0, init_arr[i], 0, copy_arr[i].length);
        }
    }
    public static void rotationA(int r, int c){
        //상하반전
        for(int i = 0; i < r/2; i++){
            int[] tmp = new int[c];
            System.arraycopy(init_arr[i], 0, tmp, 0, c);
            System.arraycopy(init_arr[r - 1 - i], 0, init_arr[i], 0, c);
            init_arr[r-1-i] = tmp;
        }
    }
    public static void rotationB(int r, int c){
        //좌우반전
        for(int i = 0; i < c / 2; i++){
            int[] tmp = new int[r];
            for(int j = 0; j < r; j++){
                tmp[j] = init_arr[j][i];
            }
            for(int j = 0; j < r; j++){
                init_arr[j][i] = init_arr[j][c-1-i];
            }
            for(int j = 0; j < r; j++){
                init_arr[j][c-1-i] = tmp[j];
            }
        }
    }
    public static void rotationC(int r, int c){
        //오른쪽 90도
        copy_arr = new int[c][r];
        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                copy_arr[i][j] = init_arr[r-1-j][i];
            }
        }
    }
    public static void rotationD(int r, int c){
        //왼쪽 90도
        copy_arr = new int[c][r];
        for(int i = 0; i < c; i++){
            for(int j = 0; j < r; j++){
                copy_arr[i][j] = init_arr[j][i];
            }
        }
    }
    public static void rotationE(int r, int c){
        //1번 구역 저장해놓기
        int[][] tmp = new int[r/2][c/2];
        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c/2; j++){
                tmp[i][j] = init_arr[i][j];
            }
        }
        //4 번 -> 1번으로
        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c / 2; j++){
                init_arr[i][j] = init_arr[i+r/2][j];
            }
        }
        //3번 -> 4번으로
        for(int i = r/2; i < r; i++){
            for(int j = 0; j < c/2; j++){
                init_arr[i][j] = init_arr[i][j+c/2];
            }
        }
        //2번 -> 3번으로
        for(int i = 0; i < r/2; i++){
            for(int j = c/2; j < c; j++){
                init_arr[i+r/2][j] = init_arr[i][j];
            }
        }

        //1번 -> 2번
        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c/2; j++){
                init_arr[i][j+c/2] = tmp[i][j];
            }
        }


    }
    public static void rotationF(int r, int c){
        int[][] tmp = new int[r/2][c/2];
        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c/2; j++){
                tmp[i][j] = init_arr[i][j];
            }
        }
        // 2번 -> 1번
        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c/2; j++){
                init_arr[i][j] = init_arr[i][j+c/2];
            }
        }
        //3번 -> 2번
        for(int i = 0; i < r/2; i++){
            for(int j = c/2; j < c; j++){
                init_arr[i][j] = init_arr[i+r/2][j];
            }
        }
        //4번 -> 3번
        for(int i = r/2; i < r; i++){
            for(int j = 0; j < c/2; j++){
                init_arr[i][j+c/2] = init_arr[i][j];
            }
        }

        for(int i = 0; i < r/2; i++){
            for(int j = 0; j < c/2; j++){
                init_arr[i+r/2][j] = tmp[i][j];
            }
        }
    }
}
