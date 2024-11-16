import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner s = new Scanner(System.in);
    boolean gameGoing = true;
    int day = 1;
    int playerHealth = 100;
    int playerHunger = 100;
    int accountBalance = 100;
    int hpCount = 0;
    String playerName = "";
    ArrayList<String> inventory = new ArrayList<>();

    // warm welcome
    public void printWelcome() throws InterruptedException {
        System.out.println("Please enter your name here...");
        playerName = s.nextLine();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(playerName + ", You are a adventurer of [Deep Valley Village]");
        System.out.println("There was a folklore that people always disappear in this village");
        System.out.println("because of the monster inside the cave that is not allowed to enter...");

        Thread.sleep(3000);

        playGame();
    }

    // Game start
    public void playGame() throws InterruptedException {
        while (gameGoing) {
            // check hunger
            if (playerHunger <= 0) {
                gameGoing = false;
                gameOver("hungry");
                return;
            }
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("It is day " + day + ",  what do you want to do?\n");
            System.out.println("1=[Shop]  2=[Status]  3=[Work]  4=[Swamp]  5=[KFC]  6=[Cave]");
            int choice = s.nextInt();

            switch (choice) {
                case 1 :
                    shop();
                    break;
                case 2 :
                    checkStatus();
                    break;
                case 3 :
                    work();
                    break;
                case 4 :
                    swamp();
                    break;
                case 5 :
                    if (accountBalance >= 20) {
                        kfc();
                    } else {
                        System.out.println("You don't have enough money to eat!");
                    }
                    break;
                case 6 :
                    gameGoing = false;
                    cave();
                    break;
                default :
                    System.out.println("Invalid choice. Try again.");
            }
        }

    }

    // Shop
    public void shop() throws InterruptedException {
        boolean buying = true;

        while (buying) {
            String great = inventory.contains("Great Sword") ? "" : "2=[Great Sword $700]\t";
            String ak = inventory.contains("Ak47") ? "" : "3=[Ak47 $5000]\t";

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Which item would you like to buy?");
            System.out.println("\nYour gold coins: " + accountBalance + "$");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1=[Health Potion $200]\t" + great + ak + "4=[return]");
            int shopChoice = s.nextInt();

            switch (shopChoice) {
                case 1 -> {
                    if (accountBalance >= 200) {
                        accountBalance -= 200;
                        inventory.add("Health Potion");
                        System.out.println("You have purchased Health Potion successfully");
                        hpCount++;
                    } else {
                        System.out.println("You do not have enough money...");
                    }
                }
                case 2 -> {
                    if (accountBalance >= 700) {
                        accountBalance -= 700;
                        inventory.add("Great Sword");
                        System.out.println("You have purchased Great sword successfully");
                    } else {
                        System.out.println("You do not have enough money...");
                    }
                }
                case 3 -> {
                    if (accountBalance >= 5000) {
                        accountBalance -= 5000;
                        inventory.add("Ak47");
                        System.out.println("You have purchased Ak47 successfully");
                    } else {
                        System.out.println("You do not have enough money...");
                    }
                }
                case 4 -> {
                    buying = false;
                    playGame();
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // check Status
    public void checkStatus() throws InterruptedException {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Your hp: " + playerHealth);
            System.out.println("Your satisfactory level: " + playerHunger + "%");
            System.out.println("Your gold coins: $" + accountBalance);
            System.out.println("Your inventory: " + inventory);
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("\n1=[rest]\t2=[return]");
            int statusChoice = s.nextInt();

            switch (statusChoice) {
                case 1 -> {
                    validChoice = true;
                    rest();
                }
                case 2 -> {
                    validChoice = true;
                    playGame();
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // working
    public void work() throws InterruptedException {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Who would you like to help today?");
            System.out.println("\n1=[Marry]  2=[Tom]  3=[Joseph]  4=[Tracey]");
            int workChoice = s.nextInt();
            System.out.println("-----------------------------------------------------------------------------------------");

            switch (workChoice) {
                case 1 -> {
                    validChoice = true;
                    accountBalance += 100;
                    playerHunger -= 15;
                    System.out.println("You helped Marry to take care of her flowers");
                    System.out.println("you feel hungry, player satisfactory leve -15%");
                    System.out.println("100$ received");
                    rest();
                }
                case 2 -> {
                    validChoice = true;
                    accountBalance += 180;
                    playerHunger -= 20;
                    System.out.println("You helped Tom to build his new house");
                    System.out.println("you feel hungry, player satisfactory leve -20%");
                    System.out.println("180$ received");
                    rest();
                }
                case 3 -> {
                    validChoice = true;
                    accountBalance += 150;
                    playerHunger -= 17;
                    System.out.println("You sold all the fish to joe after fishing with him");
                    System.out.println("you feel hungry, player satisfactory leve -17%");
                    System.out.println("150$ received");
                    rest();
                }
                case 4 -> {
                    validChoice = true;
                    accountBalance += 220;
                    playerHunger -= 25;
                    System.out.println("You taught Tracey how to code");
                    System.out.println("you feel hungry, player satisfactory leve -25%");
                    System.out.println("220$ received");
                    rest();
                }
                default -> System.out.println("Invalid choice, choose again");
            }
        }
    }

    // kfc
    public void kfc() throws InterruptedException{
        accountBalance -= 20;
        playerHunger += 50;

        // max hunger = 100
        if (playerHunger > 100) {
            playerHunger = 100;
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("After eating KFC, you feel satisfied...");
        System.out.println("your satisfaction level: " + playerHunger +"%");
        System.out.println("gold coins -50$");
        playGame();
    }

    // swamp
    public void swamp() throws InterruptedException{
        boolean validChoice = false;
        int slimeHealth = 120;
        boolean sword = inventory.contains("Great Sword");
        boolean ak47 = inventory.contains("Ak47");
        boolean hp;
        String ownSword = sword ? "2=[Great Sword]  " : "";
        String ownAk = ak47 ? "3=[ak47]" : "" ;
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("A slime is jumping towards you");
    
        while (!validChoice) {
            hp = inventory.contains("Health Potion");
            if (playerHealth <= 0) {
                gameOver("slime");
            }

            System.out.println("\n1=[atk]  2=[flee]  3=[health potion x " + hpCount + "]");
            int swampChoice = s.nextInt();
            System.out.println("-----------------------------------------------------------------------------------------");

            switch (swampChoice) {
                case 1 -> {
                            System.out.println("1=[kick]  " + ownSword + ownAk + "  4=[return]");
                        int atkChoice = s.nextInt();

                        if (atkChoice ==  1) {
                            slimeHealth -= 30;
                            playerHealth -= 15;
                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println("slime has jumped on you...");
                            System.out.println("your kick deal 30 damage to the slime");
                            System.out.println("Slime hp:" + slimeHealth);
                            System.out.println("Your hp:" + playerHealth);
                        }

                        if (atkChoice == 2 && sword) {
                            slimeHealth -= 60;
                            playerHealth -= 15;
                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println("slime has jumped on you...");
                            System.out.println("your swing deal 60 damage to the slime");
                            System.out.println("Slime hp:" + slimeHealth);
                            System.out.println("Your hp:" + playerHealth);
                        }

                        if (atkChoice == 2 && !sword) {
                            System.out.println("invalid number");
                        }

                        if (atkChoice == 3 && ak47) {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println("you just one shot the slime...");
                            slimeHealth -= 1000;
                        }

                        if (atkChoice == 3 && !ak47) {
                            System.out.println("invalid number");
                        }

                        // check if slime died
                        if (slimeHealth <= 0) {
                            accountBalance += 450;
                            playerHunger -= 20;
                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println("You have defeated the slime");
                            System.out.println("+450 gold coins");
                            System.out.println("you feel hungry, player satisfactory leve -20%");
                            validChoice = true;
                            rest();
                        }

                        if (atkChoice == 4) {
                            continue;
                        }

                        if (atkChoice != 1 && atkChoice != 2 && atkChoice != 3) {
                            System.out.println("Invalid choice, choose again");
                        }
                }
                case 2 -> {
                    playerHunger -= 20;
                    System.out.println("You have escaped from the swamp...");
                    System.out.println("player satisfactory leve -20%");
                    validChoice = true;
                    playGame();
                }
                case 3 -> {
                    if (hp) {
                        hpCount--;
                        inventory.remove("Health Potion");
                        playerHealth += 55;
                        System.out.println("Your current hp: " + playerHealth);
                        if (playerHealth > 100) {
                            playerHealth = 100;
                        }
                    } else {
                        System.out.println("You have no health potion...");
                    }
                }
                default -> System.out.println("Invalid choice, choose again");
            }
        }

    }

    // cave
    public void cave() throws InterruptedException {
        int tomHealth = 180;
        int traceyHealth = 150;
        boolean enter = false;
        boolean enterRoom = false;
        boolean tomDied = false;
        boolean traceyDied = false;
        boolean validChoice = false;
        boolean validChoice2 = false;
        boolean sword = inventory.contains("Great Sword");
        boolean ak47 = inventory.contains("Ak47");
        String ownSword = sword ? "1=[Great Sword]  " : "";
        String ownAk = ak47 ? "2=[ak47]  " : "" ;

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("It seems to be the front door of the cave...");
        System.out.println("Are you sure you want to get in?\n");
        System.out.println("1=[Yes] 2=[No]");

        while (!validChoice) {
            int choice = s.nextInt();

            if (choice == 1 && sword) {
                validChoice = true;
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("You slowly opened the door, but there is so dark inside...");
                System.out.println("When you try to step forward, the door closed");
                System.out.println("Looks like you cannot leave here now...");
                enter = true;
            }

            if (choice == 1 && !sword) {
                validChoice = true;
                System.out.println("please bring a sword and come back");
                gameGoing = true;
                playGame();
            }

            if (choice == 2) {
                validChoice = true;
                gameGoing = true;
                playGame();
            }

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid number");
            }
        }

        while (enter) {
            System.out.println("\n1=[Yell]  2=[Do nothing]");
            int doSomething = s.nextInt();

            switch (doSomething) {
                case 1 -> {
                    enter = false;
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println(playerName + ": Anyone hear me?");
                    System.out.println("[You heard someone nearby yelling]\n");
                    Thread.sleep(4000);
                    System.out.println(playerName + ": Oh, tracey, why are you here?");
                    System.out.println("Tracey: I have no idea...but we should find a way to leave\n");
                    Thread.sleep(4000);
                    System.out.println(playerName + ": Yea, but how?");
                    System.out.println("Tracey: follow me, I know the way");
                    Thread.sleep(4000);
                    System.out.println("You followed Tracey to a small room");
                    Thread.sleep(4000);
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println("Tracey: We are not welcome of outsiders, HAHAHAHAHA!");
                    System.out.println("When the light has switched on, you saw Tom and Tracey smiling at you");
                    enterRoom = true;
                }
                case 2 -> {
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println("[Nothing happened inside the darkness]");
                }
            }
        }

        while (!validChoice2 && enterRoom) {
            boolean hp = inventory.contains("Health Potion");
            String tomOption = tomDied ? "" : "1=[Tom]  ";
            String traceyOption = traceyDied ? "" : "2=[Tracey]";
            System.out.println("\n1=[Atk]  2=[Health Potion x " + hpCount + "]");
            int choice2 = s.nextInt();

            switch (choice2) {
                case 1 -> {
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println(ownSword + ownAk + "3=[return]");
                    int atkChoice = s.nextInt();

                    switch (atkChoice) {
                        case 1 -> {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println(tomOption + traceyOption);
                            int atkWho = s.nextInt();

                            if (tomDied && atkWho == 1) {
                                System.out.println("invalid number");
                            }

                            if (atkWho == 1) {
                                tomHealth -= 60;

                                if (tomHealth <= 0) {
                                    tomDied = true;
                                    System.out.println("Tom was died");
                                }
                            }

                            if (traceyDied && atkWho == 2) {
                                System.out.println("invalid number");
                            }

                            if (atkWho == 2) {
                                traceyHealth -= 60;

                                if (traceyHealth <= 0) {
                                    traceyDied = true;
                                    traceyHealth = 0;
                                    System.out.println("Tracey was died");
                                }
                            }

                            if (tomDied || traceyDied) {
                                playerHealth -= 30;
                            } else {
                                playerHealth -= 60;
                            }

                            // check both died
                            if (tomDied && traceyDied) {
                                ending();
                                return;
                            }

                            System.out.println("-----------------------------------------------------------------------------------------");
                            System.out.println("Your current hp: " + playerHealth);
                            System.out.println("Tom's current hp: " + tomHealth);
                            System.out.println("Tracey's current hp: " + traceyHealth);
                        }
                        case 2 -> {
                            if (ak47) {
                            System.out.println("[Gunshot echos around the cave]");
                            System.out.println("You used Ak47 to shut them down...");
                            ending();
                            return;
                            } else {
                                System.out.println("invalid number");
                            }
                        }
                    }

                }
                case 2 -> {
                    System.out.println("-----------------------------------------------------------------------------------------");
                    if (hp) {
                        inventory.remove("Health Potion");
                        hpCount--;
                        playerHealth += 55;
                        if (playerHealth > 100) {
                            playerHealth = 100;
                        }
                        System.out.println("Your current hp: " + playerHealth);
                    } else {
                        System.out.println("You have no health potion...");
                    }
                }
            }

            // check if player died
            if (playerHealth <= 0) {
                validChoice2 = true;
                gameOver("villager");
            }
        }
    }

    // gameOver
    public void gameOver(String reasonOfDying) {
        String showReason = switch (reasonOfDying) {
            case "hungry" -> "You starved to death...";
            case "villager" -> "You are killed by Tom and Tracey...";
            case "slime" -> "You are killed by a Slime...";
            case "yourself" -> "You are killed by yourself...";
            default -> "";
        };

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Game Over! " + showReason);
    }

    // rest
    public void rest() throws InterruptedException {
        Thread.sleep(1500);

        System.out.println("\nYou are tired so you decided to sleep in a hotel nearby");
        day += 1;
        playerHunger -= 10;
        playerHealth = 100;
        Thread.sleep(1500);
        playGame();
    }

    // real ending
    public void ending() throws InterruptedException {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Tom and Tracey were died");
        System.out.println("Their bodies laid down quietly\n");
        Thread.sleep(4000);
        System.out.println(playerName + ": I think I should leave here asap");
        System.out.println("You want to leave the cave, however, you cannot find a way to leave");
        Thread.sleep(6000);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Three week passed, you still cannot find the way to leave");
        System.out.println("You have no choice but to eat the corpse of them to survive");
        Thread.sleep(6000);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Two months passed, you still cannot find the way to leave");
        System.out.println("You are too hungry so you decided to eat yourself");
        Thread.sleep(6000);
        gameOver("yourself");
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.printWelcome();
    }
}