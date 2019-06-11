package lab_5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

public class FrogAnimation implements ActionListener, KeyListener {

    private static final float speed = 0.01f;
    private static final float rotationSpeed = 0.035f;
    private TransformGroup frog;
    private Transform3D transform3D = new Transform3D();

    private boolean w = false;
    private boolean s = false;
    private boolean a = false;
    private boolean d = false;
    private boolean q = false;
    private boolean e = false;
    private boolean space = false;

    private float x = -0.5f;
    private float y = -0.5f;
    private float z = 0f;
    private int angle = 0;

    private float radius = 1.5f;
    private double scale = 1;
    private double delta = 0.01;

    public FrogAnimation(TransformGroup frog) {
        this.frog = frog;
        this.frog.getTransform(this.transform3D);

        transform3D.setTranslation(new Vector3f(x, y, z));
        frog.setTransform(transform3D);

        Timer timer = new Timer(10, this);
        timer.start();
    }

    private void Move() {
        if (w) {
            y += speed;
        } else if (s) {
            y -= speed;
        } else if (a) {
            x -= speed;
        } else if (d) {
            x += speed;
        } else if (q) {
            z -= 3*speed;
        } else if (e) {
            z += 3*speed;
        } else if (space) {
            Transform3D rotation = new Transform3D();
            rotation.rotX(-rotationSpeed);
            transform3D.mul(rotation);

            transform3D.setTranslation(new Vector3f(x, y, z));

            if(angle<=180 ) {
                y = (float) (radius * Math.sin(Math.toRadians(angle))-0.3);
                x = (float) (-radius * Math.cos(Math.toRadians(angle))+0.85);
                z-=0.015;
                angle++;
            } else if (angle<=360 && angle>180){
                y = (float) (-radius * Math.sin(Math.toRadians(angle))-0.3);
                x = (float) (-radius * Math.cos(Math.toRadians(angle))+0.85);
                z+=0.015;
                angle++;
            } else if (angle>360)
                angle=0;
        }
        transform3D.setTranslation(new Vector3f(x,y,z));
        frog.setTransform(transform3D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Move();
    }

    @Override
    public void keyPressed(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w': w = true; break;
            case 's': s = true; break;
            case 'a': a = true; break;
            case 'd': d = true; break;
            case 'q': q = true; break;
            case 'e': e = true; break;
            case ' ': space = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ev) {
        switch (ev.getKeyChar()) {
            case 'w': w = false; break;
            case 's': s = false; break;
            case 'a': a = false; break;
            case 'd': d = false; break;
            case 'q': q = false; break;
            case 'e': e = false; break;
            case ' ': space = false; break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
