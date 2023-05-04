package model;

import java.time.LocalDateTime;

import java.util.*; //For List to function

public class Receipt {

	private LocalDateTime dateAndTime;

	private List<Item> soldItems;

	private double totalPrice;

	private double totalVAT;

	private double totalPayment;

	private double totalChange;

	public Receipt(SaleInfo saleInfo) {
		this.dateAndTime = saleInfo.getCustomerPaymentDTO().getDate();
		this.soldItems = saleInfo.getSale().getSoldItems();
		this.totalPrice = saleInfo.getTotalPriceAfterDiscount();
		this.totalVAT = saleInfo.getTotalVATAfterDiscount();
		this.totalPayment = saleInfo.getCustomerPaymentDTO().getPaymentAmount();
		this.totalChange = saleInfo.getCustomerPaymentDTO().getChange();
	}


	/*
	 * Returns the list of sold items
	 * 
	 * @return <code>soldItems</code>, represents the list of items sold in sale
	 */
	public List<Item> getSoldItems() {
		return this.soldItems;
	}

	/*
	 * Returns the double TotalPrice
	 * 
	 * @return <code>TotalPrice</code>, represents the total price of sale
	 */
	public double getTotalPrice() {
		return this.totalPrice;
	}

	/*
	 * Returns the double TotalVAT
	 * 
	 * @return <code>TotalVAT</code>, represents the total value of VAT
	 */
	public double getTotalVAT() {
		return this.totalVAT;
	}

	
	public LocalDateTime getDateAndTime() {
		return this.dateAndTime;
	}

	/*
	 * Returns objects paymentAmount
	 * 
	 * @return paymentAmount represents the amount of payment given to
	 */
	public double getTotalPayment() {
		return this.totalPayment;
	}

	/*
	 * Returns objects change
	 * 
	 * @return change represents the diffrence in totalPrice and amountPayment
	 */
	public double getTotalChange() {
		return this.totalChange;
	}
}
