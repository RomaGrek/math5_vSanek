
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.Scanner;

public class DifferentialEquation {
    static double x0;
    static double y0;
    static double leftBoard;
    static double rightBoard;
    static int n;
    static double y;
    static double x;
    static double[] xPol;
    static double[] yPol;
    static int sumX;
    static double step;
    static double[] xInter;
    static double[] yInter; // производная
    static double[] ySimple; // НЕ производная


    static void advancedEulerMethod1() {
        leftBoard = x0;
        sumX = (int) ((rightBoard - x0) * 10 + 1) ;
        double[] xData = new double[sumX];
        double[] yData = new double[sumX];
        int p = 0;
        for (x = x0; x <= rightBoard; x = x + 0.1) {
            xData[p] = x;
            yData[p] = Function.function1X(x);
            p++;
        }


        double h = (rightBoard - x0) / n;
        step = h;

        xInter = new double[n + 1];
        yInter = new double[n + 1];
        ySimple = new double[n + 1];
        xInter[0] = x0;
        yInter[0] = y0;
        ySimple[0] = y0;
        p = 1;
        for (x = x0 + h; x <= rightBoard; x = x + h) {
            y = y0 + (h / 2.0) * (Function.function1XY(x0, y0) + Function.function1XY(x, y0 + h * Function.function1XY(x0, y0)));
            xInter[p] = x;
            yInter[p] = y;
            ySimple[p] = Function.function1X(x);

            x0 = x;
            y0 = y;
            p++;
        }

        double[][] y_d = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            y_d[0][i] = yInter[i];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                y_d[i][j] = y_d[i - 1][j + 1] - y_d[i - 1][j];
            }
        }

        xPol = new double[sumX];
        yPol = new double[sumX];

        double xxx = leftBoard;
        for (int i = 0; i < sumX; i++) {
            double factorial = 1;
            double drob = 1;
            double result = yInter[0];
            double t = (xxx - (leftBoard + h)) / h;
            for (int j = 1; j < n; j++) {
                factorial *= j;
                drob *= t - j + 1;
                result += (drob/factorial) * y_d[j][0];
            }
            xPol[i] = xxx;
            yPol[i] = result;

            xxx = xxx + 0.1;
        }
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y'(x)", xPol, yPol);

        new SwingWrapper(chart).displayChart();
    }










    static void advancedEulerMethod2() {
        leftBoard = x0;
        sumX = (int) ((rightBoard - x0) * 10) + 1;
        double[] xData = new double[sumX];
        double[] yData = new double[sumX];
        int p = 0;
        for (x = x0; x <= rightBoard; x = x + 0.1) {
            xData[p] = x;
            yData[p] = Function.function2X(x);
            p++;
        }


        double h = (rightBoard - x0) / n;
        step = h;

        xInter = new double[n + 1];
        yInter = new double[n + 1];
        ySimple = new double[n + 1];
        xInter[0] = x0;
        yInter[0] = y0;
        ySimple[0] = y0;
        p = 1;
        for (x = x0 + h; x <= rightBoard; x = x + h) {
            y = y0 + (h / 2.0) * (Function.function2XY(x0, y0) + Function.function2XY(x, y0 + h * Function.function2XY(x0, y0)));
            xInter[p] = x;
            yInter[p] = y;
            ySimple[p] = Function.function2X(x);
            x0 = x;
            y0 = y;
            p++;
        }

        double[][] y_d = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            y_d[0][i] = yInter[i];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                y_d[i][j] = y_d[i - 1][j + 1] - y_d[i - 1][j];
            }
        }

        xPol = new double[sumX];
        yPol = new double[sumX];

        double xxx = leftBoard;
        for (int i = 0; i < sumX; i++) {
            double factorial = 1;
            double drob = 1;
            double result = yInter[0];
            double t = (xxx - (leftBoard + h)) / h;
            for (int j = 1; j < n; j++) {
                factorial *= j;
                drob *= t - j + 1;
                result += (drob/factorial) * y_d[j][0];
            }
            xPol[i] = xxx;
            yPol[i] = result;

            xxx = xxx + 0.1;
        }

        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y'(x)", xPol, yPol);


        new SwingWrapper(chart).displayChart();
    }

    static boolean checkBoard() {
        return x0 >= rightBoard;
    }

}
