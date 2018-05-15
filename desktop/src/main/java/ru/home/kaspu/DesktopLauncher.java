package ru.home.kaspu;

import com.jme3.system.AppSettings;

public class DesktopLauncher {

    public static void main(String[] args) {
        CoreLauncher coreApp = new CoreLauncher();
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1360, 768);
        settings.setFullscreen(false);
        settings.setGammaCorrection(true);
        settings.setVSync(true);
        settings.setDepthBits(24);
        settings.setSamples(1);
        coreApp.setSettings(settings);
        coreApp.setShowSettings(false);

        coreApp.start();
    }
}
