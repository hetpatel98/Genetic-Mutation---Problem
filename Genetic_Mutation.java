package com.example;
import java.io.*;
import java.util.*;

public class Genetic_Mutation {

    //Just implementing the below method assuming we have this method being called from some main function

    static int findMutationDistance(String start, String end, String[] bank) {

        Set<String> bankSet = new HashSet<String>(Arrays.asList(bank)); //declare a HashSet based from the bank of DNAs

        Set<String> starts = new HashSet<String>(); //contains all the "starting" or base DNA for the current iteration
        starts.add(start); //for the first iteration the base DNA is the "start" string
        return calculate(starts, end, bankSet);
    }

    //method to check if the next step is possible i.e. it only differs by 1 character
    private static boolean isValidNextStep(String start, String s) {
        boolean diffCharFound = false;
        for (int i=0; i<start.length(); i++)
        {
            if (start.charAt(i) != s.charAt(i)){
                if (diffCharFound){
                    return false; //if 2 or more different chars were found, false was returned
                }
                diffCharFound =true;
            }
        }
        return diffCharFound; // returns True, since if 2 or more different chars were found, false was already returned
    }

    public static int calculate(Set<String> starts, String end, Set<String> bank){
        if(starts.contains(end)){ //if start and end string are the same, the distance is 0
            return 0;
        }
        Set<String> bankMinusStart = new HashSet<String>(bank); //HashSet of all remaining bank DNAs that havent been checked
        bankMinusStart.removeAll(starts); //remove all the "starts" which are the base DNAs of the previous iterations from the bank
        Set<String> nextSteps = new HashSet<String> ();//set to track all possible nextSteps
        for (String start : starts) { //iterate trough the "starts" Set
            for (String mutation : bankMinusStart) { //iterate throught the "bankMinusStart" Set
                if (isValidNextStep(start, mutation)) {
                    if (mutation.equals(end)) { //if the DNA is a valid next step from the base or start string of the current iteration
                        return 1;
                    } else {
                        nextSteps.add(mutation); //add the mutation to the nextSteps Set if it is not the end DNA
                    }
                }
            }
        }
        if(nextSteps.size() == 0) //if nextSteps is empty, than no posible mutations were found, so return -1;
            return -1;
        int remainingDistance = calculate(nextSteps, end, bankMinusStart); //recursively call the calculate function using the nextSteps as the Set of the start strings for                                                                              for the next iteration
        return remainingDistance == -1 ? -1 : remainingDistance + 1; //return -1 if remainingDistance is -1, else return the remainingDistance + 1;
    }



}
