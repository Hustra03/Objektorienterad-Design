package se.kth.iv1350.erikmichel.seminar3.model;

import se.kth.iv1350.erikmichel.seminar3.intergration.CustomerPaymentDTO;
import se.kth.iv1350.erikmichel.seminar3.intergration.DiscountDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleInfo {

	private Sale sale;

	private List<DiscountDTO> recordedDiscounts;

	private CustomerPaymentDTO customerPayment;

	private double discountTotalPrice;

	private double discountTotalVAT;

	private int customerId;

	private DiscountCalculationInterface currentDiscountInterface;

	/*
	 * Creates an SaleInfo object based upon a sale with an empty discount list and
	 * calculates discountTotalPrice and discountTotalVAT
	 * 
	 * @param Sale is the sale object this saleInfo describes and becomes the sale
	 * attribute
	 */
	public SaleInfo(Sale sale, DiscountCalculationInterface currentDiscountInterface) {
		this.sale = sale;
		List<DiscountDTO> recordedDiscounts = new ArrayList<DiscountDTO>();
		this.recordedDiscounts = recordedDiscounts;
		this.currentDiscountInterface = currentDiscountInterface;
		calculateTotalPriceAndVATAfterDiscount();
	}

	/*
	 * This method updates discountTotalPrice and discountTotalVAT for sale based on
	 * current discount
	 * 
	 */
	private void calculateTotalPriceAndVATAfterDiscount() {
		this.discountTotalPrice = this.currentDiscountInterface.calculateTotalPriceAfterDiscount(this);
		this.discountTotalVAT = this.currentDiscountInterface.calculateTotalVATAfterDiscount(this);
	}

	/*
	 * This method changes currentDiscountInterface to the parameter
	 * 
	 * @param currentDiscountInterface new DiscountCalculationInterface to be used
	 * in order to calculate discountTotalPrice and discountTotalVAT
	 */
	public void setCurrentDiscountInterface(DiscountCalculationInterface currentDiscountInterface) {
		this.currentDiscountInterface = currentDiscountInterface;
	}

	/*
	 * Replaces customerId and recordedDiscount attributes with respective
	 * parameters and recalculates discountTotalPrice and discountTotalVAT
	 * 
	 * @param recordedDiscounts represents the list of discounts which will apply to
	 * this sale
	 * 
	 * @param customerId represents the customerId of the customer this sale is made
	 * for/by/to
	 */
	public void setDiscountAndCustomerId(List<DiscountDTO> recordedDiscounts, int customerId) {
		this.customerId = customerId;
		this.recordedDiscounts = recordedDiscounts;
		calculateTotalPriceAndVATAfterDiscount();
	}

	/*
	 * Replaces customerPayment with the respective attribute
	 * 
	 * @param customerPayment is the CustomerPaymentDTO which this
	 */
	void updateSaleInfoPayment(CustomerPaymentDTO customerPayment) {
		this.customerPayment = customerPayment;
	}

	/*
	 * Returns discounted total price
	 * 
	 * @return discountTotalPrice represents the total price after every discount
	 * has been applied
	 */
	public double getTotalPriceAfterDiscount() {
		return this.discountTotalPrice;
	}

	/*
	 * Returns total Value Added Tax after discount
	 * 
	 * @return discountetTotalVAT represents the total VAT after every discount has
	 * been applied
	 */
	public double getTotalVATAfterDiscount() {
		return this.discountTotalVAT;
	}

	/*
	 * Returns the sale object this class describes
	 * 
	 * @return this.sale represents the sale this saleInfo should describe
	 */
	public Sale getSale() {
		return this.sale;
	}

	/*
	 * Returns the customerPayment included in this object and for the sale it
	 * describes
	 * 
	 * @return this.customerPayment represents the payment made by the customer
	 * along with
	 * information about it
	 */
	public CustomerPaymentDTO getCustomerPaymentDTO() {
		return this.customerPayment;
	}

	/*
	 * Returns the customerId included in this object and for the sale it
	 * describes
	 * 
	 * @return this.customerId represents the customerId for the customer this sale
	 * is for
	 */
	public int getCustomerId() {
		return this.customerId;
	}

	/*
	 * Returns the recordedDiscounts included in this object and for the sale it
	 * describes
	 * 
	 * @return this.recordedDiscounts list of applicable discounts applied to this
	 * sale
	 */
	public List<DiscountDTO> getRecordedDiscounts() {
		return this.recordedDiscounts;
	}

}
