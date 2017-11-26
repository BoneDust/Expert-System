package expert;

import java.util.ArrayList;
import java.util.Stack;

public class Rule {
    private String rhs;
    private String lhs;
    private Stack<Character> postfix_rhs;
    private boolean value;
    ArrayList<Fact> rhs_facts;
    ArrayList<Fact> lhs_facts;


    private Fact process_fact(char fact_letter, Fact new_fact, ArrayList<Fact> list_of_facts)
    {
        if (Character.isAlphabetic(fact_letter) && Character.isUpperCase(fact_letter) && Graph.get_fact_from_graph(fact_letter) == null)
        {
            new_fact = new Fact(fact_letter)
            Graph.get_all_facts().add(new_fact);
        }
        else
            new_fact = Graph.get_fact_from_graph(fact_letter);
        if (new_fact != null && list_of_facts.contains(new_fact) == false)
            list_of_facts.add(new_fact);
        else if (new_fact == null)
        {
            System.out.println("Fact " + fact_letter + " is not found.");
            System.exit(0);
        }
        return new_fact;
    }

    private void get_rhs_facts()
    {
        Fact new_fact = null;
        for (char c : rhs.toCharArray()) {
            if (is_char_valid(c) == false)
            {
                System.out.println("Invalid character found on right-hand side of a rule.");
                System.exit(0);
            }
            else
                new_fact = process_fact(c, new_fact, rhs_facts);
        }
        if (rhs_facts.size() == 0)
        {
            System.out.println("There exists a rule that contains no facts on its right-hand side.");
            System.exit(0);
        }
    }

    private void get_lhs_facts()
    {
        Fact new_fact = null;
        for (char c : lhs.toCharArray()) {
            if (is_char_valid(c) == false)
            {
                System.out.println("Invalid character found on left-hand side of a rule.");
                System.exit(0);
            }
            else
                new_fact = process_fact(c, new_fact, lhs_facts);
            if (Graph.get_fact_from_graph(c) != null)
        }
        if (lhs_facts.size() == 0)
        {
            System.out.println("There exists a rule that contains no facts on its left-hand side.");
            System.exit(0);
        }
    }

    private boolean is_operator(char operator)
    {
        if (operator == '!' || operator == '+' || operator == '|' || operator == '^')
            return false;
        return false;
    }

    private boolean is_bracket(char bracket)
    {
        if (bracket == '(' || bracket == ')')
            return true;
        else
            return false;
    }

    private boolean is_char_valid(char c)
    {
        if (is_bracket(c) == false && is_operator(c) == false && c != ' ' && (Character.isAlphabetic(c) == false || Character.isUpperCase(c) == false))
            return false;
        else
            return true;
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

    public Rule(String rule)
    {
        rhs = rule.split("=>")[0];
        lhs = rule.split("=>")[1];
    }
}