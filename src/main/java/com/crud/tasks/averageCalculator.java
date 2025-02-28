package com.crud.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class averageCalculator {
    public static double calculateAverageWeight(List<Integer> grades, List<Integer> weights) {
        if (grades.size() != weights.size()) {
            throw new IllegalArgumentException("Liczba ocen i wag musi być taka sama.");
        }

        if (grades.isEmpty() || weights.isEmpty()) {
            throw new IllegalArgumentException("Listy ocen i wag nie mogą być puste.");
        }

        for (int grade : grades) {
            if (grade < 1 || grade > 6) {
                throw new IllegalArgumentException("Oceny muszą być w zakresie od 1 do 6.");
            }
        }

        for (int weight : weights) {
            if (weight < 1 || weight > 10) {
                throw new IllegalArgumentException("Wagi muszą być w zakresie od 1 do 10.");
            }
        }
        int weightedSum = 0;
        int totalWeight = 0;

        for (int i = 0; i < grades.size(); i++) {
            weightedSum += grades.get(i) * weights.get(i);
            totalWeight += weights.get(i);
        }

        return (double) weightedSum / totalWeight;





    }

    public static void main(String[] args) {
        List<Integer> grades = Arrays.asList(3, 1, 1, 5, 6, 4);
        List<Integer> weights = Arrays.asList(4, 6, 8, 4, 4, 10);

        try {
            double average = calculateAverageWeight(grades, weights);
            System.out.println("Średnia ważona: " + average);
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }

    }
}
