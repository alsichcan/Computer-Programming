# How to use

1. copy and paste `test.in` to `(your_path)/HW5/problem2/mytest`.
2. replace `main()` with the following code snippet.

```c++
int main() {
    while (true) {
        int question_number;
        std::cin >> question_number;
        if (question_number == 1) {
            int a, b;
            std::cin >> a >> b;
            std::tie(a, b) = number_fight(a, b);
            std::cout << a << " " << b << std::endl;
        } else if (question_number == 2) {
            int a, b;
            std::cin >> a >> b;
            std::tie(a, b) = number_vs_number(a, b);
            std::cout << a << " " << b << std::endl;
        } else if (question_number == 3 || question_number == 4) {
            auto a = scan_player();
            auto b = scan_player();
            std::multiset<int> a_, b_;
            if (question_number == 3) {
                tie(a_, b_) = player_battle(
                        a.first, a.second, b.first, b.second
                );
            } else {
                tie(a_, b_) = player_vs_player(
                        a.first, a.second, b.first, b.second
                );
            }
            print_multiset(a_);
            print_multiset(b_);
        } else if (question_number == 5 || question_number == 6) {
            int num_players;
            std::cin >> num_players;
            std::vector<player> players;
            for (int i = 0; i < num_players; i++) {
                players.push_back(scan_player());
            }
            int winner_id;
            if (question_number == 5) {
                winner_id = tournament(players);
            } else {
                winner_id = steady_winner(players);
            }
            std::cout << winner_id << std::endl;
        } else {
            return 0;
        }
    }
    return 0;
}
```

3. run the following commands in terminal. (not sure if it works in Windows too...)

```bash
$ cd (your_path)/HW5/problem2
$ (your_path)/HW5/cmake-build-debug/problem2/problem2 < mytest/test.in > mytest/mytest.out
```

5. compare `mytest.out` with `test.out`.
6. **make sure to undo step 2 before submitting.**
