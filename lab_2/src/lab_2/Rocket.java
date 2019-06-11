package lab_2;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rocket extends JPanel {

    public static void rocket(Graphics2D g2d,double points_top[][],double points_bot[][],double points_nose[][], int x, int y) {

        Rectangle corp = new Rectangle();
        corp.setBounds(x, y, 250, 60);

        g2d.setColor(Color.WHITE);
        g2d.fill(corp);

        GradientPaint gp = new GradientPaint(0, 5, new Color(255, 128, 64),
                                             10, 30, new Color(255,0,0),
                                        true);
        g2d.setPaint(gp);

        GeneralPath stabilizers = new GeneralPath();

        g2d.translate(0, 0);

        stabilizers.moveTo(points_top[0][0], points_top[0][1]);
        for (int k = 1; k < points_top.length; k++)
            stabilizers.lineTo(points_top[k][0], points_top[k][1]);

        stabilizers.moveTo(points_bot[0][0], points_bot[0][1]);
        for (int k = 1; k < points_bot.length; k++)
            stabilizers.lineTo(points_bot[k][0], points_bot[k][1]);

        stabilizers.moveTo(points_nose[0][0], points_nose[0][1]);
        for (int k = 1; k < points_nose.length; k++)
            stabilizers.lineTo(points_nose[k][0], points_nose[k][1]);

        g2d.drawLine(x, y+30, x+70, y+30);

        stabilizers.closePath();
        g2d.fill(stabilizers);
    }
}