#include <iostream>

class Point {
public:
    Point(int x, int y)
      :x(x), y(y) {}

    int getX() const {
      return x;
    }

    int getY() const {
      return x;
    }

private:
  int x, y;
};


void draw(int x, int y, int x2, int y2) {
    std::cout << "from : " << x << " " << y << std::endl;
    std::cout << "to : " << x2 << " " << y2 << std::endl;
}

void draw(Point p, Point p2) {
}

int main() {
    Point p(1, 5), p2(2, 4);

    draw(p.getX(), p.getY(), p2.getX(), p2.getY());

    // TODO : draw(p, p2);
    
    return 0;
}