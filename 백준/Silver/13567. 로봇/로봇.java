import java.util.*;
import java.io.*;


public class Main {
    
    static int M;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};
        
        M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        Robot robot = new Robot(0, 0, 0);
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String dir = st.nextToken();
            int move = Integer.parseInt(st.nextToken());
            
            if(dir.equals("MOVE")) {             
                int nx = robot.x + dx[robot.look] * move;
                int ny = robot.y + dy[robot.look] * move;
                
                if(outRange(nx, ny)) {
                    robot.x = -1;
                    robot.y = -1;
                    break;
                }
                
                robot.x = nx;
                robot.y = ny;                
            } else {
                if(move == 0) {
                    robot.look = (robot.look + 3) % 4;
                } else {
                    robot.look = (robot.look + 1) % 4;
                }
            }
        }
        
        if(robot.x == -1 && robot.y == -1){
            System.out.println("-1");
        }else        
            System.out.println(robot.y + " " + robot.x);
    }
    
    static boolean outRange(int x, int y){
        return x < 0 || y < 0 || x >= M || y >= M;
    }
    
    static class Robot {
        int look, x, y;
        
        public Robot(int x, int y, int look){
            this.look = look;
            this.x = x;
            this.y = y;
        }
        
        public String toString(){
            return look + " " + x + " " + y;
        }
    }
}
