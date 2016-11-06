/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analog.clock;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private final float minutesHandLength = 0.7f;
    private float minutesHandAngle = 90;
    private final float hoursHandLength = 0.5f;
    private float hoursHandAngle = 90;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Analog Clock", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwSwapBuffers(window);
        
        LocalTime localTime = LocalTime.now();
        String tokens[] = localTime.toString().split("\\:");
        System.out.println(tokens[0] + ":" + tokens[1] + ":" + tokens[2]);
        double seconds = Double.parseDouble(tokens[2]);
        secondsHandAngle = (float) (-6 * seconds + 90);
        // FIX YOUR CODE TO WORK FOR THE MINUTES AND HOUR HAND
    }
    
    private void drawDial() {
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
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
        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        secondsHandAngle -= 6;
        GL11.glPushMatrix();
        GL11.glRotatef(secondsHandAngle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(secondsHandLength, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void drawMinutesHand() {
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        minutesHandAngle -= 6/60.0;
        GL11.glPushMatrix();
        GL11.glRotatef(minutesHandAngle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(minutesHandLength, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void drawHoursHand() {
        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        hoursHandAngle -= 6/60.0/60.0;
        GL11.glPushMatrix();
        GL11.glRotatef(hoursHandAngle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(hoursHandLength, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private void loop() {
        try {
            for (int i = 0; i < 1000; i++) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                drawDial();
                drawSecondsHand();
                drawMinutesHand();
                drawHoursHand();
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
