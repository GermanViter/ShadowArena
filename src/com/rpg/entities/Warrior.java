public class Warrior extends Hero {

    public Warrior(String name) {
        super(name, 150, 150);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " swings a mighty sword at " + target.getName() + "!");
        target.takeDamage(20);
    }
}
