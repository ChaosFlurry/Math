package com.math.calculus;


import java.util.*;

@SuppressWarnings("unused")
public class Function {
    private String equation;
    private ArrayList<Term> terms;
    private String expandedEquation;
    private String factoredEquation;

    public Function(String equation) {
        this.equation = equation;
    }
    
    public Function(ArrayList<Term> terms) {
        this.equation = resolveEquation(terms);
    }
    
    public Function(Term... terms) {
        this.equation = resolveEquation(new ArrayList<>(Arrays.asList(terms)));
    }
    
    
    public static ArrayList<Term> parseEquation(String equation) {
        // Evaluate inner brackets and expand
        
        // Separate Terms using regex
        
        // Add each Term into a List
    }
    
    public static String resolveEquation(ArrayList<Term> terms) {
        // Sort terms according to descending power
        Collections.sort(terms);
        
        // Group like terms
        Map<Integer, Integer> groupedTerms = new HashMap<>();
        for (Term t : terms) {
            if (groupedTerms.containsKey(t.getPower())) {
                groupedTerms.put(t.getPower(), groupedTerms.get(t.getPower()) + t.getCoefficient());
            } else {
                groupedTerms.put(t.getPower(), t.getCoefficient());
            }
        }
        
        ArrayList<Term> sortedTerms = new ArrayList<>();
        for (int coefficient : groupedTerms.keySet()) {
            int power = groupedTerms.get(coefficient);
            Term parsedTerm = new Term(groupedTerms.get(coefficient), power);
            sortedTerms.add(parsedTerm);
        }
        
        // Add each element to a String
        String equation = "";
        for (Term t : sortedTerms) {
            if (equation.isEmpty()) {
                equation += t.toString();
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
