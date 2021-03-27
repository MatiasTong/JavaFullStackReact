import java.util.Random;

public class Goblin {
    private int HP;
    private int attackPower;
    private int defensePower;


    public Goblin(){
        Random rGen = new Random();
        HP = rGen.nextInt(6) + 5;
        attackPower = rGen.nextInt(2) + 2;
        defensePower = rGen.nextInt(1) + 1;
    }

    

    public void attack(Hero hero){
        double damage = (double) attackPower / (double) hero.getDefensePower();
        double newHeroHP = hero.getHP() - damage;
        hero.setHP(newHeroHP);

    }

    public boolean isAlive(){
        if(HP > 0){
            return true;
        } else{
            return false;
        }
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    @Override
    public String toString() {
        int HPAdjusted;

        if(HP<0){
            HPAdjusted = 0;
        } else {
            HPAdjusted = HP;
        }

        return "Goblin {" +
                " HP: " + HPAdjusted +
                ", attack: " + attackPower +
                ", defense: " + defensePower +
                " }";
    }
}
