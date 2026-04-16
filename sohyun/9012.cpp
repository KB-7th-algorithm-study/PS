#include <iostream>
#include <stack>
#include <string>
using namespace std;

int main()
{
    int T = 0;
    string str = "";
    cin >> T;
    while(T--){
        stack<char> result;
        cin >> str;
        for(int i = 0; i < str.length(); i++){
            if(result.empty()){
                result.push(str[i]);
            }else{
                if(result.top() == '(' && str[i] == ')')
                    result.pop();
                else
                    result.push(str[i]);
            }
        }
        if(result.empty()) 
            cout << "YES" << '\n';
        else
            cout << "NO" << '\n';
    }
    return 0;
}