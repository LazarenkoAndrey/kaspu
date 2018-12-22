package ru.home.kaspu.test;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Test003 extends SimpleApplication implements AnimEventListener {
  private AnimChannel channel;
  private AnimControl control;
  Node player;
  
  @Override
  public void simpleInitApp() {
    viewPort.setBackgroundColor(ColorRGBA.LightGray);
    initKeys();
    DirectionalLight dl = new DirectionalLight();
    dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
    
    rootNode.addLight(dl);
    player = (Node) assetManager.loadModel("src/main/assets/Models/Ninja/Ninja.mesh.xml");
    player.setLocalScale(0.02f);
    player.rotate(0f, FastMath.PI *1.2f, 0f);
    rootNode.attachChild(player);
    control = player.getControl(AnimControl.class);
    control.addListener(this);
    channel = control.createChannel();
    //channel.setAnim("Jump");
    //channel.setLoopMode(LoopMode.DontLoop);
  }
  
  public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
    if (animName.equals("Walk")) {
      channel.setAnim("Jump", 0.50f);
      channel.setLoopMode(LoopMode.DontLoop);
      channel.setSpeed(1f);
    }
  }
  
  @Override
  public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    // не использовано
  }
  
  /** Пользовательские назначения клавиш: сопоставления названий действий с клавишами. */
  private void initKeys() {
    inputManager.addMapping("Walk", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addListener(actionListener, "Walk");
  }
  
  private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Walk") && !keyPressed) {
        channel.setAnim("Walk", 0.50f);
        channel.setLoopMode(LoopMode.Loop);
      }
    }
  };
          
}