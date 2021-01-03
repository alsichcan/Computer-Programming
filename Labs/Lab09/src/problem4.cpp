//
// Created by Park on 2020-11-11.
//

#include <cstdio>
#include <iostream>

int main(){
    char str[100];
    std::cin >> str;

    printf("\t[%s]\n", str);
    printf("\t[%10s]\n", str);
    printf("\t[%-10s]\n", str);
}