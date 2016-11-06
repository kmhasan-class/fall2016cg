/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analog.clock;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class AnalogClock {
    private long window;
    private float angle = 0.0f;
    private final float secondsHandLength = 0.8f;
    private float secondsHandAngle = 90;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Analog Clock", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }
    
    private void drawDial() {
        int n = 12;
        double r = 1;
        double theta = 2 * Math.PI / n;
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINES);
        for (int i = 0; i < n;) {
            //GL11.glVertex3f(0, 0, 0);
            float x = (float) (r * Math.cos(theta * i));
            float y = (float) (r * Math.sin(theta * i));
            GL11.glVertex3f(x, y, 0);
            i++;
            x = (float) (r * Math.cos(theta * i));
            y = (float) (r * Math.sin(theta * i));
            GL11.glVertex3f(x, y, 0);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private void drawSecondsHand() {
        secondsHandAngle -= 6;
        GL11.glPushMatrix();
        GL11.glRotatef(secondsHandAngle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(secondsHandLength, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private void loop() {
        try {
            for (int i = 0; i < 1000; i++) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                drawDial();
                drawSecondsHand();
                Thread.sleep(1000);
                GLFW.glfwSwapBuffers(window);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AnalogClock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AnalogClock() {
        init();
        loop();
    }

    public static void main(String[] args) {
        new AnalogClock();
    }
    
}
