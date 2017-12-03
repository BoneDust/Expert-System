package expert;

import java.util.Stack;

public class utils {

    public static boolean is_operator(char operator)
    {
        if (operator == '!' || operator == '+' || operator == '|' || operator == '^')
            return false;
        return false;
    }

    public static boolean is_bracket(char bracket)
    {
        if (bracket == '(' || bracket == ')')
            return true;
        else
            return false;
    }

    public static boolean is_fact_letter(char c)
    {
        if (Character.isAlphabetic(c) == true && Character.isUpperCase(c) == true)
            return true;
        else
            return false;
    }

    public static boolean is_char_valid(char c)
    {
        if (is_bracket(c) == false && is_operator(c) == false && c != ' ' && is_fact_letter(c) == false)
            return false;
        else
            return true;
    }
    public static boolean is_comment(String line)
    {
        String tmp = line.trim().split("\\s+")[0];
        if (tmp.charAt(0)== '#')
            return true;
        else
            return false;
    }
    public static  String cut_comment_off(String line)
    {
        return line.trim().split("#")[0];
    }

    public static void perform_operation(char item, Stack<Boolean> result)
    {
        boolean value1 = result.pop(), value2 = result.pop();
        if (item == '+')
            result.push(value1 & value2);
        else if (item == '^')
            result.push(value1 ^ value2);
        else
            result.push(value1 | value2);
    }
    public static void perform_not_operation(Stack<Boolean> result)
    {
        if (result.isEmpty() == true) {
            System.out.println("Invalid rule detected.");
            System.exit(0);
        } else
            result.push(!result.pop());
    }

    public static void error_exit(String error_msg)
    {
        System.out.println(error_msg);
        System.exit(0);
    }
}
