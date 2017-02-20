package com.math.calculus;

import java.util.*;

@SuppressWarnings("unused")
public class Function {
    private String equation;
    private ArrayList<Term> terms;
    private String expandedEquation;
    private String factoredEquation;
    
    // Highest power
    // Lowest power
    // Number of terms (after being grouped)
    // nth term

    public Function(String equation) {
        this.terms = parseEquation(equation);
        this.equation = equation;
    }
    
    public Function(ArrayList<Term> terms) {
        this.terms = terms;
        this.equation = resolveEquation(terms);
    }
    
    public Function(Term... terms) {
        this.equation = resolveEquation(new ArrayList<>(Arrays.asList(terms)));
        this.terms = new ArrayList<>(Arrays.asList(terms));
    }
    
    public String getEquation() {
        return equation;
    }
    
    public ArrayList<Term> getTerms() {
        return terms;
    }
    
    public String getExpandedEquation() {
        return expandedEquation;
    }
    
    public String getFactoredEquation() {
        return factoredEquation;
    }
    
    @Override
    public String toString() {
        return equation;
    }
    
    public int f(int x) {
        int result = 0;
        for (Term t : terms) {
            result += Math.pow(x, t.getPower()) * t.getCoefficient();
        }
        return result;
    }
    
    public static ArrayList<Term> groupLikeTerms(ArrayList<Term> terms) {
        Map<Integer, Integer> groupedTerms = new HashMap<>();
        for (Term t : terms) {
            // K = power, V = coefficient
            if (groupedTerms.containsKey(t.getPower())) {
                groupedTerms.put(t.getPower(), groupedTerms.get(t.getPower()) + t.getCoefficient());
            } else {
                groupedTerms.put(t.getPower(), t.getCoefficient());
            }
        }
    
        ArrayList<Term> sortedTerms = new ArrayList<>();
        for (int power : groupedTerms.keySet()) {
            int coefficient = groupedTerms.get(power);
            Term parsedTerm = new Term(coefficient, power);
            sortedTerms.add(parsedTerm);
        }
        // Sort terms according to descending powers
        Collections.sort(sortedTerms);
        Collections.reverse(sortedTerms);
        return sortedTerms;
    }
    
    public static ArrayList<Term> parseEquation(String equation) {
        // Evaluate inner brackets and expand
        
        
        // Separate Terms
        ArrayList<String> unparsedTerms = new ArrayList<>();
        int head = 0;
        int tail;
        for (int i = 0; i < equation.length(); i++) {
            tail = i;
            String currentIndex = Character.toString(equation.charAt(i));
            if (i != 0 && (currentIndex.equals("+") || currentIndex.equals("-"))) {
                String previousIndex = Character.toString(equation.charAt(i - 1));
                // Negative signs can appear after the power operator "^"
                if (previousIndex.equals("^")) {
                    continue;
                }
                unparsedTerms.add(equation.substring(head, tail));
                head = tail;
            } else if (i == equation.length() - 1) {
                unparsedTerms.add(equation.substring(head, tail + 1));
            }
        }
        
        // Parse Terms and add to List
        ArrayList<Term> equationTerms = new ArrayList<>();
        for (String s : unparsedTerms) {
            Term parsedTerm = new Term(s);
            equationTerms.add(parsedTerm);
        }
        return equationTerms;
    }
    
    public static String resolveEquation(ArrayList<Term> terms) {
        // Group like terms
        ArrayList<Term> sortedTerms = groupLikeTerms(terms);
        
        // Add each element to a String
        String equation = "";
        for (Term t : sortedTerms) {
            if (equation.isEmpty()) {
                equation += t;
            } else {
                if (t.getCoefficient() > 0) {
                    equation += "+" + t.toString();
                } else {
                    equation += t.toString();
                }
            }
        }
        return equation;
    }
}
