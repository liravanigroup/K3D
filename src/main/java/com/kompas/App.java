package com.kompas;

import com.kompas.api.DrawingProgram;
import com.kompas.api.KompasAPI5;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.javafx.tk.TKStage;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32Util;
import com.sun.jna.platform.win32.WinDef;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.awt.windows.WComponentPeer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by White Stream on 17.02.2017.
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    public BufferedImage getScreenShot (Component component){
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

    private static long getWindowPointer(Stage stage) {
        try {
            TKStage tkStage = stage.impl_getPeer();
            Method getPlatformWindow = tkStage.getClass().getDeclaredMethod("getPlatformWindow" );
            getPlatformWindow.setAccessible(true);
            Object platformWindow = getPlatformWindow.invoke(tkStage);

            Method getNativeHandle = platformWindow.getClass().getMethod( "getNativeHandle" );
            getNativeHandle.setAccessible(true);
            Object nativeHandle = getNativeHandle.invoke(platformWindow);

            return (Long) nativeHandle;
        } catch (Throwable e) {
            System.err.println("Error getting Window Pointer");
            return 0;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DrawingProgram drawingProgram = new KompasAPI5();
        StackPane root = new StackPane();

        Pane p = new Pane();
        p.setVisible(true);

        Canvas c = new Canvas();


        root.getChildren().addAll(p);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();



//
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//
//        Stage s = new Stage();
//        s.setHeight(800);
//        s.setWidth(1200);
//
//
//        Scene sc = new Scene(s, 1200, 800);
//        s.show();
//
////        long ref2 = getWindowPointer(primaryStage);
////        long ref = getWindowPointer(s);
//
//        long ref3 = getWindowPointer(p);


//        primaryStage.show();


//        long ref = com.sun.glass.ui.Window.getWindows().get(0).getNativeHandle();
//        long ref = com.sun.glass.ui.Window.getWindows().get(0).getView().getNativeView();
//        long ref2 = com.sun.glass.ui.Window.getFocusedWindow().getNativeHandle();

//        drawingProgram.drawDocumentInWindow(ref);
//        drawingProgram.drawDocumentInWindow(ref2);


//        drawingProgram.close();

    }
}
