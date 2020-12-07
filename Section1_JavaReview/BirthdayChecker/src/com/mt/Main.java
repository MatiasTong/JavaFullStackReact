package com.mt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the 100% Scientifically Accurate Birthday Calculator!\nWhat's your birthday?");
        String birthday = sc.nextLine();
        LocalDate birthdayDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        System.out.println("That means you were born on a " + birthdayDate.getDayOfWeek() + "!");
        LocalDate dateThisYear = LocalDate.of(LocalDate.now().getYear(), birthdayDate.getMonthValue(), birthdayDate.getDayOfMonth());
        LocalDate dateNextYear = LocalDate.of(LocalDate.now().getYear()+1, birthdayDate.getMonthValue(), birthdayDate.getDayOfMonth());
        System.out.println("This year it falls on a " + dateThisYear.getDayOfWeek() + "...");

        long noOfDaysBetween;
        int age;
        if (dateThisYear.isBefore(LocalDate.now()) || dateThisYear.isEqual(LocalDate.now())) {
            noOfDaysBetween = Math.abs(LocalDate.now().until(dateNextYear, ChronoUnit.DAYS));
            age = dateNextYear.getYear() - birthdayDate.getYear();
        } else{
            noOfDaysBetween = Math.abs(LocalDate.now().until(dateThisYear, ChronoUnit.DAYS));
            age = LocalDate.now().getYear() - birthdayDate.getYear();
        }


        System.out.printf("Since today is %s, \nthere's only %d more day until the next one when you turn %d",
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
                noOfDaysBetween, age);


    }
}
