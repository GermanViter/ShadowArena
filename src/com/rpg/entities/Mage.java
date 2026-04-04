public class Mage extends Hero implements Regenerable {

    public Mage(String name) {
        super(name, 100, 100);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " casts a fireball at " + target.getName() + "!");
        target.takeDamage(25);
    }

    @Override
    public void regenerate() {
        if (this.getCurrentHP() < 100) {
            System.out.println(getName() + " uses magic to heal!");
            this.setCurrentHP(100);

        }
    }
}
