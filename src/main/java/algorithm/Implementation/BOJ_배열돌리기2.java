package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_배열돌리기2 {
    static int n, m, r;
    static int[][] arr;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = Math.min(n,m) / 2;
        int nn = n;
        int nm = m;

        for(int i = 0; i < num; i++){
            int len = (nn + nm) * 2 - 4;  // 각 겹의 회전 길이 계산
            int cir = r % len;  // 최종적으로 회전해야 하는 횟수 계산
            nn -= 2;
            nm -= 2;

            for(int j = 0; j < cir; j++){

                int tmp = arr[i][i];
                int idx = 0;

                int x = i, y = i;

                while(idx < 4){
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];

                    if(nx >= i && ny >= i && nx < n - i && ny < m - i){
                        arr[x][y] = arr[nx][ny];
                        x = nx;
                        y = ny;
                    }else{
                        idx++;
                    }
                }
                arr[i+1][i] = tmp;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
