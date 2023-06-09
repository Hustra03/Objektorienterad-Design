package se.kth.iv1350.erikmichel.seminar3.view;

import se.kth.iv1350.erikmichel.seminar3.model.Item;
import se.kth.iv1350.erikmichel.seminar3.controller.Controller;
import se.kth.iv1350.erikmichel.seminar3.controller.RegisterItemException;
import se.kth.iv1350.erikmichel.seminar3.intergration.ReceiptDTO;
import se.kth.iv1350.erikmichel.seminar3.intergration.SaleDTO;
import se.kth.iv1350.erikmichel.seminar3.intergration.SaleInfoDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class

public class View {

	private Controller controller;

	/*
	 * Creates an object of View type
	 * 
	 * @param controller is the controller object the view object will utalize
	 */
	public View(Controller controller) {
		this.controller = controller;
	}

	/*
	 * This is the method called by main, and where the user interface is located
	 * 
	 */
	public void startSystem() {
		boolean endOperation = false;
		int level = 1;
		while (endOperation == false) {
			int i;
			printMenu(level);
			i = intUserInput("Enter menu alternative : ");
			System.out.println("Option choosen is: " + i);
			switch (i) {
				case 1:
					level = optionOne(level);
					break;
				case 2:
					level = optionTwo(level);
					break;
			}

			System.out.println("");
		}

	}

	/*
	 * Handles menu and input/output for first menu option
	 * 
	 * @param level determines where in the program the user is located, and thus
	 * what should be shown at any one time
	 */
	private int optionOne(int level) {

		if (level == 1) {
			controller.startSale();
			System.out.println("Starting Sale");
			return 2;
		}

		if (level == 2) {
			int itemId = intUserInput("Enter item identifyer : ");
			System.out.println("Item identifyer is: " + itemId);

			int quantity = intUserInput("Enter quantity : ");
			System.out.println("Quantity is: " + quantity);

			try {
				printSaleDTO(controller.registerItem(itemId, quantity));
			} catch (RegisterItemException e) {
				printSaleDTO(controller.GetSale());
				System.out.println("Operation Faliure: Could Not Register Item With Id: " + e.getItemId());
				exceptionLogging(e);
			} catch (Exception e) {
				System.out.println("Operation Faliure: " + e.getMessage());
				exceptionLogging(e);
			}

			return 2;
		}

		if (level == 3) {
			return 4;
		}

		if (level == 4) {
			System.out.println("Total Price :" + controller.GetSaleInfo().getTotalPriceAfterDiscount());
			double amountPayment = doubleUserInput("Enter amount payment : ");
			while (amountPayment < controller.GetSaleInfo().getTotalPriceAfterDiscount()) {
				System.out.println("Ensure Payment Amount Is Sufficent For Sale, Total Price :"
						+ controller.GetSaleInfo().getTotalPriceAfterDiscount());
				amountPayment = doubleUserInput("Enter amount payment : ");
			}
			System.out.println("Amount payment is: " + amountPayment);
			printSaleInfo(controller.recivePayment(amountPayment));
			printReciept(controller.sendSaleInfo());
			return 4;
		}
		return 1;
	}

	/*
	 * Handles menu and input/output for second menu option
	 * 
	 * @param level determines where in the program the user is located, and thus
	 * what should be shown at any one time
	 */
	private int optionTwo(int level) {
		if (level == 2) {
			SaleInfoDTO saleInfo = controller.endSale();
			System.out.println("Total Price :" + saleInfo.getTotalPriceAfterDiscount() + "|| Total VAT : "
					+ saleInfo.getTotalVATAfterDiscount());
			return 3;
		}
		if (level == 3) {
			int customerId = intUserInput("Enter customer identifyer : ");
			System.out.println("Customer identifyer registered: " + customerId);
			printSaleInfo(controller.getDiscount(customerId));
			return 4;
		}
		return 1;
	}

	/*
	 * Prints information from <code>sale</code> to System.out
	 * 
	 * @param sale determines what should be printed and how that information should
	 * be printed
	 * 
	 */
	private void printSaleDTO(SaleDTO sale) {

		System.out.println("Sale Information : ");
		System.out.println("\tItem Information : ");
		System.out.println("\t{");
		for (int itemNumber = 1; itemNumber < sale.getSoldItems().size() + 1; itemNumber++) {
			
			printItem(sale.getSoldItems().get(itemNumber - 1), itemNumber);
		}
		System.out.println("\t}");
		System.out.println("Total Price : " + sale.getTotalPrice());
		System.out.println("Total VAT : " + sale.getTotalVAT());

	}

