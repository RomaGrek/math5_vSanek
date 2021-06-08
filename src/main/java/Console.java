import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        System.out.println("Выберите тип уравнения:\n" +
                "1: y' + y - 7 * exp(x) = 0\n" +
                "2: y' - (x^2 + 2 * x + y) = 0");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        if (type == 1) {
            System.out.print("Введите начальные условия\nx0 = ");
            DifferentialEquation.x0 = scanner.nextDouble();
            System.out.print("y0 = ");
            DifferentialEquation.y0 = scanner.nextDouble();
            System.out.println("Введите конец отрезка");
            DifferentialEquation.rightBoard = scanner.nextDouble();
            while (DifferentialEquation.checkBoard()) {
                System.out.println("Вы ввели некорректное значение правого промежутка");
                System.out.println("Пожалуйста повторите ввод");
                DifferentialEquation.rightBoard = scanner.nextDouble();
            }

            System.out.println("Введите точность (количество точек)");
            DifferentialEquation.n = scanner.nextInt();
            DifferentialEquation.advancedEulerMethod1();
            DifferentialEquation.searchY();

        } else if (type == 2) {
            System.out.print("Введите начальные условия\nx0 = ");
            DifferentialEquation.x0 = scanner.nextDouble();
            System.out.print("\ny0=");
            DifferentialEquation.y0 = scanner.nextDouble();
            System.out.println("Введите конец отрезка");
            DifferentialEquation.rightBoard = scanner.nextDouble();
            while (DifferentialEquation.checkBoard()) {
                System.out.println("Вы ввели некорректное значение правого промежутка");
                System.out.println("Пожалуйста повторите ввод");
                DifferentialEquation.rightBoard = scanner.nextDouble();
            }

            System.out.println("Введите точность (количество точек)");
            DifferentialEquation.n = scanner.nextInt();
            DifferentialEquation.advancedEulerMethod2();
            DifferentialEquation.searchY();

        } else {
            System.out.println("Вы ввели некорректный тип уравнения :)");
        }
    }
}
