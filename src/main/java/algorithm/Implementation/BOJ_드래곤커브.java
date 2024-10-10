package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_드래곤커브 {
    static int n;
    static boolean[][] check = new boolean[101][101];

    /*
    0 일때 오른쪽
    1 일때 위쪽
    2 일때 왼쪽
    3 일때 아래
     */
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static int answer = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        while(n --> 0){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            curve(x,y,dir,age);
        }

        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(check[i][j] && check[i][j+1] && check[i+1][j] && check[i+1][j+1]) answer++;
            }
        }
        System.out.println(answer);
    }

    public static void curve(int x ,int y, int d, int g){
        List<Integer> list = new ArrayList<>();
        list.add(d);

        while(g --> 0){
            for(int i = list.size() - 1; i >= 0; i--){
                list.add((list.get(i) + 1) % 4);
            }
        }

        check[y][x] = true;
        for(Integer dir : list){
            x += dx[dir];
            y += dy[dir];
            check[y][x] = true;
        }
    }
}
