import java.util.Stack;

public class rule_manipulation {


    public static boolean is_fact(char fact)
    {
        if (Character.isAlphabetic(fact))
            return true;
        return false;
    }

    public static boolean is_operator(char operator)
    {
        if (operator == '!' || operator == '+' || operator == '|' || operator == '^')
            return true;
        return false;
    }

    public static Stack<Character> to_postfix(String rule)
    {
        Stack<Character> postfix  = new Stack<>();
        Stack<Character> holder = new Stack<>();
        for (int i = 0; i < rule.length(); i++)
        {
            if (is_fact(rule.charAt(i)))
            {
                postfix.push(rule.charAt(i));
                if(!holder.empty())
                    postfix.push(holder.pop());
            }
            else if (is_operator(rule.charAt(i)) || rule.charAt(i) == '(')
                holder.push(rule.charAt(i));
            else if (rule.charAt(i) == ')')
            {
                while (!holder.empty() && holder.peek() != '(')
                    postfix.push(holder.pop());
                if (holder.peek() == '(')
                    holder.pop();
            }
        }
        return postfix;
    }
}
