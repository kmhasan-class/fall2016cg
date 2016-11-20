/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.animation;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author kmhasan
 */
public class Robot {

    private float leftUpperArmTiltAngle;
    private float rightUpperArmTiltAngle;
    private float leftForearmTiltAngle;
    private float rightForearmTiltAngle;

    private float scaleFactor;
    
    private float locationX;
    private float locationY;
    private float locationZ;
    
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
    private float rightUpperLegWidth;
    private float rightUpperLegHeight;
    private float rightLowerLegWidth;
    private float rightLowerLegHeight;
    private float leftUpperLegWidth;
    private float leftUpperLegHeight;
    private float leftLowerLegWidth;
    private float leftLowerLegHeight;

    private float armTiltAngle;
    private float neckTiltAngle;

    public Robot() {
        scaleFactor = 1;
        
        locationX = 0;
        locationY = 0;
        locationZ = 0;
        
        armTiltAngle = 0;
        neckTiltAngle = 0;
        
        leftUpperArmTiltAngle = 0;
        rightUpperArmTiltAngle = 0;
        leftForearmTiltAngle = 0;
        rightForearmTiltAngle = 0;

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
        rightUpperLegWidth = 0.1f;
        rightUpperLegHeight = 0.3f;
        rightLowerLegWidth = 0.08f;
        rightLowerLegHeight = 0.4f;
        leftUpperLegWidth = 0.1f;
        leftUpperLegHeight = 0.3f;
        leftLowerLegWidth = 0.08f;
        leftLowerLegHeight = 0.4f;
    }

    public Robot(float scaleFactor) {
        this();
        this.scaleFactor = scaleFactor;
    }
    
    private void drawCircle(double r, int n) {
        double theta = 2 * Math.PI / n;
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_LINES);
        for (int i = 0; i < n;) {
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
        GL11.glVertex3f((float) width / 2, (float) height / 2, 0);
        GL11.glVertex3f(-(float) width / 2, (float) height / 2, 0);
        GL11.glVertex3f(-(float) width / 2, -(float) height / 2, 0);
        GL11.glVertex3f((float) width / 2, -(float) height / 2, 0);
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

    private void drawRightUpperLeg() {
        drawRectangle(rightUpperLegWidth, rightUpperLegHeight);
    }

    private void drawRightLowerLeg() {
        drawRectangle(rightLowerLegWidth, rightLowerLegHeight);
    }

    private void drawLeftUpperLeg() {
        drawRectangle(leftUpperLegWidth, leftUpperLegHeight);
    }

    private void drawLeftLowerLeg() {
        drawRectangle(leftLowerLegWidth, leftLowerLegHeight);
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

    // HOMEWORK: Add hands/claws and wheels at the feet!
    public void drawRobot() {
        GL11.glPushMatrix();
        GL11.glTranslatef(locationX, locationY, locationZ);
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
        drawTorso();

        GL11.glPushMatrix();
        GL11.glTranslatef(torsoWidth / 2, torsoHeight / 2 - rightUpperArmHeight / 2, 0);
        GL11.glRotatef(rightUpperArmTiltAngle, 0, 0, 1);
        GL11.glTranslatef(rightUpperArmWidth / 2, 0, 0);
        drawRightUpperArm();
        GL11.glPushMatrix();
        GL11.glTranslatef(rightUpperArmWidth / 2, 0, 0);
        GL11.glRotatef(rightForearmTiltAngle, 0, 0, 1);
        GL11.glTranslatef(rightForearmWidth / 2, 0, 0);
        drawRightForearm();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(-torsoWidth / 2, torsoHeight / 2 - leftUpperArmHeight / 2, 0);
        GL11.glRotatef(leftUpperArmTiltAngle, 0, 0, 1);
        GL11.glTranslatef(-leftUpperArmWidth / 2, 0, 0);
        drawLeftUpperArm();

        GL11.glPushMatrix();
        GL11.glTranslatef(-leftUpperArmWidth / 2, 0, 0);
        GL11.glRotatef(leftForearmTiltAngle, 0, 0, 1);
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

        GL11.glPushMatrix();
        GL11.glTranslatef(torsoHeight / 4, -torsoHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(0, -rightUpperLegHeight / 2, 0);
        drawRightUpperLeg();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, -rightUpperLegHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(0, -rightLowerLegHeight / 2, 0);
        drawRightLowerLeg();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(-torsoHeight / 4, -torsoHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(0, -leftUpperLegHeight / 2, 0);
        drawLeftUpperLeg();
        GL11.glPushMatrix();
        GL11.glTranslatef(0, -leftUpperLegHeight / 2, 0);
        GL11.glRotatef(armTiltAngle, 0, 0, 1);
        GL11.glTranslatef(0, -leftLowerLegHeight / 2, 0);
        drawLeftLowerLeg();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    public void increaseLeftUpperArmTiltAngle() {
        leftUpperArmTiltAngle++;
    }

    public void decreaseLeftUpperArmTiltAngle() {
        leftUpperArmTiltAngle--;
    }
    
    public void moveLeft() {
        locationX -= 0.01f;
    }
    
    public void moveRight() {
        locationX += 0.01f;
    }
}
