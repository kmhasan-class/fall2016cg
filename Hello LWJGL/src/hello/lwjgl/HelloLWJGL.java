package hello.lwjgl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class HelloLWJGL {
    private long window;
    private float angle = 0.0f;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Hello from Java", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }
    
    private void print() {
        String vendor = GL11.glGetString(GL11.GL_VENDOR);
        String version = GL11.glGetString(GL11.GL_VERSION);
        System.out.println("Vendor:  " + vendor);
        System.out.println("Version: " + version);
    }
    
    private void drawSomething() {
        GL11.glPushMatrix();
        GL11.glRotatef(angle++, 0.0f, 0.0f, 1.0f);
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private void loop() {
        for (int i = 0; i < 10000; i++) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            try {
                drawSomething();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloLWJGL.class.getName()).log(Level.SEVERE, null, ex);
            }
            GLFW.glfwSwapBuffers(window);
        }
    }
    
    public HelloLWJGL() {
        init();
        print();
        loop();
    }

    public static void main(String[] args) {
        new HelloLWJGL();
    }
   
}
