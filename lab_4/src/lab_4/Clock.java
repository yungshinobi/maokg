package lab_4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

// Vector3f - float, Vector3d - double
public class Clock implements ActionListener {
    private float upperEyeLimit = 8.0f; // 5.0
    private float lowerEyeLimit = 5.0f; // 1.0
    private float farthestEyeLimit = 7.0f; // 6.0
    private float nearestEyeLimit = 4.0f; // 3.0

    private TransformGroup clockTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Clock();
    }

    private Clock() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        clockTransformGroup = new TransformGroup();
        clockTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildClock();
        objRoot.addChild(clockTransformGroup);

        Background background = new Background(new Color3f(0.9f, 0.9f, 0.9f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }

    private void buildClock() {
        Cylinder body = new Cylinder(2, 0.5f, Utils.getBodyAppearence());
        Transform3D bodyT = new Transform3D();
        bodyT.setTranslation(new Vector3f());
        TransformGroup bodyTG = new TransformGroup();
        bodyTG.setTransform(bodyT);
        bodyTG.addChild(body);

        Cylinder clock_face = new Cylinder(1.6f, 0.41f, Utils.getClockFaceApearance());
        Transform3D bodyT2 = new Transform3D();
        bodyT2.setTranslation(new Vector3f(0,0.1f,0));
        TransformGroup bodyTG2 = new TransformGroup();
        bodyTG2.setTransform(bodyT2);
        bodyTG2.addChild(clock_face);

        Cylinder clock_back = new Cylinder(1.4f, 0.2f, Utils.getBodyAppearence());
        Transform3D bodyT3 = new Transform3D();
        bodyT3.setTranslation(new Vector3f(0,-0.3f,0));
        TransformGroup bodyTG3 = new TransformGroup();
        bodyTG3.setTransform(bodyT3);
        bodyTG3.addChild(clock_back);

        Sphere ball = new Sphere(0.3f, Utils.getBodyAppearence());
        Transform3D ballT = new Transform3D();
        ballT.setTranslation(new Vector3f(0, 0, 2.2f));
        TransformGroup ballTG = new TransformGroup();
        ballTG.setTransform(ballT);
        ballTG.addChild(ball);
        bodyTG.addChild(ballTG);

        Cylinder arrowCenter = new Cylinder(0.2f, 0.11f, Utils.getArrowApearance());
        Transform3D acT = new Transform3D();
        acT.setTranslation(new Vector3f(0,0.31f,0));
        TransformGroup acTG = new TransformGroup();
        acTG.setTransform(acT);
        acTG.addChild(arrowCenter);

        for(int i = 0; i < 12; ++i) {
            Cylinder dot = new Cylinder(0.1f, 0.1f, Utils.getArrowApearance());

            Transform3D dotT = new Transform3D();
            dotT.rotY(angle);
            dotT.setTranslation(new Vector3f((float)Math.cos(angle)*1.4f, 0.31f, (float)Math.sin(angle)*1.4f));

            TransformGroup dotTG = new TransformGroup();
            dotTG.setTransform(dotT);
            dotTG.addChild(dot);
            clockTransformGroup.addChild(dotTG);
            angle += Math.PI/6;
        }

        Box arrow1 = new Box(0.4f, 0.05f, 0.05f, Utils.getArrowApearance());
        Transform3D a1T = new Transform3D();
        a1T.setTranslation(new Vector3f(0.4f,0.31f,0));
        TransformGroup a1TG = new TransformGroup();
        a1TG.setTransform(a1T);
        a1TG.addChild(arrow1);

        Box arrow2 = new Box(0.6f, 0.05f, 0.05f, Utils.getArrowApearance());
        Transform3D a2T = new Transform3D();
        a2T.rotY(Math.PI/3);
        a2T.setTranslation(new Vector3f(-0.2f,0.31f,0.4f));
        TransformGroup a2TG = new TransformGroup();
        a2TG.setTransform(a2T);
        a2TG.addChild(arrow2);

        clockTransformGroup.addChild(bodyTG);
        clockTransformGroup.addChild(bodyTG2);
        clockTransformGroup.addChild(bodyTG3);
        clockTransformGroup.addChild(acTG);
        clockTransformGroup.addChild(a1TG);
        clockTransformGroup.addChild(a2TG);

    }

    // ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.03f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        clockTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.1f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
