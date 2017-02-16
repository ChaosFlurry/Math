package com.math.calculus;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        
        
        // Separate Terms
        ArrayList<String> unparsedTerms = new ArrayList<>();
        int head = 0;
        int tail;
        for (int i = 0; i < equation.length(); i++) {
            tail = i;
            String currentIndex = Character.toString(equation.charAt(i));
            if (currentIndex.equals("+") || currentIndex.equals("-")) {
                unparsedTerms.add(equation.substring(head, tail));
                head = tail;
            }
        }
        
        // Parse Terms by separating coefficient and power
        ArrayList<Term> equationTerms = new ArrayList<>();
        for (String s : unparsedTerms) {
            int coefficient;
            int power;
            if (s.contains("x")) {
                if (s.contains("^")) {
                    coefficient = Integer.parseInt(s.substring(0, s.indexOf("x")));
                    power = Integer.parseInt(s.substring(s.indexOf("^") + 1));

                } else {
                    coefficient = Integer.parseInt(s.substring(0, s.indexOf("x")));
                    power = 1;
                }
            } else {
                coefficient = Integer.parseInt(s);
                power = 0;
            }
            Term parsedTerm = new Term(coefficient, power);
            
            // Add each parsed Term into a List
            equationTerms.add(parsedTerm);
        }
        return equationTerms;
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
