package algorithm.Implementation;

import java.util.*;
import java.io.*;
/*
1. 부분 배열 돌리기
2. 인접한 칸 탐색 후 얼음 제거
3. 반복?
4. 덩어리 체크 o
 */
public class BOJ_마법사상어와파이어스톰 {
    static int n,q, size;
    static int[][] arr;
    static boolean[][] visited;

    //상우하좌
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<Integer> l = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        size = (int)Math.pow(2,n);
        arr = new int[size][size];
        visited = new boolean[size][size];

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < size; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < q; i++){
            l.add(Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i < q; i++){
            rotation((int)Math.pow(2,l.get(i)));
            check();
        }

        int sum = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                sum += arr[i][j];
            }
        }
        sb.append(sum).append("\n");

        int max_Mass = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(arr[i][j] > 0 && !visited[i][j]){
                    max_Mass = Math.max(max_Mass, mass(i,j));
                }
            }
        }
        sb.append(max_Mass);

        System.out.println(sb);
    }

    public static void rotation(int L){
        //배열 돌리기 시계 방향 90도
        for(int i = 0; i < size; i += L){
            for(int j = 0; j < size; j += L){
                part(i,j,L);
            }
        }

    }
    public static void part(int r, int c, int L){
        int[][] tmp = new int[L][L];
        for(int i = 0; i < L; i++){
            for(int j = 0; j < L; j++){
                tmp[i][j] = arr[r+L-1-j][c+i];
            }
        }
        for(int i = 0; i < L; i++){
            for(int j = 0; j < L; j++){
                arr[r+i][c+j] = tmp[i][j];
            }
        }
    }

    public static void check() {
        /*
        얼음 제거 -> 배열 카피해서 원본 배열 보면서 해야될듯?
         */
        int[][] copy = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (copy[i][j] == 0) continue;
                    int cnt = 0;
                    for(int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx >= 0 && ny >= 0 && nx < size && ny < size){
                            if(arr[nx][ny]> 0) cnt++;
                        }
                    }
                    if(cnt < 3){
                        copy[i][j]--;
                    }

            }
        }
        arr = copy;

    }
    public static int mass(int x, int y){
        //덩어리
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        visited[x][y] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < size && ny < size){
                    if(arr[nx][ny] > 0 && !visited[nx][ny]){
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
