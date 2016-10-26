
public class Test {

    public static void printLimit(Object obj, int width) {

        String val = obj.toString();

        if (val.length() > width && width > 0) {
            System.out.println(val.substring(0, width));
            printLimit(val.substring(width, val.length()), width);
        } else {
            System.out.println(val);
        }

    }

    public static void main(String[] args) {

        printLimit(args[1], Integer.valueOf(args[0]));

    }

}
