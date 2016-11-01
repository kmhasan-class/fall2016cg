package fresh.lwjgl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class FreshLWJGL {
    private long window;
    private float angle = 0.0f;
    
    private void init() {
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(600, 600, "Hello LWJGL", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }
    
    private void drawTriangle(float scale) {
        angle += 0.1;
        GL11.glPushMatrix();
        GL11.glRotatef(angle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glVertex3f(scale, 0.0f, 0.0f);
        GL11.glVertex3f(0.0f, scale, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private void loop() {
        try {
            for (int i = 0; i < 1000; i++) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                for (float scale = 0.2f; scale <= 1.0f; scale += 0.2f)
                    drawTriangle(scale);
                Thread.sleep(10);
                GLFW.glfwSwapBuffers(window);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FreshLWJGL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public FreshLWJGL() {
        init();
        loop();
    }

    public static void main(String[] args) {
        new FreshLWJGL();
    }
    
}
