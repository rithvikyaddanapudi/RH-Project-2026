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
        hero.addItem("Health Potion");
        System.out.println("Use them wisely and try not to die too fast.\nGood luck " + playerName);

        Monster[] bosses ={
            new Monster("Goblin Leader", 50, 50, 5, 0),
            new Monster("Goblin Cheiftain", 60, 60, 10, 3),
            new Monster("Skeleton Overlord", 60, 60, 15, 0),
            new Monster("Bloodfang Werewolf", 110, 110, 12, 5),
            new Monster("Void Wyrm", 130, 130, 20, 10),
            new Monster("The Hollow Lich", 100, 100, 23, 6),
            new Monster("The Forgotten Emporer", 150, 150, 25, 15)
        };

        String[] floorWeapons = {
            "Dagger", "Iron sword", "Steel sword", "Steel Longsword","Bloodfang Scythe", "Void Reaver", "Soulrender"
        };
        int [] floorWeaponsDamage = {5, 7, 10, 13, 17, 20, 23};

        String[] floorArmor = {
            "Reinforced Tunic", "Chainmail armor", "Bone Carapace", "Werewolf Cloak", "Void Carapace", "Spectral Cape", "Crownguard Plate"
        };
        int [] floorArmorDefense = {5, 7, 9, 13, 15, 19, 23};

        for (int floor = 0; floor < bosses.length; floor++){
            Monster boss = bosses[floor];

            System.out.println("======================");
            System.out.println("Entering floor " + (floor + 1) + "!");
            System.out.println("You encountered boss: " + boss.name + "\nHP: " + boss.health);
            System.out.println("======================");

            while (hero.isAlive() && boss.isAlive()){
                System.out.println("\nYour HP: " + hero.health + "/" + hero.maxHealth + " | " + boss.name + "HP: " + boss.health + "/" + boss.maxHealth);
                System.out.println("========\nChose your action:\n [1] Attack \n[2] Heal \n[3] Show stats \n[Input number only]");
                String choice = input.nextLine();

                switch (choice){
                    
                    case "1":
                        System.out.println("You attack " + boss.name + "!");
                        boss.takeDamage(hero.getTotalAttack());
                    break;

                    case "2":
                        hero.usePotion();
                    break;

                    case "3":
                        hero.showStats();
                    continue;

                    default: 
                        System.out.println("Invalid input! You tripped and lost your turn.");
                    break;
                }

                if (boss.isAlive()){
                    System.out.println(boss.name + " attacks");
                    hero.takeDamage(boss.baseAttack);
                }
            }
            if (!hero.isAlive()){
                break;
            }

            System.out.println("\n** Congrats on completeing Floor" + (floor + 1) + "! **");
            hero.weaponEquip(floorWeapons[floor], floorWeaponsDamage[floor]);
            hero.armorEquip(floorArmor[floor], floorArmorDefense[floor]);
            hero.addItem("Health Potion");
        }

        if (hero.isAlive()) {
            System.out.println("\n**************************************************");
            System.out.println("CONGRATULATIONS " + hero.name + "! YOU CLEARED THE DUNGEON!");
            System.out.println("**************************************************");
        } else {
            System.out.println("\nYou perished in the dungeon. Better luck next time!");
        }

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
                System.out.println("You just picked up " + itemName);
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