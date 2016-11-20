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

    private BodyPart selectedBodyPart = BodyPart.NONE;
    private boolean leftShiftPressed = false;
    private boolean rightShiftPressed = false;
    
    private Robot robot1;
    private Robot robot2;
    private Robot selectedRobot;
    
    private void init() {
        GLFW.glfwInit();
        robot1 = new Robot(0.5f);
        robot2 = new Robot(0.4f);
        selectedRobot = robot1;
        window = GLFW.glfwCreateWindow(800, 800, "Robot Animation", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwSwapBuffers(window);

        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW.GLFW_KEY_1)
                    selectedRobot = robot1;
                if (key == GLFW.GLFW_KEY_2)
                    selectedRobot = robot2;
                if (key == GLFW.GLFW_KEY_LEFT_SHIFT)
                    leftShiftPressed = true;
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT)
                    rightShiftPressed = true;
                if (key == GLFW.GLFW_KEY_U && mods == GLFW.GLFW_MOD_SHIFT) {
                    if (leftShiftPressed)
                        selectedBodyPart = BodyPart.LEFT_UPPER_ARM;
                    if (rightShiftPressed)
                        selectedBodyPart = BodyPart.RIGHT_UPPER_ARM;
                    leftShiftPressed = false;
                    rightShiftPressed = false;
                }
                if (key == GLFW.GLFW_KEY_F && mods == GLFW.GLFW_MOD_SHIFT) {
                    if (leftShiftPressed)
                        selectedBodyPart = BodyPart.LEFT_FOREARM;
                    if (rightShiftPressed)
                        selectedBodyPart = BodyPart.RIGHT_FOREARM;
                    leftShiftPressed = false;
                    rightShiftPressed = false;
                }
                if (key == GLFW.GLFW_KEY_ESCAPE) {
                    GLFW.glfwSetWindowShouldClose(window, true);
                } else if (key == GLFW.GLFW_KEY_RIGHT) {
                    switch (selectedBodyPart) {
                        case LEFT_UPPER_ARM: selectedRobot.decreaseLeftUpperArmTiltAngle(); break;
                        case RIGHT_UPPER_ARM: break;
                        case LEFT_FOREARM: break;
                        case RIGHT_FOREARM: break;
                        case NONE: selectedRobot.moveRight(); break;
                        default: break;
                    }
                } else if (key == GLFW.GLFW_KEY_LEFT) {
                    switch (selectedBodyPart) {
                        case LEFT_UPPER_ARM: selectedRobot.increaseLeftUpperArmTiltAngle(); break;
                        case RIGHT_UPPER_ARM: break;
                        case LEFT_FOREARM: break;
                        case RIGHT_FOREARM: break;
                        case NONE: selectedRobot.moveLeft(); break;
                        default: break;
                    }
                }
                
                System.out.println("Selected body part " + selectedBodyPart);
            }
        });
    }

    private void loop() {
        while (GLFW.glfwWindowShouldClose(window) != true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            robot1.drawRobot();
            robot2.drawRobot();
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
