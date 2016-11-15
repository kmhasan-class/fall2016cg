/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.animation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class RobotAnimation {

    private long window;
    private float neckTiltAngle = 0f;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Analog Clock", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwSwapBuffers(window);
        
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW.GLFW_KEY_ESCAPE)
                    GLFW.glfwSetWindowShouldClose(window, true);
                else if (key == GLFW.GLFW_KEY_RIGHT)
                    neckTiltAngle++;
                else if (key == GLFW.GLFW_KEY_LEFT)
                    neckTiltAngle--;
            }
        });
    }

    private void drawCircle(double r, int n) {
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

    private void drawRectangle(double width, double height) {
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3f( (float) width / 2,  (float) height / 2, 0);
        GL11.glVertex3f(-(float) width / 2,  (float) height / 2, 0);
        GL11.glVertex3f(-(float) width / 2, -(float) height / 2, 0);
        GL11.glVertex3f( (float) width / 2, -(float) height / 2, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void drawTorso() {
        drawRectangle(0.25, 0.40);
    }
    
    private void drawNeck() {
        drawRectangle(0.1, 0.15);
    }
    
    private void drawHead() {
        // HOMEWORK: add two eyes and a nose (triangle)
        drawCircle(0.15, 10);
    }
    
    // HOMEWORK: Finish drawing the robot if you can
    private void drawRobot() {
        drawTorso();
        GL11.glPushMatrix();
        // HOMEWORK: Fix the pivot point, you'll need a translate, rotate and another translate
        // REPLACE THE NUMBERS WITH SOME SYMBOLIC CONSTANTS or VARIABLES
        GL11.glTranslatef(0, (float) (0.40 + 0.15) / 2, 0);
        GL11.glRotatef(neckTiltAngle, 0, 0, 1);
        drawNeck();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, (float) (0.15 + 0.15 / 2), 0);
        drawHead();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    private void loop() {
        while (GLFW.glfwWindowShouldClose(window) != true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            drawRobot();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

    }

    public RobotAnimation() {
        init();
        loop();
    }

    public static void main(String[] args) {
        new RobotAnimation();
    }

}
