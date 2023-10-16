import java.util.List;

public class JEP441_PatternMatchingForSwitch {
    public static void main(String[] args) {
        exhaustivenessAndCompatibility();
        expressionWithPatternMatching();
        scopeWithPatternMatching();
        enhancedTypeChecking ();
        withGuardedCaseLabel();
        withEnumConstants();
    }


    private static void exhaustivenessAndCompatibility() {
        var color = Color.RED;

        // default case way
        switch (color) {
            case BLUE:
                System.out.println("The color is BLUE");
                break;
            case RED:
                System.out.println("The color is RED");
                break;
        }

        //lambda expressions
        switch (color){
            case BLUE -> System.out.println("The color is BLUE");
            case RED -> System.out.println("The color is RED");
        }

    }

    private static void expressionWithPatternMatching() {
        Object anyValue = 42;

        String message = switch (anyValue) {
            case Integer i -> String.format("int %d", i);
            case String s -> String.format("string %s", s);
            case Double d -> String.format("double %d", d);
            case null -> "n/a";
            default -> anyValue.toString();
        };

        System.out.println(message);
    }


    private static void scopeWithPatternMatching() {
        Number number = 0;

        String message = null;
        switch (number) {
            case Integer i when i == 0:
                System.out.println("num is 0");
                break;
            case Integer x when x < 0:
                message = "num is negative number";
                break;
            case Integer n when n == 87:
                message = "number is " + n;
                break;
            default:
                message = "unhandled";
        }
        System.out.println("switch statement: " + message);


        //notice the yield syntax
        message = switch (number) {
            case Integer i when i == 0:
                System.out.println("num is 0");
                yield "zero =0";
            case Integer x when x < 0:
                yield "num is negative number";
            case Integer n when n == 87:
                yield "number is " + n;
            default:
                yield "unhandled";
        };
        System.out.println("switch expression: " + message);
    }

    private static void enhancedTypeChecking() {
        Object value = 42;
        var message = switch (value) {
            case null -> "The value is `null`";
            case String s -> "Is String: " + s;
            case Integer n -> "is an integer: " + n;
            case Number n -> "Is a Number: " + n;
            case int[] ar -> "Is an array of number: " + ar;
            case List
                    list -> "Is a list of some type: " + list;
            // can infer the record generic type
            case Wrapper(var v) -> "Wrapped value: " + v;
            default -> "Is untested type =(: " + value.toString();
        };
        System.out.println(message);
    }

    static void withGuardedCaseLabel() {
        Object obj = 42;

        // JDK 20 changed `&&` to `when`
        String message = switch (obj) {
            // case Integer i when (i < 0) -> "negative number";
            case Integer i when i < 0 -> "negative number";
            case Integer i when i == 0 -> "zero";
            case Integer i when i > 0 && i <= 100 -> "positive number between 0 and 100";
            // must be after the guarded case as it is dominant
            case Integer i -> "number";
            // required to be exhaustive
            default -> obj.toString();
        };
        System.out.println(message);
    }

    static void withEnumConstants() {
        Object color = Color.RED;

        // before
        var colorName = switch (color) {
            case Color s when s == Color.RED -> "RED";
            case Color s when s == Color.BLUE -> "BLUE";
            case Color s when s == Color.GREEN -> "GREEN";
            default -> "none";
        };

        // JDK 21: we can use the qualified name when switching over an enum type
        // we should use all qualified name when at least one case is
        colorName = switch (color) {
            case Color.RED -> "RED";
            case Color.BLUE -> "BLUE";
            case Color.GREEN -> "GREEN";
            default -> "other type";
        };
        System.out.println(colorName);
    }
    enum  Color {RED, BLUE, GREEN}
    record Wrapper<T>(T t) {}
}
