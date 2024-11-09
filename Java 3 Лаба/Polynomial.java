public class Polynomial {
    private RationalFraction[] coefficients;

    public Polynomial(RationalFraction[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    public int getDegree() {
        return coefficients.length - 1;
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.getDegree(), other.getDegree());
        RationalFraction[] newCoefficients = new RationalFraction[maxDegree + 1];

        for (int i = 0; i < newCoefficients.length; i++) {
            newCoefficients[i] = new RationalFraction(0, 1);
        }

        for (int i = 0; i < this.coefficients.length; i++) {
            newCoefficients[i] = this.coefficients[i];
        }

        for (int i = 0; i < other.coefficients.length; i++) {
            newCoefficients[i] = newCoefficients[i].add(other.coefficients[i]);
        }

        return new Polynomial(newCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (i != coefficients.length - 1) {
                result.append(" + ");
            }
            result.append("(").append(coefficients[i]).append(")");
            if (i > 0) {
                result.append("x^").append(i);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Polynomial[] polynomials = new Polynomial[3];

        polynomials[0] = new Polynomial(new RationalFraction[] {
                new RationalFraction(3, 4),
                new RationalFraction(2, 3),
                new RationalFraction(1, 2)
        });

        polynomials[1] = new Polynomial(new RationalFraction[] {
                new RationalFraction(4, 5),
                new RationalFraction(3, 4),
                new RationalFraction(2, 3)
        });

        polynomials[2] = new Polynomial(new RationalFraction[] {
                new RationalFraction(1, 5),
                new RationalFraction(1, 4),
                new RationalFraction(1, 3)
        });

        System.out.println("Полиномы:");
        for (int i = 0; i < polynomials.length; i++) {
            System.out.println("P" + i + "(x) = " + polynomials[i]);
        }

        Polynomial sum = polynomials[0];
        for (int i = 1; i < polynomials.length; i++) {
            sum = sum.add(polynomials[i]);
        }

        System.out.println("\nСумма всех полиномов:");
        System.out.println("Sum(x) = " + sum);
    }
}