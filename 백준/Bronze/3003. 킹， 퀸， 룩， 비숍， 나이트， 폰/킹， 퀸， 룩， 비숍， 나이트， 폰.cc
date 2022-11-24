#include <iostream>

using namespace std;
enum chess {
    KING = 1, 
    QUEEN = 1, 
    ROOK = 2, 
    BISHOP = 2, 
    KNIGHT= 2, 
    PAWN = 8
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int king, queen, rook, bishop, knight, pawn;

    cin >> king >> queen >> rook >> bishop >> knight >> pawn;

    cout << KING - king << " " << QUEEN - queen << " " << ROOK - rook << " " << BISHOP - bishop << " " << KNIGHT - knight << " " << PAWN - pawn;

}