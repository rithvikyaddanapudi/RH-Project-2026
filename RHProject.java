import java.util.*;
public class RHProject{
    public static void main(String []args){
        Scanner input = new Scanner(System.in);

        System.out.println("Hello Player!\nWelcome to the dungeon!");
        String playerAnswerForNameVerify = "";
        String playerName = "";
        
        do {
            System.out.println("Please enter your name: ");
            playerName = input.nextLine();

            System.out.println("Your name is " + playerName + ", is that correct? [Yes or No]");
            playerAnswerForNameVerify = input.nextLine();

            if (playerAnswerForNameVerify.equalsIgnoreCase("no")){
            System.out.println("-----------------");
        }   else if (playerAnswerForNameVerify.equalsIgnoreCase("yes")){
            System.out.println("Thank you for confirming!");
        }   else{
            System.out.println("Invalid Input");
        }

        } while (!(playerAnswerForNameVerify.equalsIgnoreCase("yes")));

        Player hero = new Player(playerName, 100, 100, 5);

        System.out.println("Well " + playerName + " to start you out I'll give you some tools.");
        hero.armorEquip("Leather Tunic", 3);
        hero.weaponEquip("Rusted Dagger", 3);
        System.out.println("Use them wisely and try not to die too fast.\nGood luck " + playerName);



        input.close();
    }
}

class Player {

    String name;
    int health;
    int maxHealth;
    int baseAttack;
    int defense;
    int weaponAttack = 0;

    // Slot 0 = Weapon, Slot 1 = Armor
    String[] equipment = new String[2];
    String[] inventory = new String[3];

    Player(String startingName, int startingHealth, int startingMaxHealth, int startingBaseAttack){
        name = startingName;
        health = startingHealth;
        maxHealth = startingMaxHealth;
        baseAttack = startingBaseAttack;
        defense = 0;
    }

    void takeDamage(int damageAmount){ //Will adjust Players heal based on damage taken
        damageAmount = damageAmount - defense;
        if (damageAmount < 0){
            damageAmount = 0;
        }
        health = health - damageAmount;
        if (health < 0){
            health = 0;
        }   
        if (health == 0){
            System.out.println(name + " has been killed.\n----Game over.----");
        }   else{
            System.out.println(name + " took " + damageAmount + " damage.");
        }
    }
    public void addItem(String itemName){
        for (int itemStart = 0; itemStart < inventory.length; itemStart++){
            if (inventory[itemStart] == null){
                inventory[itemStart] = itemName;
                return;
            }
        }
        System.out.println("Inventory is Full!!");
    }

    public void usePotion(){
        for (int i = 0; i < inventory.length; i++){
            if ("Health Potion".equalsIgnoreCase(inventory[i])){
                inventory[i] = null;
                heal(30);
                return;
            }
        }
        System.out.println("You have no Health Potions left!");
    }

    void heal(int healAmount){ //Will adjust Players health based on healing
        int oldHealth = health;
        health = health + healAmount;
        
        if (health > maxHealth){
            health = maxHealth;
        }

        int actualHealed =  health - oldHealth;
        
        System.out.println(name + " healed " + actualHealed + " points of health.");
    }

    void showStats(){ //Will show the current stats of the player
        System.out.println("-------------------\nPlayer Stats:\nName: " + name 
                        + "\nHealth: " + health + " / " + maxHealth 
                        + "\nAttack: " + (baseAttack + weaponAttack)
                        + "\nWeapon: " + equipment[0]
                        + "\nArmor: " + equipment[1]
                        + "\nInventory: \n" + inventory[0]  + "\n" + inventory[1] + "\n" + inventory[2]
                        + "\n-------------------");
    }

    void armorEquip(String armorName, int armorDefense){
        equipment[1] = armorName;
        defense = armorDefense;
        System.out.println("You just equipped " + armorName + " with defense of " + armorDefense);
    }

    void weaponEquip(String weaponName, int weaponBonusDamage){
        equipment[0] = weaponName;
        weaponAttack = weaponBonusDamage;
        System.out.println("You just equipped " + weaponName + " with attack power " + weaponBonusDamage);
    }

    int getTotalAttack(){
        return baseAttack + weaponAttack;
    }

    boolean isAlive(){
        return health > 0;
    }
}

class Monster {

    String name;
    int health;
    int maxHealth;
    int baseAttack;
    int resistance;

     Monster(String startingName, int startingHealth, int startingMaxHealth, int startingBaseAttack, int startingResistance){
        name = startingName;
        health = startingHealth;
        maxHealth = startingMaxHealth;
        baseAttack = startingBaseAttack;
        resistance = startingResistance;
     }

    void takeDamage(int damageAmount){
        damageAmount = damageAmount - resistance;
        if (damageAmount < 0){
            damageAmount = 0;
        }
        health = health - damageAmount;
        if (health < 0){
            health = 0;
        }
        if (health == 0){
            System.out.println(name + " has been killed.");
        }   else{
            System.out.println(name + " took " + damageAmount + " damage.");
        }
     }

     boolean isAlive(){
        return health > 0;
     }
}