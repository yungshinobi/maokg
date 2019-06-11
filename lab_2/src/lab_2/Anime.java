package lab_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class Anime extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    double points_top[][] = {
            { 180, -65}, { 230, -65}, { 250, -25 }, { 200, -25}
    };
    double points_bot[][] = {
            { 200, 35}, { 250, 35}, { 230, 75}, { 180, 75}
    };

    double points_nose[][] = {
            {430,-25},{480,5},{430,35}
    };

    static double points_static_top[][] = {
            { -500, -40}, { -450, -40}, { -430, 0}, { -480, 0}
    };
    static double points_static_bot[][] = {
            { -480, 60}, { -430, 60 }, { -450, 100 }, { -500, 100 }
    };

    static double points_static_nose[][] = {
            {-250,0},{-200,30},{-250,60}
    };

    Timer timer;

    // Для анімації повороту
    private double angle = 0;

    // Для анімації руху
    private double tx = 1;
    private double ty = 0;
    private double deltaX = 1;
    private int radius = 100;
    private int radiusExtention = 200;

    public Anime() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // параметри для рендеру
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(new Color(128,128,255));
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        // встановлюємо (0;0) в центр холсту
        g2d.translate(maxWidth/2, maxHeight/2);

        g2d.setPaint(Color.WHITE);
        BasicStroke bs1 = new BasicStroke(15, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        BasicStroke bs2 = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        g2d.setStroke(bs1);
        g2d.drawRect(
                -(radius + radiusExtention-300),
                -(radius + radiusExtention),
                (radius + radiusExtention)*2,
                (radius + radiusExtention)*2
        );

        g2d.setStroke(bs2);
        Rocket.rocket(g2d, points_static_top, points_static_bot, points_static_nose, -500, 0);

        g2d.translate(tx, ty);

        GeneralPath stabilizers = new GeneralPath();

        stabilizers.moveTo(points_top[0][0], points_top[0][1]);
        for (int k = 1; k < points_top.length; k++)
            stabilizers.lineTo(points_top[k][0], points_top[k][1]);

        stabilizers.moveTo(points_bot[0][0], points_bot[0][1]);
        for (int k = 1; k < points_bot.length; k++)
            stabilizers.lineTo(points_bot[k][0], points_bot[k][1]);

        stabilizers.moveTo(points_nose[0][0], points_nose[0][1]);
        for (int k = 1; k < points_nose.length; k++)
            stabilizers.lineTo(points_nose[k][0], points_nose[k][1]);

        stabilizers.closePath();

        GradientPaint gp = new GradientPaint(0, 5, new Color(255, 128, 64),
                10, 30, new Color(255,0,0),
                true);
        g2d.setPaint(gp);

        g2d.rotate(angle, stabilizers.getBounds2D().getCenterX(), stabilizers.getBounds2D().getCenterY());
        g2d.fill(stabilizers);

        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(180, -25, 250, 60);

        g2d.setPaint(gp);
        g2d.drawLine(180, 5, 250, 5);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("fly me to the moon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Anime());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {

        // рух
         double radiusInSquare = Math.pow(radius, 2);
        if (tx <= 0 && ty < 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx > 0 && ty <= 0){
            tx -= deltaX;
            ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx >= 0 && ty > 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }else if(tx < 0 && ty >= 0){
            tx += deltaX;
            ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
        }

        // поворот
        angle -= 0.01;

        repaint();
    }
}
