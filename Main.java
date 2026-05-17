package src;

import src.view.Controls;
import src.engine.CurrencyManager;
import src.engine.Engine;
import src.engine.GameView;
import src.screen.MainMenu;
import src.view.background.Tower;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Запускаем меню
        SwingUtilities.invokeLater(() -> {
            new MainMenu();
        });
    }

    // ЭТОТ МЕТОД ЗАПУСКАЕТ ИГРУ (вызывается из меню)
    public static void startGame() {
        SwingUtilities.invokeLater(() -> {
            CurrencyManager.getInstance();

            Engine engine = Engine.getInstance();


            // Башня справа (основная)
            Tower tower = new Tower(1, 900f, 620f, 100, Tower.TowerType.STONE, 0f);
            tower.setFraction(0);
            engine.spawnObject(tower);


            // Дружественная башня слева
            Tower friendlyTower = new Tower(999, 100f, 620f, 100, Tower.TowerType.STONE, 0f);
            friendlyTower.setFraction(0);
            engine.spawnObject(friendlyTower);


            GameView gameView = new GameView(engine);
            JFrame frame = new JFrame("Tower Battle - Archer Defense");
            frame.setSize(engine.getScreenWidth(), engine.getScreenHeight());
            frame.setLayout(new BorderLayout());
            frame.add(gameView, BorderLayout.CENTER);
            frame.add(new Controls(engine), BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}