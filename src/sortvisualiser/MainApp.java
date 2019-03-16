package sortvisualiser;

import extension.IExtension;
import host.IHostApplication;
import sortvisualiser.screens.MainMenuScreen;
import sortvisualiser.screens.Screen;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The main application point for controlling the program
 *
 * @author Matthew Hopson
 */
public class MainApp implements IExtension {
    
    private  JFrame window;
    
    public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
    
     IHostApplication theApp;
    
    private  ArrayList<Screen> screens;
    
    public MainApp() {
       // screens = new ArrayList<>();
        //window = new JFrame("Sort visualiser");
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//        
//        System.setProperty("sun.java2d.opengl", "true");
//        SwingUtilities.invokeLater(() -> {
//            new MainApp().start();
//        });
    }
    
    public void setHostApp(IHostApplication inHostApp){
         theApp = inHostApp;    
    }
    
    public Screen getCurrentScreen() {
        return screens.get(screens.size() - 1);
    }
    
    public void pushScreen(Screen screen) {
        if (!screens.isEmpty()) {
            window.remove(getCurrentScreen());
        }
        screens.add(screen);
        window.setContentPane(screen);
        window.validate();
        screen.onOpen();
    }
    
    public void popScreen() {
        if (!screens.isEmpty()) {
            Screen prev = getCurrentScreen();
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                Screen current = getCurrentScreen();
                window.setContentPane(current);
                window.validate();
                current.onOpen();
            } else {
                window.dispose();
            }
        }
    }
    
    public void start() {
        pushScreen(new MainMenuScreen(this));
        window.pack();
    }

//    public static void main(String... args) {
//        System.setProperty("sun.java2d.opengl", "true");
//        SwingUtilities.invokeLater(() -> {
//            new MainApp().start();
//        });
//    }
    @Override
    public String getExtensionName() {
        return "Assignment 2";
    }
    
    @Override
    public String getExtensionTitle() {
        return "Group assignment by group X";
    }
    
    @Override
    public void executeVisualization() {
        System.out.println("the extension is started");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                screens = new ArrayList<>();
                window = new JFrame("Sort visualiser");
                window.setSize(WIN_WIDTH, WIN_HEIGHT);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                
                System.setProperty("sun.java2d.opengl", "true");
                SwingUtilities.invokeLater(() -> {
                    new MainApp().start();
                });
            }
        });
    }
    
    @Override
    public String getCreditInformation() {
        return "Trung, Mien, Son, Quang Anh";
    }
}
