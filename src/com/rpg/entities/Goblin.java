public class Goblin extends Monster {

    public Goblin() {
        super("Sneaky Goblin", 50, 50);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " bites " + target.getName() + " with dirty teeth!");
        target.takeDamage(10);
    }
}
