package ru.home.kaspu.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class Test004 extends SimpleApplication {

  private TerrainQuad terrain;
  private Material matTerrain;

  @Override
  public void simpleInitApp() {
    flyCam.setMoveSpeed(50);

    matTerrain = new Material(assetManager, "Common/MatDefs/Terrain/Terrain.j3md");
    matTerrain.setTexture("Alpha", assetManager.loadTexture("Textures/Terrain/splat/alphamap.png"));

    Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
    grass.setWrap(WrapMode.Repeat);
    matTerrain.setTexture("Tex1", grass);
    matTerrain.setFloat("Tex1Scale", 64f);
    
    Texture dirt = assetManager.loadTexture("Textures/Terrain/splat/dirt.jpg");
    dirt.setWrap(WrapMode.Repeat);
    matTerrain.setTexture("Tex2", dirt);
    matTerrain.setFloat("Tex2Scale", 32f);

    Texture rock = assetManager.loadTexture("Textures/Terrain/splat/road.jpg");
    rock.setWrap(WrapMode.Repeat);
    matTerrain.setTexture("Tex3", rock);
    matTerrain.setFloat("Tex3Scale", 128f);

    //matTerrain.getAdditionalRenderState().setWireframe(true);
    //matTerrain.getAdditionalRenderState().setLineWidth(1f);
    
    AbstractHeightMap heightmap = null;
    Texture heightMapImage = assetManager.loadTexture("Textures/Terrain/splat/mountains512.png");
    heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
    heightmap.load();
    int patchSize = 65;
    terrain = new TerrainQuad("my terrain", patchSize, 513, heightmap.getHeightMap());

    terrain.setMaterial(matTerrain);
    terrain.setLocalTranslation(0, -100, -400);
    terrain.setLocalScale(2f, 1f, 2f);
    rootNode.attachChild(terrain);

    TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
    terrain.addControl(control);
  }
}