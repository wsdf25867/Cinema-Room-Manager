package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner sc = new Scanner(System.in);
    static int rows;
    static int seats;
    static char[][] cinema;

    static int currentIncome = 0;
    static int totalIncome = 0;
    static int numberOfPurchasedTickets = 0;

    public static void main(String[] args) {
        // Write your code here

        // get rows and seats
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();

        cinema = new char[rows + 1][seats + 1];

        // Make cinema
        cinema[0][0] = ' ';
        for (int i = 1; i < rows + 1; i++) cinema[i][0] = (char) ('0' + i);
        for (int i = 1; i < seats + 1; i++) cinema[0][i] = (char) ('0' + i);
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < seats + 1; j++) {
                cinema[i][j] = 'S';
            }
        }

        // Calculate total income
        calculateTotalIncome();

        int choice;
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            if (choice == 1) {
                printCinema();
            } else if (choice == 2) {
                buyAticket();
            } else if (choice == 3) {
                showStatistics();
            } else if (choice == 0) {
                break;
            }
        }
    }

    private static void calculateTotalIncome() {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= seats; j++) {
                    totalIncome += i > rows / 2 ? 8 : 10;
                }
            }

        }
    }

    private static void showStatistics() {
        double percentageOfPurchase = getPercentage();
        System.out.printf("Number of purchased tickets: %d\n", numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentageOfPurchase);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    private static double getPercentage() {
        float countB = 0;
        float countS = 0;
        for (char[] chars : cinema) {
            for (char aChar : chars) {
                if ('B' == aChar) {
                    countB++;
                }
                if ('S' == aChar) {
                    countS++;
                }
            }
        }
        return (countB / (rows * seats)) * 100;
    }

    private static void buyAticket() {
        System.out.println("Enter a row number:");
        int selectedRow = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int selectedSeat = sc.nextInt();

        if (selectedRow > rows || selectedSeat > seats) {
            System.out.println("Wrong input!");
            buyAticket();
            return;
        }


        if (cinema[selectedRow][selectedSeat] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyAticket();
            return;
        } else {
            cinema[selectedRow][selectedSeat] = 'B';
        }

        int totalSeats = rows * seats;
        int ticeketPrice;
        if (totalSeats <= 60) {
            ticeketPrice = 10;
        } else {
            ticeketPrice = selectedRow > rows / 2 ? 8 : 10;
        }
        currentIncome += ticeketPrice;
        numberOfPurchasedTickets++;
        System.out.printf("Ticket price: $%d", ticeketPrice);
        System.out.println();
    }

    private static void printCinema() {
        System.out.println("Cinema:");
        for (char[] chars : cinema) {
            for (char aChar : chars) {
                System.out.printf("%c ", aChar);
            }
            System.out.println();
        }
    }
}