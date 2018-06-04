package afal.app;

import javax.enterprise.inject.Model;

@Model
public class IndexBean {

    private double simpleInterest;

    public double getSimpleInterest() {
        return simpleInterest;
    }
    public void setSimpleInterest(double simpleInterest) {
        this.simpleInterest = simpleInterest;
    }

    private String message = "Hello  Welcome to JSF!";

    private double principle;

    public double getPrinciple() {
        return principle;
    }
    public void setPrinciple(double principle) {
        this.principle = principle;
    }
    public int getYears() {
        return years;
    }
    public void setYears(int years) {
        this.years = years;
    }
    public double getInterest() {
        return interest;
    }
    public void setInterest(double interest) {
        this.interest = interest;
    }
    private int years;
    private double interest;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String calculateSI() {
        simpleInterest = principle * interest * years;
        return "index";
    }
}
