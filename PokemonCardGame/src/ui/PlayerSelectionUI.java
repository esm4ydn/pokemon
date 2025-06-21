package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSelectionUI extends JFrame {
    public PlayerSelectionUI() {
        setupUI();
    }

    private void setupUI() {
        setTitle("Pokemon Card Game - Player Selection");
        setSize(1424, 818);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Arka plan paneli
        BackgroundPanelStart mainPanelStart = new BackgroundPanelStart("resources/pokemon-wallpaper-1024x768.jpg");
        mainPanelStart.setLayout(new GridBagLayout()); // GridBagLayout kullanarak bileşenleri ortalayalım

        // GridBagLayout ayarları
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Bileşenler arasında boşluk bırakma
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Başlık etiketi
        JLabel titleLabel = new JLabel("OYUNCU MOD SEÇİMİ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBackground(Color.BLACK);
        mainPanelStart.add(titleLabel, gbc);

        // Butonlar için GridBagConstraints ayarları
        gbc.gridy++;
        gbc.gridwidth = 1;

        JButton playerVsComputerButton = new JButton("Oyuncu vs Bilgisayar");
        mainPanelStart.add(playerVsComputerButton, gbc);

        gbc.gridx++;
        JButton computerVsComputerButton = new JButton("Bilgisayar vs Bilgisayar");
        mainPanelStart.add(computerVsComputerButton, gbc);

        // Buton olayları
        playerVsComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI(false); // Oyunun başlatılması
                dispose(); // Bu pencerenin kapatılması
            }
        });

        computerVsComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI(true); // Oyunun başlatılması
                dispose(); // Bu pencerenin kapatılması
            }
        });

        add(mainPanelStart);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PlayerSelectionUI();
    }
}

// Arka plan paneli sınıfı
class BackgroundPanelStart extends JPanel {
    private Image backgroundImage;

    public BackgroundPanelStart(String fileName) {
        try {
            backgroundImage = new ImageIcon(fileName).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
