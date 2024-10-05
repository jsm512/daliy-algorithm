package algorithm.Implementation;

import java.util.*;
import java.io.*;
public class BOJ_마법사상어와토네이도 {
    static int n;
    static int[][] arr;

    //좌하우상 -> 달팽이 순회 배열
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    //먼지가 뿌려지는 % 저장
    static double[] per = {1,1,2,2,7,7,10,10,5,0};

    //먼지가 뿌려지는 상대좌표
    static int[][] sdx = {
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 좌
            {-1,-1,0,0,0,0,1,1,2,1}, // 하
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 우
            {1,1,0,0,0,0,-1,-1,-2,-1}, // 상
    };
    static int[][] sdy = {
            {1,1,0,0,0,0,-1,-1,-2,-1}, // 좌
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 하
            {-1,-1,0,0,0,0,1,1,2,1}, // 우
            {-1,1,-2,2,-1,1,-1,1,0,0}, // 상
    };
    static int answer = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = n / 2;
        int y = n / 2;

        move(x,y);
        System.out.println(answer);

    }

    public static void move(int x, int y){
        //방향이 2번 바뀌면 이동이 1씩 추가된다 -> 4번 바뀌고 +2 씩 해주면 됨
        int[] changedDir = {1,1,2,2}; //좌하우상 각 이동 횟수
        //탐색 방향의 모래 양 저장 변수
        double ySand;

        while(true){
            //좌하우상 4번 이동
            for(int i = 0; i < 4; i++){
                //각 방향의 이동 횟수만큼 반복
                for(int j = 0; j < changedDir[i]; j++){
                    //이동할 좌표로 갱신
                    x += dx[i];
                    y += dy[i];

                    ySand = arr[x][y];
                    
                    //이동한 좌표에 모래가 있다면
                    if(ySand > 0){
                        //흩뿌리기
                        spread(x,y,i,ySand);
                    }
                    //(0,0)이면 순회 종료 break말고 return으로 종료해버려 -> break할라면 while Loop 종료로도 할 수 있음
                    if(x == 0 && y == 0) return;
                }
            }
            // 4가지 방향에 대해 순회를 완료했으면 각 방향의 이동 횟수를 +2 해줌
            for(int k = 0; k < 4; k++){
                changedDir[k] += 2;
            }
        }
    }

    public static void spread(int x, int y, int dir, double ySand){
        int nx, ny;
        double spreadSands = 0;

        //모래를 이동시킬거니까 원래 모래가 있던 자리는 0으로 초기화
        arr[x][y] = 0;

        //모래 뿌리기
        for(int i = 0; i < 10; i++){
            //상대좌표로 계산해 정해진 %만큼 뿌려줌
            nx = x + sdx[dir][i];
            ny = y + sdy[dir][i];

            int sand = (int) Math.floor(ySand / 100 * per[i]);

            //9개의 저장된 좌표에 다 뿌렸으면
            if(i == 9){
                //aSand는 뿌리고 남은 모래의 양임
                double aSand = ySand - spreadSands;
                //sdx,sdy의 9번째 idx는 범위를 체크할 수 있게 모래의 이동방향이 저장됨
                if(!(nx >= 0 && ny >= 0 && nx < n && ny < n)){
                    answer += aSand;
                }else{
                    arr[nx][ny] += aSand;
                }
                //정해진 9개의 좌표에 모래 뿌리기
            }else{
                //뿌릴려는 상대좌표가 범위를 넘어서면 answer에 더해줌
                if(!(nx >= 0 && ny >= 0 && nx < n && ny < n)){
                    answer += sand;
                    //범위를 안넘으면 -> 뿌릴 좌표에 앞서 계산한 %만큼 모래를 저장
                }else{
                    arr[nx][ny] += sand;
                }
                // 9개의 뿌려진 모래의 누적합
                spreadSands += sand;
            }
        }
    }
}
