package expert;

import java.util.ArrayList;
import java.util.Stack;

public class Rule {

    private String rhs;
    private String lhs;
    private boolean value;
    private boolean evaluated;
    private Stack<Character> postfix_lhs;
    private ArrayList<Fact> rhs_facts;
    private ArrayList<Fact> lhs_facts;


    private Fact process_fact(char fact_letter, ArrayList<Fact> list_of_facts)
    {
        Fact new_fact;
        if (utils.is_fact_letter(fact_letter) && Graph.get_fact_from_graph(fact_letter) == null)
        {
            new_fact = new Fact(fact_letter);
            Graph.get_all_facts().add(new_fact);
        }
        else
            new_fact = Graph.get_fact_from_graph(fact_letter);
        if (new_fact != null && list_of_facts.contains(new_fact) == false)
            list_of_facts.add(new_fact);
        else if (new_fact == null)
        {
            System.out.println("Fact '" + fact_letter + "' is not found.");
            System.exit(0);
        }
        return new_fact;
    }

    private void get_lhs_facts()
    {
        Fact new_fact = null;
        for (char c : lhs.toCharArray())
        {
            if (utils.is_char_valid(c) == false)
                utils.error_exit("Invalid character found on left-hand side of a rule.");
            else
                new_fact = process_fact(c, lhs_facts);
        }
        if (lhs_facts.size() == 0)
            utils.error_exit("There exists a rule that contains no facts on its left-hand side.");
    }

    private void get_rhs_facts()
    {
        Fact new_fact = null;
        for (char c : rhs.toCharArray()) {
            if (utils.is_char_valid(c) == false)
                utils.error_exit("Invalid character found on right-hand side of a rule.");
            else
                new_fact = process_fact(c, rhs_facts);
            for (Fact fact : lhs_facts)
            {
                if (new_fact.getLinked_facts().contains(fact) == false)
                    new_fact.getLinked_facts().add(fact);
            }
            new_fact.getRules_involved_in().add(this);
        }
        if (rhs_facts.size() == 0)
            utils.error_exit("There exists a rule that contains no facts on its right-hand side.");
    }

    private void to_postfix(String rule)
    {
        Stack<Character> holder = new Stack<>();

        for (char c : rule.toCharArray())
        {
            if (utils.is_char_valid(c) == false)
               utils.error_exit("Invalid character encountered in one of the rules.");
            else if (utils.is_fact_letter(c))
                holder.push(c);
            else if (utils.is_operator(c) || c == '(')
            {
                while (postfix_lhs.peek() == '!' && c != '(' && c != '!' ) // ! has highest precedence
                    holder.push(postfix_lhs.pop());
                while (postfix_lhs.peek() == '+' && c != '(' && c != '+') // followed by +
                    holder.push(postfix_lhs.pop());
                while (postfix_lhs.peek() == '^' && c != '(' && c != '^') // then ^
                    holder.push(postfix_lhs.pop());
                postfix_lhs.push(c);
            }
            else if (c == ')')
            {
                while (!postfix_lhs.empty() && postfix_lhs.peek() != '(')
                    holder.push(postfix_lhs.pop());
                if (postfix_lhs.peek() == '(')
                    postfix_lhs.pop();
            }
        }
        while(holder.empty() == false)
            postfix_lhs.push(holder.pop());
    }

    public void evaluate_rule()
    {
        Stack<Boolean> result = new Stack<>();
        char item;
        Fact fact;

        while(postfix_lhs.empty() == false)
        {
            item = postfix_lhs.pop();
            if (utils.is_fact_letter(item))
            {
                fact = Graph.get_fact_from_graph(item);
                if (fact.is_determined())
                    result.push(fact.get_value());
                else
                {
                    fact.determine_value();
                    if (fact.is_determined())
                        result.push(fact.get_value());
                    else
                        return;
                }
            }
            else if (item == '!')
                utils.perform_not_operation(result);
            else
            {
                if (result.size() < 2)
                    utils.error_exit("Invalid rule detected.");
                else
                    utils.perform_operation(item, result);
            }
        }
        if (result.size() != 1)
            utils.error_exit("Invalid rule detected.");
        evaluated = true;
        value = result.pop();
    }

    public boolean is_evaluated()
    {
        return evaluated;
    }
    public Rule(String rule)
    {
        lhs = rule.trim().split("=>")[0].trim();
        rhs = rule.trim().split("=>")[1].trim();
        value = false;
        evaluated = false;
        postfix_lhs = new Stack<>();
        rhs_facts = new ArrayList<>();
        lhs_facts = new ArrayList<>();
        to_postfix(rhs);
    }
}