	/*
	 * Prints information from <code>saleInfo</code> to System.out
	 * 
	 * @param saleInfo determines what should be printed and how that information
	 * should be printed
	 * 
	 */
	private void printSaleInfo(SaleInfoDTO saleInfo) {

		printSaleDTO(saleInfo.getSale());
		if (saleInfo.getCustomerPaymentDTO() != null) {
			System.out.println("Change : " + saleInfo.getCustomerPaymentDTO().getChange());
		}
		System.out.println("Discounted total price : " + saleInfo.getTotalPriceAfterDiscount());
		System.out.println("Discounted total VAT : " + saleInfo.getTotalVATAfterDiscount());
		System.out.println("Customer id : " + saleInfo.getCustomerId());

	}

	/*
	 * Prints information from <code>item</code> to System.out
	 * 
	 * @param item determines what should be printed and how that information
	 * should be printed
	 * 
	 * @param itemNumber determines the number printed before item information
	 */
	private void printItem(Item item, int itemNumber) {
		System.out.print("\t " + itemNumber + ". ||");
		System.out.print(" ItemId :" + item.getItemDescriptionDTO().getItemId() + " ||");
		System.out.print(" Item Quantity :" + item.getQuantity() + " ||");
		System.out.print(" Name :" + item.getItemDescriptionDTO().getName() + " ||");
		System.out.print(" Per Quantity Price :" + item.getItemDescriptionDTO().getPrice() + " ||");
		System.out.print(" VATrate :" + item.getItemDescriptionDTO().getVATrate() + "% ||");
		System.out.print(" Description :" + item.getItemDescriptionDTO().getDescription() + " ||");
		System.out.println("");	}

	/*
	 * Prints information from <code>receipt</code> to System.out
	 * 
	 * @param receipt determines what should be printed and how that information
	 * should be printed
	 * 
	 */
	private void printReciept(ReceiptDTO receipt) {
		System.out.println("Reciept info :");
		System.out.print("Date And Time :" + receipt.getDateAndTime() + "||");
		System.out.print("Total Price :" + receipt.getTotalPrice() + "||");
		System.out.print("Total VAT :" + receipt.getTotalVAT() + "||");
		System.out.print("Total Payment :" + receipt.getTotalPayment() + "||");
		System.out.print("Total Change :" + receipt.getTotalChange() + "||");
		System.out.println("");
		System.out.println("Items sold:");
		int itemNumber = 1;
		for (Item item : receipt.getSoldItems()) {
			printItem(item, itemNumber);
			itemNumber+=1;
		}
	}

	/*
	 * Prints menu options information to System.out
	 * 
	 * @param level determines which strings should be printed
	 * 
	 */
	private void printMenu(int level) {

		if (level == 1) {
			System.out.println("1. Start Sale");
			System.out.println("2. Do Nothing");

		}
		if (level == 2) {
			System.out.println("1. Register New Item");
			System.out.println("2. Stop Registering Items");

		}
		if (level == 3) {
			System.out.println("1. No Discount");
			System.out.println("2. Register Discount");

		}
		if (level == 4) {
			System.out.println("1. Enter Payment");
			System.out.println("2. Remove Current Sale");
		}

	}

	/*
	 * Accepts user input of int type
	 * 
	 * @param stringToBePrinted is the string describing what the user should input
	 * 
	 * @return i is the users input which will be used for other sections of the
	 * code
	 */
	private int intUserInput(String stringToBePrinted) {

		int i = 404;
		while (true) {
			try {
				Scanner myObj = new Scanner(System.in);
				System.out.println("");
				System.out.print(stringToBePrinted);
				i = myObj.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invald Input, try again with valid INT");
				exceptionLogging(e);
			}

		}
		return i;
	}

	/*
	 * Accepts user input of double type
	 * 
	 * @param stringToBePrinted is the string describing what the user should input
	 * 
	 * @return i is the users input which will be used for other sections of the
	 * code
	 */
	private double doubleUserInput(String stringToBePrinted) {

		double i = 404;
		while (true) {
			try {
				Scanner myObj = new Scanner(System.in);
				System.out.println("");
				System.out.print(stringToBePrinted);
				i = myObj.nextDouble();
				break;
			} catch (Exception e) {
				System.out.println("Invald Input, try again with valid DOUBLE");
				exceptionLogging(e);
			}

		}

		return i;
	}

	private void exceptionLogging(Exception e1) {
		try {
			FileWriter myWriter = new FileWriter("exceptionLog.txt", true);
			myWriter.write("Exception Thrown : " + e1.toString() + e1.getCause() +"\n");
			myWriter.close();
			System.out.println("Successfully wrote to exceptionLog.txt");
		} catch (IOException e) {
			System.out.println("An IOException occurred when attempting to print to file exceptionLog.txt");
		} catch (Exception e) {
			System.out.println("An unknown Exception occurred when attempting to print to file exceptionLog.txt");
		}
	}

}
