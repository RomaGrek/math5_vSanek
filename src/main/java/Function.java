public class Function {

    /* диффиринциальное ур-е, функция возвразает значения правой части уравнения вида y' = y/x - 3
    * ВАЖНО! - нельзя давать промеэуток x проходящий через 0! Так как на 0 делить нельзя! */
    static double function1XY(double x, double y) {
        return (y/x - 3);
    }
    /* общее рещение дифура #1 (мы его сами посчитали на листочке) */
    static double function1X(double x, double C) {
        return x * (C - 3 * Math.log(x));
    }
    /* поиск C для постороения графика проверки (синяя линия)
    * ВАЖНО! нелья давать отрицательнй икс, так как натуральный логарифм даст ошибку*/
    static double searchC1(double x, double y) {
        return (y/x) + 3 * Math.log(x);
    }

//    static double function2XY(double x, double y) {
//        return (Math.pow(x, 2) + 2 * x + y);
//    }
//
//    static double function2X(double x) {
//        return (Math.exp(x) - Math.pow(x, 2) - 4 * x - 4);
//    }

    /* диффиринциальное ур-е, функция возвразает значения правой части уравнения вида y' = (x * y)   */
    static double function2XY(double x, double y) {
        return x * y;
    }

    /* общее рещение дифура #2 (мы его сами посчитали на листочке) */
    static double function2X(double x, double C) {
        return Math.exp(Math.pow(x,2)/2 + C);
    }

    /* поиск C для постороения графика проверки (синяя линия)
    * ВАЖНО! нелья давать отрицательнй игрик, так как натуральный логарифм даст ошибку*/
    static double searchC2(double x, double y) {
        return Math.log(y) - ((x * x) / 2.0);
    }
}