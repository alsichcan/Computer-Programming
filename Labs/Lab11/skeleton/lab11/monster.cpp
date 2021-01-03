#include "monster.h"

Monster::Monster(hp_t hp, hp_t damage, MonsterType type, MonsterType critical_to)
        : hp(hp), damage(damage), type(type), critical_to(critical_to) { }

WaterMon::WaterMon() : Monster(500, 10, waterMon, fireMon) {}
FireMon::FireMon() : Monster(300, 20, fireMon, grassMon) {}
GrassMon::GrassMon() : Monster(600, 10, grassMon, waterMon) {}

hp_t Monster::get_hp() const {
    return hp;
}

MonsterType Monster::get_type() const {
    return type;
}

void Monster::decrease_health(hp_t attack_damage) {
    hp -= attack_damage;
}

void Monster::attack(Monster *attacked_monster) {
    if (attacked_monster->get_type() == critical_to) {
        critical_attack(attacked_monster);
    } else {
        attacked_monster->decrease_health(damage);
    }
}

void Monster::critical_attack(Monster *attacked_monster) {

}
