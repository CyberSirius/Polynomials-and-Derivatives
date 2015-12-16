package com.rashev.search;

public class Term {
    private int coefficient;
    private int power;
    private boolean isConstant;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Term(int coefficient, boolean isConstant) {
        this.coefficient = coefficient;
        this.isConstant = isConstant;
        this.power = 1;
    }

    public Term(String term) {
        if (term.contains("*"))
            this.coefficient = Integer.parseInt(term.split("\\*")[0]);
        else this.coefficient = 1;
        if (term.contains("^"))
            this.power = Integer.parseInt(term.split("\\^")[1]);
        else this.power = 1;
        if (!term.contains("x")) {
            isConstant = true;
            this.coefficient = Integer.parseInt(term);
            this.power = 1;
        }
        this.isConstant = false;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public boolean isConstant() {
        return isConstant;
    }

    @Override
    public String toString() {
        return this.coefficient + "*x^" + this.power + this.isConstant();//case where coefficient is 1 || power is 1
    }
}
