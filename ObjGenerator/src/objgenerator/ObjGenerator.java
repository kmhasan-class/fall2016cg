/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objgenerator;

/**
 *
 * @author kmhasan
 */
public class ObjGenerator {

    public ObjGenerator() {
        int n = 10;
        double r = 1.0;
        double h = 2.0;
        
        System.out.printf("o Prism\n");
        for (int i = 0; i < n; i++) {
            double x;
            double y;
            double z;
            double theta = 2 * Math.PI / n * i;
            x = r * Math.cos(theta);
            y = r * Math.sin(theta);
            z = 0;
            System.out.printf("v %.3f %.3f %.3f\n", x, y, z);
        }

        for (int i = 0; i < n; i++) {
            double x;
            double y;
            double z;
            double theta = 2 * Math.PI / n * i;
            x = r * Math.cos(theta);
            y = r * Math.sin(theta);
            z = h;
            System.out.printf("v %.3f %.3f %.3f\n", x, y, z);
        }
        
        System.out.printf("f");
        for (int i = 1; i <= n; i++)
            System.out.printf(" %d", i);
        System.out.println();
        
        System.out.printf("f");
        for (int i = n + 1; i <= 2 * n; i++)
            System.out.printf(" %d", i);
        System.out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ObjGenerator();
    }
    
}
