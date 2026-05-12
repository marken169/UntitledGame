package src.view.unit;

import src.engine.GameObject;

import java.awt.*;

/**
 * Всадник на динозавре: спавнится, идёт к башне,
 * останавливается и атакует, пока башня не разрушится.
 */
public class UnitDinoRider extends BaseUnit {

    // Переменные для анимации атаки
    private boolean isAttacking = false;
    private float attackAnimationTime = 0f;
    private final float ATTACK_ANIMATION_DURATION = 0.3f;

    public static Builder builder() {
        return new UnitDinoRider().new Builder();
    }

    public class Builder extends BaseUnit.Builder {
        private Builder() {
            super();
        }

        public UnitDinoRider build() {
            return UnitDinoRider.this;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Обновление анимации атаки
        if (isAttacking) {
            attackAnimationTime += deltaTime;
            if (attackAnimationTime >= ATTACK_ANIMATION_DURATION) {
                isAttacking = false;
            }
        }
    }

    /**
     * Атака копьём (мах, без броска)
     */
    @Override
    public void attack(GameObject target, float currentTime) {
        if (target == null || !target.isAlive()) return;

        // Проверка дистанции
        if (distanceTo(target) > attackRange) return;

        // Проверка кулдауна
        if (currentTime - lastAttackTime < attackCooldown) return;

        // Запускаем анимацию атаки
        isAttacking = true;
        attackAnimationTime = 0f;

        // Наносим урон
        target.takeDamage(attackDamage);

        // Обновляем время последней атаки
        lastAttackTime = currentTime;

        System.out.println("DinoRider атакует копьём! Урон: " + attackDamage);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // тень
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillOval(Math.round(x - 25 * scale), Math.round(y + 50 * scale),
                Math.round(150 * scale), Math.round(20 * scale));

        // задние ноги динозавра
        g2.setColor(new Color(100, 160, 100));
        g2.fillRoundRect(Math.round(x + 17 * scale), Math.round(y + 30 * scale),
                Math.round(18 * scale), Math.round(30 * scale),
                Math.round(10 * scale), Math.round(10 * scale));
        g2.fillRoundRect(Math.round(x + 80 * scale), Math.round(y + 30 * scale),
                Math.round(18 * scale), Math.round(30 * scale),
                Math.round(10 * scale), Math.round(10 * scale));

        // тело динозавра
        g2.setColor(new Color(100, 180, 100));
        g2.fillRoundRect(Math.round(x - 10 * scale), Math.round(y - 10 * scale),
                Math.round(110 * scale), Math.round(55 * scale),
                Math.round(30 * scale), Math.round(50 * scale));

        // шея и голова динозавра
        g2.fillRoundRect(Math.round(x + 70 * scale), Math.round(y - 60 * scale),
                Math.round(35 * scale), Math.round(75 * scale),
                Math.round(25 * scale), Math.round(45 * scale));
        g2.setColor(new Color(100, 160, 100));
        g2.fillRoundRect(Math.round(x + 65 * scale), Math.round(y - 80 * scale),
                Math.round(35 * scale), Math.round(45 * scale),
                Math.round(25 * scale), Math.round(35 * scale));
        g2.fillRoundRect(Math.round(x + 85 * scale), Math.round(y - 70 * scale),
                Math.round(35 * scale), Math.round(35 * scale),
                Math.round(25 * scale), Math.round(70 * scale));

        // передние ноги динозавра
        g2.fillRoundRect(Math.round(x - 5 * scale), Math.round(y + 30 * scale),
                Math.round(18 * scale), Math.round(30 * scale),
                Math.round(10 * scale), Math.round(10 * scale));
        g2.fillRoundRect(Math.round(x + 60 * scale), Math.round(y + 30 * scale),
                Math.round(18 * scale), Math.round(30 * scale),
                Math.round(10 * scale), Math.round(10 * scale));

        // глаза и нос динозавра
        g2.setColor(new Color(255, 255, 255));
        g2.fillOval(Math.round(x + 77 * scale), Math.round(y - 72 * scale),
                Math.round(10 * scale), Math.round(15 * scale));
        g2.fillOval(Math.round(x + 90 * scale), Math.round(y - 77 * scale),
                Math.round(10 * scale), Math.round(15 * scale));
        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(Math.round(x + 83 * scale), Math.round(y - 67 * scale),
                Math.round(5 * scale), Math.round(7 * scale));
        g2.fillOval(Math.round(x + 96 * scale), Math.round(y - 72 * scale),
                Math.round(5 * scale), Math.round(7 * scale));
        g2.setColor(new Color(40, 40, 40));
        g2.fillOval(Math.round(x + 103 * scale), Math.round(y - 62 * scale),
                Math.round(5 * scale), Math.round(7 * scale));
        g2.fillOval(Math.round(x + 110 * scale), Math.round(y - 65 * scale),
                Math.round(5 * scale), Math.round(7 * scale));

        // всадник с копьём
        g2.setColor(new Color(108, 29, 13));
        g2.fillRoundRect(Math.round(x + 15 * scale), Math.round(y - 40 * scale),
                Math.round(40 * scale), Math.round(50 * scale),
                Math.round(35 * scale), Math.round(75 * scale));
        g2.setColor(new Color(217, 142, 73));
        g2.fillOval(Math.round(x + 17 * scale), Math.round(y - 65 * scale),
                Math.round(35 * scale), Math.round(35 * scale));
        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(Math.round(x + 42 * scale), Math.round(y - 57 * scale),
                Math.round(5 * scale), Math.round(10 * scale));
        g2.fillOval(Math.round(x + 33 * scale), Math.round(y - 55 * scale),
                Math.round(5 * scale), Math.round(10 * scale));

        // ===== КОПЬЁ С АНИМАЦИЕЙ АТАКИ =====
        Graphics2D gSpear = (Graphics2D) g2.create();

        // Вычисляем угол для анимации
        float angle = 0f;
        if (isAttacking) {
            // При атаке копьё движется вверх-вниз
            float t = attackAnimationTime / ATTACK_ANIMATION_DURATION;
            angle = (float) Math.sin(t * Math.PI) * 50f; // мах от -50 до +50 градусов
        } else {
            angle = -20f; // обычное положение
        }

        // Точка вращения (рука всадника)
        int pivotX = Math.round(x + 50 * scale);
        int pivotY = Math.round(y - 20 * scale);

        gSpear.rotate(Math.toRadians(angle), pivotX, pivotY);

        // Рисуем древко копья
        gSpear.setStroke(new BasicStroke(7.0f * scale));
        gSpear.setColor(new Color(121, 67, 25));
        gSpear.drawLine(Math.round(x - 20 * scale), Math.round(y - 15 * scale),
                Math.round(x + 100 * scale), Math.round(y - 15 * scale));

        // Рисуем наконечник
        gSpear.setStroke(new BasicStroke(3.0f * scale));
        gSpear.setColor(new Color(128, 121, 115));
        int[] xPoints = {
                Math.round(x + 100 * scale),
                Math.round(x + 120 * scale),
                Math.round(x + 100 * scale)
        };
        int[] yPoints = {
                Math.round(y - 25 * scale),
                Math.round(y - 15 * scale),
                Math.round(y - 5 * scale)
        };
        gSpear.fillPolygon(xPoints, yPoints, 3);

        gSpear.dispose();

        drawHealthBar(g2, scale);
    }
}