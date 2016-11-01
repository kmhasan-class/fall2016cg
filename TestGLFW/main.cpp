#include <iostream>
#include <unistd.h>
#include <GLFW/glfw3.h>
using namespace std;

GLFWwindow* window;
float angle = 0.0f;

void init() {
    glfwInit();
    window = glfwCreateWindow(600, 600, "Hello from C++", NULL, NULL);
    glfwMakeContextCurrent(window);
}

void print() {
    const GLubyte* vendor = glGetString(GL_VENDOR);
    const GLubyte* version = glGetString(GL_VERSION);
    cout << "Vendor:  " << vendor << endl;
    cout << "Version: " << version << endl;
}

void drawSomething() {
    glPushMatrix();
    glRotatef(angle++, 0.0, 0.0, 1.0);
    glBegin(GL_TRIANGLES);
    glVertex3f(1.0f, 0.0f, 0.0f);
    glVertex3f(0.0f, 1.0f, 0.0f);
    glVertex3f(0.0f, 0.0f, 0.0f);
    glEnd();
    glPopMatrix();
}

void loop() {
    for (int i = 0; i < 10000; i++) {
        glClear(GL_COLOR_BUFFER_BIT);
        drawSomething();
        usleep(1000);
        glfwSwapBuffers(window);
    }
}

int main() {
    init();
    print();
    loop();
    return 0;
}
