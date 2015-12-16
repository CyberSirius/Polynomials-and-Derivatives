package com.rashev.search;


import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        printDerivative(args[0]);
    }

    public static void printDerivative(String input) {
        System.out.println("The derivative of f(x) = " + input + " is:");
        ArrayList<Term> simple = expressionSimplifying(input);
        System.out.print("f'(x) = ");
        for (int i = 0; i < simple.size(); i++) {
            Term currentTerm = deriveTerm(simple.get(i));
            if (currentTerm.isConstant())
                System.out.print("0");
            else if (currentTerm.getCoefficient() == 0)
                continue;
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
        String[] termsString = expression.split("\\+");
        if (!expression.contains("+")) {
            termsString = new String[1];
            termsString[0] = expression;
        }
        ArrayList<Term> terms = new ArrayList<>();
        ArrayList<Term> simplifiedExpression = new ArrayList<>();
        for (String term : termsString) {
            if (term.contains("x"))
                terms.add(new Term(term));
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
            return new Term(true);//if term is constant, derived is 0
        return new Term(term.getCoefficient() * term.getPower(), term.getPower() - 1);
    }
}
