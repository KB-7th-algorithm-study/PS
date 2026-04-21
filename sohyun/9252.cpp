#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    
    string str1 = "", str2 = "", lcs = "";
    int maxLength = 0, endIndex = 0; 
    
    cin >> str1 >> str2;
    int str1Size = str1.size(), str2Size = str2.size();
    int i = str1Size, j = str2Size;
    
    vector<vector<int>> dp(str1Size + 1, vector<int>(str2Size + 1, 0));

    for (int i = 1; i <= str1Size; i++) {
        for (int j = 1; j <= str2Size; j++) {
            if (str1[i - 1] == str2[j - 1]) 
                dp[i][j] = dp[i - 1][j - 1] + 1;
            else 
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }

    while (i > 0 && j > 0) {
        if (str1[i - 1] == str2[j - 1]) {
            lcs += str1[i - 1];
            i--; j--;
        } else if (dp[i - 1][j] >= dp[i][j - 1]) {
            i--;
        } else {
            j--;
        }
    }
    reverse(lcs.begin(), lcs.end());
    cout << dp[str1Size][str2Size] << '\n' << lcs << '\n';

    return 0;
}