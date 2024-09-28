package algorithm.Implementation;
/*
# BOJ_20056 마법사 상어와 파이어볼
# 문제 유형 : 구현 / 시뮬레이션
# 난이도 : 골드 IV
# 풀이 노트
i개의 (r,c)위치에 m 질량, d 방향, s 속도를 가진 파이어볼이 주어짐
각 파이어볼은 d 방향으로 s만큼 이동
이동 후
1. 같은 칸에 파이어볼이 여러개 있다면
파이어볼을 하나로 합친 후 4개로 나눈다
나눠진 파이어볼의
질량 : 합쳐진 파이어볼 / 5
속력 : 합쳐진 파이어볼 / 합쳐진 파이어볼 개수
방향 : 모두 같은 경우 -> (0,2,4,6) 순 / 다른 경우 -> (1,3,5,7) 순
질량이 0인 파이어볼은 사라짐
k번의 명령 후 남아 있는 파이어볼의 질량의 합은?

파이어볼 객체를 생성

 */
import java.util.*;
import java.io.*;

public class BOJ_마법상어와파이어볼 {
    static class fireBall{
        int r,c,m,d,s;
        public fireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N,M,K;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static List<fireBall>[][] map;
    static List<fireBall> arr = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new List[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            arr.add(new fireBall(r,c,m,d,s));
        }

        while(K --> 0){
            move();
            split();
        }

        int answer = 0;
        for(int i = 0; i < arr.size(); i++){
            answer += arr.get(i).m;
        }
        System.out.println(answer);

        br.close();
    }

    public static void move(){
        for(fireBall f : arr){
            int nx = (f.r + N + dx[f.d] * (f.s % N)) % N;
            int ny = (f.c + N + dy[f.d] * (f.s % N)) % N;
            f.r = nx;
            f.c = ny;
            map[nx][ny].add(f);
        }
    }

    public static void split(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j].size() < 2){
                    map[i][j].clear();
                    continue;
                }
                int m_sum = 0;
                int s_sum = 0;
                int odd = 0;
                int even = 0;
                for(fireBall f : map[i][j]){
                    m_sum += f.m;
                    s_sum += f.s;

                    if(f.d % 2 == 0) odd++;
                    else even++;

                    arr.remove(f);
                }

                int nMass = m_sum / 5;
                int nSpeed = s_sum / map[i][j].size();
                map[i][j].clear();

                if(nMass == 0) continue;

                if(odd > 0 && even > 0){
                    for(int k = 1; k < 8; k+=2){
                        arr.add(new fireBall(i,j,nMass,nSpeed,k));
                    }
                }else{
                    for(int k = 0; k < 8; k+=2){
                        arr.add(new fireBall(i,j,nMass,nSpeed,k));
                    }
                }

            }
        }
    }
}
