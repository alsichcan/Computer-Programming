#ifndef LAB11_MONSTER_H
#define LAB11_MONSTER_H

typedef int hp_t;  // using hp_t = int;

enum MonsterType {
    waterMon = 0,
    fireMon = 1,
    grassMon = 2,
};

class Monster {
private:
    static int num_monsters;
protected:
    hp_t hp;
    hp_t damage;
    MonsterType type;
    MonsterType critical_to;
public:
    Monster(hp_t hp, hp_t damage, MonsterType type, MonsterType critical_to);
    hp_t get_hp() const;
    MonsterType get_type() const;

    void decrease_health(hp_t attack_damage);
    void attack(Monster *attacked_monster);
    void critical_attack(Monster *attacked_monster);
};

class WaterMon : public Monster {
public:
    WaterMon();
};

class FireMon : public Monster {
public:
    FireMon();
};

class GrassMon : public Monster {
public:
    GrassMon();
};

#endif //LAB11_MONSTER_H
