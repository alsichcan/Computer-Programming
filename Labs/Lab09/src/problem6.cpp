//
// Created by Park on 2020-11-11.
//

#incldue <iostream>

int main(){
    int len;
    std::cin >> len;
    int A[len][len];
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            printf("A[%d][%d]=", i, j);
            std::cin >> A[i][j];
        }
    }

    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            printf("%d ", A[i][j]);
        }
        printf("\n");
    }
}
