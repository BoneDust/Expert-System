package expert;

import java.util.ArrayList;

public class Graph {

    private static ArrayList<Fact> all_facts;
    private static ArrayList<Rule> all_rules;
    private ArrayList<Fact> facts_to_determine;

    public void add_new_fact_to_graph(Fact node)
    {
        all_facts.add(node);
    }

    public void add_new_rule_to_graph(Rule rule)
    {
        all_rules.add(rule);
    }

    public static Fact get_fact_from_graph(char letter)
    {
        for (Fact fact : all_facts)
        {
            if (fact.get_letter() == letter)
                return fact;
        }
        return null;
    }

    public void set_facts_to_be_determined(String line)
    {
        String temp = line.split("[?]")[1];
        for (char c : temp.toCharArray())
        {
            if (Character.isAlphabetic(c) && get_fact_from_graph(c) != null && facts_to_determine.contains(c) == false)
                facts_to_determine.add(get_fact_from_graph(c));
            if (get_fact_from_graph(c) == null)
            {
                System.out.println("Fact " + c + " is not found.");
                System.exit(0);
            }
        }
    }

    public void set_initial_values(String line)
    {
        String temp = line.split("=")[1];
        for (char c : temp.toCharArray())
        {
            if (Character.isAlphabetic(c) && get_fact_from_graph(c) != null) {
                get_fact_from_graph(c).set_value(true);
                get_fact_from_graph(c).set_determined(true);
            }
            if (get_fact_from_graph(c) == null)
            {
                System.out.println("Fact " + c + " is not found.");
                System.exit(0);
            }
        }
    }

    private boolean are_facts_determined()
    {
        int count = 0;
        for (Fact fact : facts_to_determine)
        {
            if (fact.is_determined() == true)
                count++;
        }
        if (count == facts_to_determine.size())
            return true;
        else
            return false;
    }

    public void determine_facts()
    {
        while (are_facts_determined() == false)
        {
            for (Fact fact : facts_to_determine)
            {
                if (fact.is_determined() == true)
                {
                    if (fact.is_printed() == false)
                        System.out.println("The value of " + fact.get_letter() + " is " + fact.get_value());
                }
                else
                    fact.determine_value();
            }
        }
    }

    public static ArrayList<Fact> get_all_facts()
    {
        return  all_facts;
    }

    public static ArrayList<Rule> get_all_rules()
    {
        return  all_rules;
    }
}

