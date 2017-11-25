package expert;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Rule {
    private String rhs;
    private String lhs;
    private Stack<Character> postfix_rhs;
    private boolean value;
    ArrayList<Fact> rhs_facts;
    ArrayList<Fact> lhs_facts;


    private void get_rhs_facts()
    {


    }
    public Rule(String rule)
    {
        rhs = rule.split("=>")[0];
        lhs = rule.split("=>")[1];
    }
}