
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


    static void advancedEulerMethod1() { // 1 -3 1 3
        leftBoard = x0;
        sumX = (int) ((rightBoard - x0) * 10 + 1) ;
        double[] xData = new double[sumX];
        double[] yData = new double[sumX];
        xData[0] = x0;
        yData[0] = y0;
        int p = 1;
        for (x = x0 + 0.1; x <= rightBoard; x = x + 0.1) {
            xData[p] = x;
            yData[p] = Function.function1X(x, Function.searchC1(xData[p - 1], yData[p - 1]));
            p++;
        }


        double h = (rightBoard - x0) / n;
        step = h;

        xInter = new double[n + 1];
        yInter = new double[n + 1];
        xInter[0] = x0;
        yInter[0] = y0;
        p = 1;
        for (x = x0 + h; x <= rightBoard; x = x + h) {
            y = y0 + (h / 2.0) * (Function.function1XY(x0, y0) + Function.function1XY(x, y0 + h * Function.function1XY(x0, y0)));
            xInter[p] = x;
            yInter[p] = y;

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
        if (xData[xData.length - 1] == 0 && yData[yData.length - 1] == 0) {
            xData[xData.length - 1] = xData[xData.length - 2];
            yData[yData.length - 1] = yData[yData.length - 2];
        }
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y'(x)", xData, yData);

        int s = 0;
        for (int i = 0; i < n; i++) {

            chart.addSeries(Integer.toString(s), new double[]{xInter[i]}, new double[]{yInter[i]});
            s++;
        }
        new SwingWrapper(chart).displayChart();
    }










    static void advancedEulerMethod2() {
        leftBoard = x0;
        if (x0 < 0 && rightBoard > 0 || x0 > 0 && rightBoard < 0) {
            sumX = (int) ((rightBoard - x0) * 10 + 1) ;
        }else {
            sumX = (int) ((rightBoard - x0) * 10);
        }
        double[] xData = new double[sumX];
        double[] yData = new double[sumX];
        xData[0] = x0;
        yData[0] = y0;
        int p = 1;
        for (x = x0 + 0.1; x <= rightBoard; x = x + 0.1) {
            xData[p] = x;
            yData[p] = Function.function2X(x, Function.searchC2(xData[p - 1], yData[p - 1]));
            p++;
        }


        double h = (rightBoard - x0) / n;
        step = h;

        xInter = new double[n + 1];
        yInter = new double[n + 1];
        xInter[0] = x0;
        yInter[0] = y0;
        p = 1;
        for (x = x0 + h; x <= rightBoard; x = x + h) {
            y = y0 + (h / 2.0) * (Function.function2XY(x0, y0) + Function.function2XY(x, y0 + h * Function.function2XY(x0, y0)));
            xInter[p] = x;
            yInter[p] = y;
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

        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y'(x)", xData, yData);
        int s = 0;
        for (int i = 0; i < n; i++) {
            chart.addSeries(Integer.toString(s), new double[]{xInter[i]}, new double[]{yInter[i]});
            s++;
        }

        new SwingWrapper(chart).displayChart();
    }

    static boolean checkBoard() {
        return x0 >= rightBoard;
    }

}
