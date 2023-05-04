package model;

import intergration.CustomerPaymentDTO;

import java.time.LocalDateTime;

public class PaymentHandler {

	private CustomerPaymentDTO customerPayment;

	private LocalDateTime dateAndTime;

	public PaymentHandler() {
	}

	/*
	 * Updates saleInfo with information about the payment
	 * 
	 * @param amountPayment represent the size of the payment
	 * 
	 * @param saleInfo contains information about the same, such as totalPrice
	 * 
	 * @return saleInfo represents the updated information about the sale,
	 * specifically with information in customerPayment
	 */
	public SaleInfo handlePayment(int amountPayment, SaleInfo saleInfo) {
		double totalPrice = saleInfo.getTotalPriceAfterDiscount();
		updateDateAndTime();
		this.customerPayment = new CustomerPaymentDTO(amountPayment, amountPayment - totalPrice,dateAndTime);
		saleInfo.updateSaleInfoPayment(customerPayment);
		return saleInfo;
	}

	/*
	 * Updates time and date with information from system
	 * 
	 */
	private void updateDateAndTime() {

		this.dateAndTime = java.time.LocalDateTime.now();
	}

	public LocalDateTime getDateAndTime() {
		return this.dateAndTime;
	}


}
