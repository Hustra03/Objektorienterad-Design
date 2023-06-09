package se.kth.iv1350.erikmichel.seminar3.view;

import se.kth.iv1350.erikmichel.seminar3.model.TotalRevenueObserver;

/* Is supposed to show the total income on the user interface
     *
     */
public class TotalRevenueView implements TotalRevenueObserver {
    private double totalRevenue;

    /*
     * Creates a new observer of TotalRevenueView class with empty attributes
     * 
     */
    public TotalRevenueView() {
    }

    /*
     * Updates attribute totalRevenue and prints that value to system.out
     * 
     * @param totalRevenue this represents the current total revenue which is to be
     * printed to system.out
     */
    @Override
    public void totalRevenueUpdate(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        printTotalRevenue();
    }

    /*
     * Prints current totalRevenue attribute to system.out
     */
    private void printTotalRevenue() {
        System.out.println("Total Revenue :" + totalRevenue);
    }
}
