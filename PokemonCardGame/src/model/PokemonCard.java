package model;

public class PokemonCard extends Card {
    public PokemonCard(String name, int damage, String imagePath) {
        super(name, damage, imagePath);
    }

    @Override
    public void displayCardInfo() {
        System.out.println("Pokemon Card: " + getName() + ", Damage: " + getDamage());
    }

    @Override
    public String toString() {
        return "PokemonCard{name='" + getName() + '\'' + ", damage=" + getDamage() + '}';
    }
}

