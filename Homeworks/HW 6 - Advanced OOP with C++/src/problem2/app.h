#ifndef PROBLEM2_APP_H
#define PROBLEM2_APP_H

class App {
public:
    App(std::istream& is, std::ostream& os);
    void run();
private:
    std::istream& is;
    std::ostream& os;
};

#endif //PROBLEM2_APP_H
