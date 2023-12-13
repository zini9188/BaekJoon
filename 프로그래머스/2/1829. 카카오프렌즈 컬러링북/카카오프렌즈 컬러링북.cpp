#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int M, N, cnt, visited[101][101];
vector<int> dirX, dirY;

int dfs(int x, int y, vector<vector<int>> board){
    visited[x][y] = 1;
    
    for(int i = 0; i < 4; i++){
        int X = x + dirX[i];
        int Y = y + dirY[i];
        if(X >= 0 && X < M && Y >= 0 && Y < N && board[X][Y] == board[x][y] && visited[X][Y] == 0){ 
            dfs(X, Y, board);
            cnt++;
        }
    }    
    return cnt;
}

vector<int> solution(int m, int n, vector<vector<int>> picture) {
    int number_of_area = 0;
    int max_size_of_one_area = 0;
    M = m;
    N = n;
    dirX = {1,0,-1,0};
    dirY = {0,-1,0,1};
    
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            visited[i][j] = 0;
        }
    }
    
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(picture[i][j] != 0 && !visited[i][j]){             
                cnt = 1;            
                max_size_of_one_area = max(max_size_of_one_area, dfs(i, j, picture));
                number_of_area++;     
            }
        }        
    }
    
    return {number_of_area, max_size_of_one_area};
}