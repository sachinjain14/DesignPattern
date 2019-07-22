package ObserverDesignPattern;

import java.util.ArrayList;

public class Loan implements Subject {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private String type;
	private float interest;
	private String bank;
	
	Loan(String type, float interest, String bank) {
		this.type = type;
		this.interest = interest;
		this.bank = bank;
	}
	
	public float getInterest() {
		return interest;
	}
	
	public void setInterest(float interest) {
		this.interest = interest;
		notifyObservers();
	}
	
	public String getBank() {
		return this.bank;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(Observer ob : observers) {
			System.out.println(ob.toString()+" Notifying Observers on change in Loan interest rate");
			ob.update(this.interest);
		}
	}
}
