import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hero {
    private double HP;
    private final double originalHP;
    private int attackPower;
    private int defensePower;
    private ArrayList<Integer> potions;
    private int gold;
    private int steps;
    private int level;

    public Hero(){
        Random rGen = new Random();
        HP = (double)rGen.nextInt(11) + 20;
        originalHP = HP;
        attackPower = rGen.nextInt(3) + 1;
        defensePower = rGen.nextInt(5) + 1;
        potions = new ArrayList<Integer>(Arrays.asList(2,2,2,2,2));
        gold = 0;
        level = 1;
    }

    public void walk(){
        steps+=1;
    }

    public void attack(Goblin goblin){
        int newGoblinHP = (int)Math.floor(goblin.getHP() - attackPower/goblin.getDefensePower());
        goblin.setHP(newGoblinHP);
    }

    public void earnGold(int gold){
        this.gold += gold;
    }

    public void usePotion(){
        potions.remove(potions.size()-1);
        HP = originalHP;
    }

    public boolean buyPotion(){
        if(gold >= 4 && potions.size() < 5){
            gold-=4;
            potions.add(2);
            return true;
        }else{
            return false;
        }
    }

    public boolean isAlive(){
        if(HP > 0){
            return true;
        } else{
            return false;
        }
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
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

    public ArrayList<Integer> getPotions() {
        return potions;
    }

    public void setPotions(ArrayList<Integer> potions) {
        this.potions = potions;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "HP=" + Math.round(HP) +
                ", attackPower=" + attackPower +
                ", defensePower=" + defensePower +
                ", potions=" + potions +
                ", gold=" + gold +
                ", steps=" + steps +
                '}';
    }

    public String toStringAbbrev() {
        return "Hero { " +
                "HP: " + Math.round(HP) +
                ", attack: " + attackPower +
                ", defense: " + defensePower +
                " }";
    }
}
