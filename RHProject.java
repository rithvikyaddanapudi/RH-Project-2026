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

    // Slot 0 = Weapon, Slot 1 = Armor
    String[] equipment = new String[2];
    String[] inventory = new String[3];

    Player(String startingName, int startingHealth, int startingMaxHealth, int startingBaseAttack){
        name = startingName;
        health = startingHealth;
        maxHealth = startingMaxHealth;
        baseAttack = startingBaseAttack;
    }

    void takeDamage(int damageAmount){ //Will adjust Players heal based on damage taken
        health = health - damageAmount;
        if (health < 0){
            health = 0;
        }   
    }

    void heal(int healAmount){ //Will adjust Players health based on healing
        health = health + healAmount;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    void showStats(){ //Will show the current stats of the player
        System.out.println("-------------------\nPlayer Stats:\nName: " + name 
                        + "\nHealth: " + health + " / " + maxHealth 
                        + "\nAttack: " + baseAttack
                        + "\nWeapon: " + equipment[0]
                        + "\nArmor: " + equipment[1]
                        + "\nInventory: \n" + inventory[0]  + "\n" + inventory[1] + "\n" + inventory[2]
                        + "\n-------------------");
    }

}