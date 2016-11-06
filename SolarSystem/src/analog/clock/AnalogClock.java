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
    private float earthOrbitAngle = 0.0f;
    private float moonOrbitAngle = 0.0f;
    private float earthAxisAngle = 0.0f;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Analog Clock", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwSwapBuffers(window);
    }
    
    private void drawCircle(double r, int n) {
        GL11.glColor3f(1.0f, 1.0f, 0.0f);
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
    
    private void loop() {
        try {
            for (int i = 0; i < 1000; i++) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                drawCircle(0.25, 8); // draw the sun
                
                earthOrbitAngle += 1;
                moonOrbitAngle += 5;
                earthAxisAngle += 10;
                
                GL11.glPushMatrix();
                GL11.glRotatef(earthOrbitAngle, 0, 0, 1);
                GL11.glTranslatef(0.75f, 0.0f, 0.0f);
                GL11.glPushMatrix();
                GL11.glRotatef(earthAxisAngle, 0, 0, 1);
                GL11.glScalef(0.5f, 0.5f, 0.5f);
                drawCircle(0.25, 3); // draw the earth
                GL11.glPopMatrix();
                
                GL11.glPushMatrix();
                GL11.glRotatef(moonOrbitAngle, 0, 0, 1);
                GL11.glTranslatef(0.25f, 0.0f, 0.0f);
                GL11.glScalef(0.25f, 0.25f, 0.25f);
                drawCircle(0.25, 5); // draw the moon
                GL11.glPopMatrix();
                GL11.glPopMatrix();
                
                Thread.sleep(10);
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
