public class RationalFraction {
    private int numerator;
    private int denominator;

    public RationalFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public RationalFraction add(RationalFraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    public RationalFraction subtract(RationalFraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    public RationalFraction multiply(RationalFraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    public RationalFraction divide(RationalFraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new RationalFraction(newNumerator, newDenominator);
    }

    private void normalize() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        RationalFraction[] fractions = {
                new RationalFraction(1, 2),
                new RationalFraction(2, 3),
                new RationalFraction(3, 4),
                new RationalFraction(4, 5),
                new RationalFraction(5, 6)
        };

        System.out.println("Исходный массив дробей:");
        for (RationalFraction fraction : fractions) {
            System.out.print(fraction + " ");
        }
        System.out.println();

        modifyEvenIndexedElements(fractions);

        System.out.println("\nМассив после модификации:");
        for (RationalFraction fraction : fractions) {
            System.out.print(fraction + " ");
        }
        System.out.println();

        System.out.println("\nДемонстрация арифметических операций:");
        RationalFraction a = new RationalFraction(1, 2);
        RationalFraction b = new RationalFraction(1, 3);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + a.add(b));
        System.out.println("a - b = " + a.subtract(b));
        System.out.println("a * b = " + a.multiply(b));
        System.out.println("a / b = " + a.divide(b));
    }

    public static void modifyEvenIndexedElements(RationalFraction[] fractions) {
        for (int i = 0; i < fractions.length - 1; i += 2) {
            fractions[i] = fractions[i].add(fractions[i + 1]);
        }
    }
}