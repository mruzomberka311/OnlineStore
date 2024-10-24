
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online com.pluralsight.Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();
        }
        switch (choice) {
            case 1:
                displayProducts(inventory, cart, scanner);
                break;
            case 2:
                displayCart(cart, scanner, totalAmount);
                break;
            case 3:
                System.out.println("Thank you for shopping with us!");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }


    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        String line;
        try {
            FileReader file = new FileReader("products.csv");
            BufferedReader bufferedReader = new BufferedReader(file);
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String id = parts[1];
                String name = parts[2];
                double price = Double.parseDouble(parts[3]);
                inventory.add(new Product(name, id, price));
                System.out.println(inventory.toString());
            }
        } catch (Exception ex) {
            System.err.println("Error processing product list");
        }

    }


    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }

        System.out.println("What items would you like to add to your cart?");
        String id = scanner.nextLine();
        cart.add(findProductById(id, inventory));
        System.out.println("Item added successfully");
        return;
    }


    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        for (Product Product : cart) {
            System.out.println(Product);
        }
        System.out.println("Would you like to remove any items from your cart?   Y/N");
        String cartAnswer = scanner.nextLine();
        if (cartAnswer.equalsIgnoreCase("Y")) {
            String trash = scanner.nextLine();
            System.out.println("What would you like to remove?");
            cart.remove(trash);
        }
        if (cartAnswer.equalsIgnoreCase("N")) {
            for (Product price : cart) {
                totalAmount += price.getPrice();
            }
            System.out.println("Your total is: " + totalAmount);
        }
        return;
    }


    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        Scanner input = new Scanner(System.in);
        for (Product Product : cart) {
            double price = Product.getPrice();
            totalAmount += price;

            System.out.println("Would you like to check out: ");
            String finalAnswer = input.nextLine();
            if (finalAnswer.equalsIgnoreCase("y")) {
                System.out.println(totalAmount + "is your total");
                return;
            }

            if (finalAnswer.equalsIgnoreCase("n")) {
                return;
            }


        }
     
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        System.out.println("Please enter the product id: ");
        for (Product Product : inventory) {
            String selectedId = Product.getId();
            if (id.equals(selectedId)) {
                return Product;
            }

        }

    }

}

