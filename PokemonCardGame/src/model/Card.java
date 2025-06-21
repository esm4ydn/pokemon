package model;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class Card {
    private String name;
    private int damage;
    private ImageIcon image;

    public Card(String name, int damage, String imagePath) {
        this.name = name;
        this.damage = damage;
        this.image = new ImageIcon(imagePath);
    }

    // Getter ve Setter metodları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(String imagePath) {
        this.image = new ImageIcon(imagePath);
    }

    public ImageIcon getResizedImage(int width) {
        Image img = image.getImage();
        int height = (int) ((double) img.getHeight(null) / img.getWidth(null) * width);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(resizedImage);
    }

    @Override
    public String toString() {
        return "Card{name='" + name + '\'' + ", damage=" + damage + '}';
    }

    // Abstract method
    public abstract void displayCardInfo();
}
