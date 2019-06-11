package lab_5;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Background;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;

public class Frog extends JFrame {
    private static Canvas3D canvas;
    private static SimpleUniverse universe;
    private static BranchGroup root;

    private static TransformGroup post;
    private static TransformGroup frog;

    private Map<String, Shape3D> shapeMap;

    public Frog() throws IOException {
        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();

        addImageBackground();

        addDirectionalLightToUniverse();
        addAmbientLightToUniverse();

        ChangeViewAngle();

        frog = getFrogGroup();

        root.addChild(post);
        root.addChild(frog);

        root.compile();
        universe.addBranchGraph(root);
    }

    // start initialization

    private void configureWindow() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground() {
        TextureLoader t = new TextureLoader("source_assets/Monte_Myoboku.png", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addDirectionalLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(100);

        DirectionalLight light = new DirectionalLight(new Color3f(0.1f, 0.5f, 0), new Vector3f(-1, -1, -1));
        light.setInfluencingBounds(bounds);

        root.addChild(light);
    }

    private void addAmbientLightToUniverse() {
        AmbientLight light = new AmbientLight(new Color3f(1, 1, 1));
        light.setInfluencingBounds(new BoundingSphere());
        root.addChild(light);
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }


    private TransformGroup getFrogGroup() throws IOException {
        Shape3D frog = getModelShape3D("12268_banjofrog_", "source_assets/Frog.obj");
        Appearance frogApp = new Appearance();

        Material material = new Material();
        material.setEmissiveColor(new Color3f(1, 0.4f, 0));
        material.setAmbientColor(new Color3f(1f, 0f, 0));
//        material.setDiffuseColor(new Color3f(1, 0.4f, 0));
//        material.setSpecularColor(new Color3f(1, 0.4f, 0));
        material.setShininess(128);
        material.setLightingEnable(true);

        frogApp.setMaterial(material);
        frog.setAppearance(frogApp);

        Transform3D transform3D = new Transform3D();
        transform3D.setScale(new Vector3d(0.5f, 0.5f, 0.5f));

        Transform3D rotationY = new Transform3D();
        rotationY.rotY(Math.PI/3);
        transform3D.mul(rotationY);

        Transform3D rotationZ = new Transform3D();
        rotationZ.rotZ(Math.PI/6);
        transform3D.mul(rotationZ);

        Transform3D rotationX = new Transform3D();
        rotationX.rotX(-Math.PI/3);
        transform3D.mul(rotationX);

        TransformGroup group = getModelGroup(frog);
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(Shape3D shape) {
        TransformGroup group = new TransformGroup();
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.addChild(shape);
        return group;
    }

    private Shape3D getModelShape3D(String name, String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        Map<String, Shape3D> map = scene.getNamedObjects();
        Shape3D shape = map.get(name);
        scene.getSceneGroup().removeChild(shape);
        return shape;
    }

    private Scene getSceneFromFile(String path) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(path));
    }


    public static void main(String[] args) {
        try {
            Frog window = new Frog();
            FrogAnimation frogAnime = new FrogAnimation(frog);
            canvas.addKeyListener(frogAnime);
            window.setVisible(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

