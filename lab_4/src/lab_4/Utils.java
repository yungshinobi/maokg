package lab_4;

import javax.media.j3d.*; // for transform
import javax.vecmath.Color3f;
import java.awt.Color;

public class Utils {
    public static Appearance getBodyAppearence() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(200,125,120));
        Color3f diffuse = new Color3f(new Color(200,200,100));
        Color3f specular = new Color3f(new Color(0,0, 0));
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

    public static Appearance getClockFaceApearance() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(255,255, 255));
        Color3f ambient = new Color3f(Color.WHITE);
        Color3f diffuse = new Color3f(Color.WHITE);
        Color3f specular = new Color3f(new Color(0,0, 0));
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }

    public static Appearance getArrowApearance() {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(new Color(6, 20, 20));
        Color3f ambient = new Color3f(new Color(6, 20, 20));
        Color3f diffuse = new Color3f(new Color(6, 20, 20));
        Color3f specular = new Color3f(new Color(100, 100, 100));
        // ambient, emissive, diffuse, specular, 1.0f
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

        return ap;
    }
}
