#include <iostream>

class Point {
public:
    Point(int x, int y)
      :x(x), y(y) {}

    int getX() const {
      return x;
    }

    int getY() const {
      return y;
    }

private:
  int x, y;

    friend std::ostream& operator <<(std::ostream& os, const Point& point){
        return os << point.getX() << " " << point.getY();
    }
};


void draw(int x, int y, int x2, int y2) {
    std::cout << "from : " << x << " " << y << std::endl;
    std::cout << "to : " << x2 << " " << y2 << std::endl;
}

void draw(Point p, Point p2) {
    std::cout << "from : " << p << std::endl;
    std::cout << "to : " << p2 << std::endl;
}

int main() {
    Point p(1, 5), p2(2, 4);

    draw(p.getX(), p.getY(), p2.getX(), p2.getY());

    // TODO : draw(p, p2);
    draw(p, p2);
    
    return 0;
}