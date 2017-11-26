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


    private void process_fact(int count_facts, char fact_letter, ArrayList<Fact> list_of_facts)
    {
        Fact new_fact;
        if (Character.isAlphabetic(fact_letter) && Character.isUpperCase(fact_letter) && Graph.get_fact_from_graph(fact_letter) == null)
            Graph.get_all_facts().add(new Fact(fact_letter));
        new_fact = Graph.get_fact_from_graph(fact_letter);
        if (new_fact != null && list_of_facts.contains(new_fact) == false) {
            list_of_facts.add(new_fact);
            count_facts++;
        }
        else if (new_fact == null)
        {
            System.out.println("Fact " + fact_letter + " is not found.");
            System.exit(0);
        }
    }

    private void get_rhs_facts()
    {
        int count_facts = 0;
        for (char c : rhs.toCharArray())
        {
           process_fact(count_facts, c, rhs_facts);
        }
        if (count_facts == 0)
        {
            System.out.println("There exists a rule that contains no facts on its right-hand side.");
            System.exit(0);
        }
    }

    private void get_lhs_facts()
    {
        int count_facts = 0;
        for (char c : lhs.toCharArray())
        {
            process_fact(count_facts, c );
        }
        if (count_facts == 0)
        {
            System.out.println("There exists a rule that contains no facts on its leftt-hand side.");
            System.exit(0);
        }
    }
    public Rule(String rule)
    {
        rhs = rule.split("=>")[0];
        lhs = rule.split("=>")[1];
    }
}