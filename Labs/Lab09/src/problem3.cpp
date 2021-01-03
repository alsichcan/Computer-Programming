//
// Created by Park on 2020-11-11.
//

#include <cstdio>
#include <iostream>

int main(){
    int A, B;
    float C;
    std::cin >> A >> B >> C;
    printf("%c %d %7d %07d %x %o %#x %#o\n",A,B,B,B,B,B,B,B);
    printf("%.2f %E", C, C);
}