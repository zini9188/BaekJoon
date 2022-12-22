#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int uniqueComp(pair<int, int> a, pair<int, int> b) {
    if (a.second == b.second)
        return a.first < b.first;
    return a.second > b.second;
}

int bestAlbumComp(pair<string, int> a, pair<string, int> b) {
    return a.second > b.second;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    map<string, vector<pair<int, int>>> unique;
    map<string, int> bestAlbum;
    int len = genres.size();
    for (int i = 0; i < len; i++) {
        unique[genres[i]].push_back({i, plays[i]});
        bestAlbum[genres[i]] += plays[i];
    }

    for (auto &play: unique) {
        sort(play.second.begin(), play.second.end(), uniqueComp);
    }

    vector<pair<string, int>> v(bestAlbum.begin(), bestAlbum.end());
    sort(v.begin(), v.end(), bestAlbumComp);


    for (const auto &count: v) {
            int index = 0;
            for (auto album: unique[count.first]) {
                answer.push_back(album.first);
                index++;
                if (index >= 2)
                    break;
            }

    }
    return answer;
}