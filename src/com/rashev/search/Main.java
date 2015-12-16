package com.rashev.search;


import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        printDerivative(args[0].replaceAll("\\s", ""));
    }

    public static void printDerivative(String input) {
        ArrayList<Term> simple = expressionSimplifying(input);
        System.out.println("The derivative of f(x) = " + simple + " is:");
        System.out.print("f'(x) = ");
        for (int i = 0; i < simple.size(); i++) {
            Term currentTerm = deriveTerm(simple.get(i));
            if (currentTerm.getCoefficient() == 0 && i != simple.size() - 1)//if the coefficient is 0, the value of the term is 0
                continue;
            else if (currentTerm.isConstant())
                System.out.print(currentTerm.getCoefficient());

            else if (currentTerm.getPower() == 0)
                System.out.print(currentTerm.getCoefficient());
            else if (currentTerm.getPower() == 1)
                System.out.print(currentTerm.getCoefficient() + "*x");
            else System.out.print(currentTerm.getCoefficient() + "*x^" + currentTerm.getPower());
            if (i != simple.size() - 1) {
                System.out.print("+");
            }
        }
        if (simple.isEmpty())
            System.out.println(0);
    }

    public static ArrayList<Term> expressionSimplifying(String expression) {
        String[] termsString;
        if (expression.contains("+")) {
            termsString = expression.split("\\+");
        } else {
            termsString = new String[1];
            termsString[0] = expression;
        }
        ArrayList<Term> terms = new ArrayList<>();
        ArrayList<Term> simplifiedExpression = new ArrayList<>();
        for (String term : termsString) {
            if (term.contains("x"))
                terms.add(new Term(term));
            else terms.add(new Term(Integer.parseInt(term), true));
        }
        for (int i = 0; i < terms.size(); i++) {
            Term temp = terms.get(i);
            for (int j = i + 1; j < terms.size(); j++) {
                if ((temp.getPower() == terms.get(j).getPower()) && (!temp.isConstant() && !terms.get(j).isConstant())) {
                    temp.setCoefficient(temp.getCoefficient() + terms.get(j).getCoefficient());
                    terms.remove(terms.get(j));
                }
            }
            simplifiedExpression.add(temp);
        }
        return simplifiedExpression;
    }

    public static Term deriveTerm(Term term) {
        if (term.isConstant())
            return new Term(0, true);
        return new Term(term.getCoefficient() * term.getPower(), term.getPower() - 1);
    }
}
