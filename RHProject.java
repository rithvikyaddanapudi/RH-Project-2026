import java.util.*;
public class RHProject{
    public static void main(String []args){

    }
}

class Player {

    String name;
    int health;
    int maxHealth;
    int baseAttack;

    String[] equiptment = new String[2];
    String[] inventory = new String[3];

    Player(String startingName, int startingHealth, int startingMaxHealth, int startingBaseAttack){
        name = startingName;
        health = startingHealth;
        maxHealth = startingMaxHealth;
        baseAttack = startingBaseAttack;
    }

    void takeDamage(int damageAmount){
        health = health - damageAmount;
        if (health < 0){
            health = 0;
        }   
    }

    void heal(int healAmount){
        health = health + healAmount;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

}