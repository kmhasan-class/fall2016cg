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
    private float armTiltAngle = 0f;
    
    private float neckWidth;
    private float neckHeight;
    private float torsoWidth;
    private float torsoHeight;
    private float headRadius;
    private int headSegments;
    private float rightUpperArmWidth;
    private float rightUpperArmHeight;
    private float rightForearmWidth;
    private float rightForearmHeight;
    private float leftUpperArmWidth;
    private float leftUpperArmHeight;
    private float leftForearmWidth;
    private float leftForearmHeight;
    
    private void setParameters() {
        torsoWidth = 0.30f;
        torsoHeight = 0.40f;
        neckWidth = 0.10f;
        neckHeight = 0.12f;
        headRadius = 0.15f;
        headSegments = 10;
        rightUpperArmWidth = 0.30f;
        rightUpperArmHeight = 0.08f;
        rightForearmWidth = 0.25f;
        rightForearmHeight = 0.06f;
        leftUpperArmWidth = 0.30f;
        leftUpperArmHeight = 0.08f;
        leftForearmWidth = 0.25f;
        leftForearmHeight = 0.06f;
    }
    
    private void init() {
        GLFW.glfwInit();
        setParameters();
        window = GLFW.glfwCreateWindow(800, 800, "Robot Animation", 0, 0);
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
                    armTiltAngle--;
                else if (key == GLFW.GLFW_KEY_LEFT)
                    armTiltAngle++;
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
        drawRectangle(torsoWidth, torsoHeight);
    }
    
    private void drawNeck() {
        drawRectangle(neckWidth, neckHeight);
    }
    
    private void drawRightUpperArm() {
        drawRectangle(rightUpperArmWidth, rightUpperArmHeight);
    }

    private void drawRightForearm() {
        drawRectangle(rightForearmWidth, rightForearmHeight);
    }
    
    private void drawLeftUpperArm() {
        drawRectangle(leftUpperArmWidth, leftUpperArmHeight);
    }

    private void drawLeftForearm() {
        drawRectangle(leftForearmWidth, leftForearmHeight);
    }
    
    private void drawHead() {
        // HOMEWORK: add two eyes and a nose (triangle)
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3f(0f, headRadius * 0.1f, 0);
        GL11.glVertex3f(-headRadius * 0.1f, -headRadius * 0.3f, 0);
        GL11.glVertex3f(+headRadius * 0.1f, -headRadius * 0.3f, 0);
        GL11.glEnd();
        GL11.glTranslatef(headRadius * 0.35f, headRadius * 0.1f, 0f);
        drawCircle(headRadius * 0.2f, headSegments);
        GL11.glTranslatef(-headRadius * 0.7f, 0f, 0f);
        drawCircle(headRadius * 0.2f, headSegments);
        GL11.glPopMatrix();
        drawCircle(headRadius, headSegments);
    }
    
    // HOMEWORK: Finish drawing the robot if you can
    private void drawRobot() {
        GL11.glPushMatrix();
        drawTorso();
        
        GL11.glPushMatrix();
        GL11.glTranslatef(torsoWidth / 2, torsoHeight / 2 - rightUpperArmHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(rightUpperArmWidth / 2, 0, 0);
        drawRightUpperArm();
        GL11.glPushMatrix();
        GL11.glTranslatef(rightUpperArmWidth / 2, 0, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(rightForearmWidth / 2, 0, 0);
        drawRightForearm();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(-torsoWidth / 2, torsoHeight / 2 - leftUpperArmHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(-leftUpperArmWidth / 2, 0, 0);
        drawLeftUpperArm();
        
        GL11.glPushMatrix();
        GL11.glTranslatef(-leftUpperArmWidth / 2, 0, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(-leftForearmWidth / 2, 0, 0);
        drawLeftForearm();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        // HOMEWORK: Fix the pivot point, you'll need a translate, rotate and another translate
        // REPLACE THE NUMBERS WITH SOME SYMBOLIC CONSTANTS or VARIABLES
        GL11.glTranslatef(0, torsoHeight / 2, 0);
        GL11.glRotatef(neckTiltAngle, 0, 0, 1);
        GL11.glTranslatef(0, neckHeight / 2, 0);
        drawNeck();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, (float) (headRadius + neckHeight / 2), 0);
        drawHead();
        GL11.glPopMatrix();
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
