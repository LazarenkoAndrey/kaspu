package ru.home.kaspu.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;

public class Test002 extends SimpleApplication {

    private DirectionalLight sun;


    @Override
    public void simpleInitApp() {

        //Sphere sphereMesh = new Sphere(32, 32, 3f);
       
        Cylinder sphereMesh = new Cylinder(100, 100, 2f, 4f, 6f, true, false);
        Geometry sphereGeo = new Geometry("Shiny rock", sphereMesh);
        //sphereMesh.setTextureMode(Sphere.TextureMode.Projected); // better quality on spheres
        TangentBinormalGenerator.generate(sphereMesh);           // for lighting effect
        Material sphereMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        sphereMat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Pond/Pond.jpg"));
        sphereMat.setTexture("NormalMap", assetManager.loadTexture("Textures/Pond/Pond_normal.png"));
        sphereMat.setBoolean("UseMaterialColors", true);
        sphereMat.setColor("Ambient", ColorRGBA.White);
        sphereMat.setColor("Diffuse", ColorRGBA.White);
        sphereMat.setColor("Specular", ColorRGBA.White);
        sphereMat.setFloat("Shininess", 82f);  // [0,128]
        sphereMat.getAdditionalRenderState().setWireframe(true);
        sphereMat.getAdditionalRenderState().setLineWidth(1f);
        sphereGeo.setMaterial(sphereMat);
        sphereGeo.setLocalTranslation(0, 2, -2); 
        sphereGeo.rotate(1.6f, 0, 0);         
        
        rootNode.attachChild(sphereGeo);

        sun = new DirectionalLight();
        sun.setDirection(new Vector3f(2, 2, -2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        AmbientLight amb = new AmbientLight();
        amb.setColor(ColorRGBA.DarkGray);
        rootNode.addLight(amb);

    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        //rootNode.rotate(0, FastMath.DEG_TO_RAD, 0);
        
        rootNode.getChild("Shiny rock").rotate(0, 0, FastMath.DEG_TO_RAD);
        
        Quaternion roll1 = new Quaternion();
        roll1.fromAngleAxis(FastMath.DEG_TO_RAD/4f, new Vector3f(0,1,0) ); 
        roll1.mult(sun.getDirection(), sun.getDirection());
    }
}
