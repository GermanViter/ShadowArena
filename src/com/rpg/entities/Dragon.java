public class Dragon extends Monster {

    public Dragon() {
        super("Elder Dragon", 300, 300);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " breathes ancient fire on " + target.getName() + "!");
        target.takeDamage(40);
    }
}
