package expert;

import java.util.ArrayList;

public class Fact {
    private  boolean value;
    private  boolean printed;
    private  char letter;
    private  boolean determined;
    private  ArrayList<Fact> linked_facts;
    private  ArrayList<Rule> rules_involved_in;


    public Fact (char letter){
        this.letter = letter;
        value = false;
        printed = false;
        determined = false;
        linked_facts = new ArrayList<>();
        rules_involved_in  = new ArrayList<>();
    }

    public  boolean can_be_determined()
    {
        if (linked_facts.size() > 0)
            return true;
        else
        {
            determined = true;
            return false;
        }
    }

    public void determine_value()
    {
        boolean propsed_value;

        for (Rule rule : rules_involved_in)
        {
            if (rule.is_evaluated() == false)
                rule.evaluate_rule();
        }
        for (Rule rule : rules_involved_in)
        {
            if (rule.is_evaluated() == true)
            {
                //To-Do need to finish this
            }
        }
    }

    public char get_letter()
    {
        return letter;
    }

    public boolean get_value()
    {
        return value;
    }

    public boolean is_printed()
    {
        return printed;
    }

    public boolean is_determined()
    {
        return determined;
    }

    public void set_value(boolean value)
    {
        this.value = value;
    }

    public void set_printed(boolean printed)
    {
        this.printed = printed;
    }

    public void set_determined(boolean determined)
    {
        this.determined = determined;
    }

    public ArrayList<Fact> getLinked_facts() {
        return linked_facts;
    }

    public ArrayList<Rule> getRules_involved_in() {
        return rules_involved_in;
    }
}

