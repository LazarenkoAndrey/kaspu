package ru.home.kaspu.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Test001 extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        Box box1 = new Box(1, 1, 1);
        Geometry geom1 = new Geometry("Box1", box1);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        mat1.setColor("Color", new ColorRGBA(0f, 0f, 1f, 0.5f));
        geom1.setMaterial(mat1);
        geom1.setQueueBucket(Bucket.Translucent);
        geom1.move(new Vector3f(2f, 0f, 0f));

        Box box2 = new Box(1, 1, 1);
        Geometry geom2= new Geometry("Box2", box2);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        mat2.setColor("Color", new ColorRGBA(1f, 0.68f, 0.68f, 0.5f));
        mat2.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        geom2.setMaterial(mat2);
        geom2.setQueueBucket(Bucket.Translucent);
        geom2.move(new Vector3f(-2f, 0f, 0f));

//        Box box2 = new Box(1, 1, 1);
//        Geometry geom2= new Geometry("Box2", box2);
//        Material mat2 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"); 
//        mat2.setBoolean("UseMaterialColors",true);  
//        mat2.setColor("Ambient", new ColorRGBA(1f, 0.68f, 0.68f, 0.5f));   // ... color of this object
//        mat2.setColor("Diffuse", new ColorRGBA(1f, 0.68f, 0.68f, 0.5f));   // ... color of light being reflected
//        //mat2.setColor("Color", new ColorRGBA(1f, 0.68f, 0.68f, 0.5f));
//        //mat2.setBoolean("UseAlpha",true);
//        mat2.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
//        geom2.setMaterial(mat2);
//        geom2.setQueueBucket(Bucket.Translucent);
//        geom2.move(new Vector3f(-2f, 0f, 0f));

        
        rootNode.attachChild(geom1);
        rootNode.attachChild(geom2);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        rootNode.rotate(0, FastMath.DEG_TO_RAD, 0);
        rootNode.getChild("Box1").rotate(0, 0, FastMath.DEG_TO_RAD);
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
