import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class CFull {
    private double[][] matrix;
    private int n;
    private Random rand = new Random();
    private Scanner scanner = new Scanner(System.in);

    public CFull() {
        System.out.print("Введите размерность матрицы n: ");
        this.n = scanner.nextInt();
        this.matrix = new double[n][n];
        fillRandom();
    }

    private void fillRandom() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextDouble() * (2 * n) - n;
            }
        }
    }

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.2f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void task1() {
        System.out.print("Введите номер столбца k: ");
        int k = scanner.nextInt();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (matrix[j][k] > matrix[j + 1][k]) {
                    double[] temp = matrix[j];
                    matrix[j] = matrix[j + 1];
                    matrix[j + 1] = temp;
                }
            }
        }
    }

    public void task2() {
        System.out.print("Введите количество позиций для сдвига: ");
        int k = scanner.nextInt();
        System.out.println("Выберите направление (1-вправо, 2-влево, 3-вверх, 4-вниз): ");
        int direction = scanner.nextInt();

        k = k % n;
        double[][] temp = new double[n][n];

        switch (direction) {
            case 1:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][(j + k) % n] = matrix[i][j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][j] = matrix[i][(j + k) % n];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[i][j] = matrix[(i + k) % n][j];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        temp[(i + k) % n][j] = matrix[i][j];
                    }
                }
                break;
        }
        matrix = temp;
    }

    public void task3() {
        int maxLength = 1;
        int currentLength = 1;

        for (int i = 0; i < n; i++) {
            currentLength = 1;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] > matrix[i][j - 1]) {
                    currentLength++;
                    maxLength = Math.max(maxLength, currentLength);
                } else {
                    currentLength = 1;
                }
            }
        }

        System.out.println("Наибольшее число возрастающих элементов: " + maxLength);
    }

    public void task4() {
        for (int i = 0; i < n; i++) {
            int firstPos = -1;
            int secondPos = -1;
            double sum = 0;

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0) {
                    if (firstPos == -1) {
                        firstPos = j;
                    } else {
                        secondPos = j;
                        break;
                    }
                }
            }

            if (firstPos != -1 && secondPos != -1) {
                for (int j = firstPos + 1; j < secondPos; j++) {
                    sum += matrix[i][j];
                }
                System.out.printf("Строка %d: сумма = %.2f%n", i, sum);
            }
        }
    }

    public void task5() {
        System.out.print("Введите число k: ");
        int k = scanner.nextInt();

        int[][] result = new int[n][n];
        int current = 1;

        for (int i = 0; i < n && current <= k; i++) {
            for (int j = 0; j < n && current <= k; j++) {
                result[i][j] = current++;
                System.out.printf("%4d ", result[i][j]);
            }
            System.out.println();
        }
    }

    public void task6() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.round(matrix[i][j]);
            }
        }
    }

    public void task7() {
        System.out.print("Введите угол поворота (90, 180, 270): ");
        int angle = scanner.nextInt();
        int rotations = angle / 90;

        for (int r = 0; r < rotations; r++) {
            double[][] temp = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temp[n - 1 - j][i] = matrix[i][j];
                }
            }
            matrix = temp;
        }
    }

    public void task8() {
        if (n > 3) {
            System.out.println("Реализован расчет только для матриц 2x2 и 3x3");
            return;
        }

        double det = 0;
        if (n == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (n == 3) {
            det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1])
                    - matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0])
                    + matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
        }

        System.out.printf("Определитель матрицы: %.2f%n", det);
    }

    public void task9() {
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / n;

            for (int j = 0; j < n; j++) {
                matrix[i][j] -= avg;
            }
        }
    }

    public void task10() {
        double max = matrix[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        boolean[] rowsToRemove = new boolean[n];
        boolean[] colsToRemove = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    rowsToRemove[i] = true;
                    colsToRemove[j] = true;
                }
            }
        }

        int newRows = 0;
        int newCols = 0;
        for (boolean b : rowsToRemove) {
            if (!b) newRows++;
        }
        for (boolean b : colsToRemove) {
            if (!b) newCols++;
        }

        double[][] newMatrix = new double[newRows][newCols];
        int newRow = 0;
        for (int i = 0; i < n; i++) {
            if (!rowsToRemove[i]) {
                int newCol = 0;
                for (int j = 0; j < n; j++) {
                    if (!colsToRemove[j]) {
                        newMatrix[newRow][newCol++] = matrix[i][j];
                    }
                }
                newRow++;
            }
        }

        matrix = newMatrix;
        n = newRows;
    }

    public void task11() {
        boolean[] rowsToKeep = new boolean[n];
        boolean[] colsToKeep = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    rowsToKeep[i] = true;
                    break;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] != 0) {
                    colsToKeep[j] = true;
                    break;
                }
            }
        }

        int newRows = 0;
        int newCols = 0;
        for (boolean b : rowsToKeep) {
            if (b) newRows++;
        }
        for (boolean b : colsToKeep) {
            if (b) newCols++;
        }

        double[][] newMatrix = new double[newRows][newCols];
        int newRow = 0;

        for (int i = 0; i < n; i++) {
            if (rowsToKeep[i]) {
                int newCol = 0;
                for (int j = 0; j < n; j++) {
                    if (colsToKeep[j]) {
                        newMatrix[newRow][newCol++] = matrix[i][j];
                    }
                }
                newRow++;
            }
        }

        matrix = newMatrix;
        n = newRows;
    }

    public void task12() {
        System.out.print("Введите позицию (строка столбец): ");
        int targetRow = scanner.nextInt();
        int targetCol = scanner.nextInt();

        int minRow = 0, minCol = 0;
        double min = matrix[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }

        if (minRow != targetRow) {
            double[] temp = matrix[minRow];
            matrix[minRow] = matrix[targetRow];
            matrix[targetRow] = temp;
        }

        if (minCol != targetCol) {
            for (int i = 0; i < n; i++) {
                double temp = matrix[i][minCol];
                matrix[i][minCol] = matrix[i][targetCol];
                matrix[i][targetCol] = temp;
            }
        }
    }

    public void task13() {
        for (int i = 0; i < n; i++) {
            int nonZeroPos = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    if (j != nonZeroPos) {
                        matrix[i][nonZeroPos] = matrix[i][j];
                        matrix[i][j] = 0;
                    }
                    nonZeroPos++;
                }
            }
        }
    }

    public void task14() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isMinInRow = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[i][k] < matrix[i][j]) {
                        isMinInRow = false;
                        break;
                    }
                }

                boolean isMaxInCol = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[k][j] > matrix[i][j]) {
                        isMaxInCol = false;


                        break;
                    }
                }

                if (isMinInRow && isMaxInCol) {
                    count++;
                    System.out.printf("Седловая точка найдена в позиции [%d,%d] = %.2f%n", i, j, matrix[i][j]);
                }
            }
        }
        System.out.println("Всего найдено седловых точек: " + count);
    }

    public void task15() {
        double[] sums = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sums[i] += matrix[i][j];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sums[j] > sums[j + 1]) {
                    double tempSum = sums[j];
                    sums[j] = sums[j + 1];
                    sums[j + 1] = tempSum;

                    double[] tempRow = matrix[j];
                    matrix[j] = matrix[j + 1];
                    matrix[j + 1] = tempRow;
                }
            }
        }
    }

    public void task16() {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLocalMin = true;

                for (int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                        if (matrix[ni][nj] <= matrix[i][j]) {
                            isLocalMin = false;
                            break;
                        }
                    }
                }

                if (isLocalMin) {
                    count++;
                    System.out.printf("Локальный минимум в позиции [%d,%d] = %.2f%n", i, j, matrix[i][j]);
                }
            }
        }
        System.out.println("Всего локальных минимумов: " + count);
    }

    public void task17() {
        double minLocalMax = Double.POSITIVE_INFINITY;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isLocalMax = true;

                for (int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                        if (matrix[ni][nj] >= matrix[i][j]) {
                            isLocalMax = false;
                            break;
                        }
                    }
                }

                if (isLocalMax && matrix[i][j] < minLocalMax) {
                    minLocalMax = matrix[i][j];
                }
            }
        }

        if (minLocalMax != Double.POSITIVE_INFINITY) {
            System.out.printf("Наименьший локальный максимум: %.2f%n", minLocalMax);
        } else {
            System.out.println("Локальные максимумы не найдены");
        }
    }

    public void task18() {
        double[] characteristics = new double[n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                characteristics[j] += Math.abs(matrix[i][j]);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (characteristics[j] < characteristics[j + 1]) {
                    double tempChar = characteristics[j];
                    characteristics[j] = characteristics[j + 1];
                    characteristics[j + 1] = tempChar;

                    for (int k = 0; k < n; k++) {
                        double temp = matrix[k][j];
                        matrix[k][j] = matrix[k][j + 1];
                        matrix[k][j + 1] = temp;
                    }
                }
            }
        }
    }

    public void task19() {
        double[] elements = new double[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                elements[index++] = matrix[i][j];
            }
        }

        Arrays.sort(elements);
        for (int i = 0; i < elements.length / 2; i++) {
            double temp = elements[i];
            elements[i] = elements[elements.length - 1 - i];
            elements[elements.length - 1 - i] = temp;
        }

        index = 0;
        for (int i = 0; i < n; i++) {
            matrix[i][i] = elements[index++];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = elements[index++];
                }
            }
        }
    }

    public static void main(String[] args) {
        CFull mt = new CFull();

        System.out.println("\nИсходная матрица:");
        mt.printMatrix();

        for (int i = 1; i <= 19; i++) {
            System.out.println("\nЗадание " + i + ":");
            switch (i) {
                case 1:
                    System.out.println("Упорядочивание строк матрицы по k-му столбцу:");
                    mt.task1();
                    break;
                case 2:
                    System.out.println("Циклический сдвиг матрицы:");
                    mt.task2();
                    break;
                case 3:
                    System.out.println("Поиск наибольшего числа возрастающих элементов:");
                    mt.task3();
                    break;
                case 4:
                    System.out.println("Сумма между первым и вторым положительными элементами:");
                    mt.task4();
                    break;
                case 5:
                    System.out.println("Вывод чисел от 1 до k в виде матрицы:");
                    mt.task5();
                    break;
                case 6:
                    System.out.println("Округление элементов матрицы:");
                    mt.task6();
                    break;
                case 7:
                    System.out.println("Поворот матрицы:");
                    mt.task7();
                    break;
                case 8:
                    System.out.println("Вычисление определителя матрицы:");
                    mt.task8();
                    break;
                case 9:
                    System.out.println("Вычитание среднего арифметического из строк:");
                    mt.task9();
                    break;
                case 10:
                    System.out.println("Удаление строк и столбцов с максимальным элементом:");
                    mt.task10();
                    break;
                case 11:
                    System.out.println("Удаление нулевых строк и столбцов:");
                    mt.task11();
                    break;
                case 12:
                    System.out.println("Перемещение минимального элемента:");
                    mt.task12();
                    break;
                case 13:
                    System.out.println("Перемещение нулей в конец строки:");
                    mt.task13();
                    break;
                case 14:
                    System.out.println("Поиск седловых точек:");
                    mt.task14();
                    break;
                case 15:
                    System.out.println("Сортировка строк по сумме элементов:");
                    mt.task15();
                    break;
                case 16:
                    System.out.println("Поиск локальных минимумов:");
                    mt.task16();
                    break;
                case 17:
                    System.out.println("Поиск наименьшего локального максимума:");
                    mt.task17();
                    break;
                case 18:
                    System.out.println("Сортировка столбцов по характеристикам:");
                    mt.task18();
                    break;
                case 19:
                    System.out.println("Заполнение главной диагонали по убыванию:");
                    mt.task19();
                    break;
            }
            System.out.println("\nРезультат:");
            mt.printMatrix();
            System.out.println("----------------------------------------");
        }
    }
}