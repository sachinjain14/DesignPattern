//Source :: http://javarevisited.blogspot.in/2011/11/decorator-design-pattern-java-example.html

package DecoratorDesignPattern;

abstract class Currency {
	String description = "Unknown currency";

	public String getCurrencyDescription() {
		return description;
	}

	public abstract double cost(double value);
}

class Rupee extends Currency {
	double value;

	Rupee() {
		description = "Indian Rupees";
	}

	@Override
	public double cost(double value) {
		this.value = value;
		return value;
	}
}

class Dollar extends Currency {
	double value;

	Dollar() {
		description = "Dollar";
	}

	@Override
	public double cost(double value) {
		this.value = value;
		return value;
	}
}

abstract class Decorator extends Currency {
	abstract String getDescription();
}

class USDDecorator extends Decorator {
	Currency currency;

	USDDecorator(Currency currency) {
		this.currency = currency;
	}

	@Override
	String getDescription() {
		return currency.getCurrencyDescription()+" ,its US Dollar";
	}

	@Override
	public double cost(double value) {
		// TODO Auto-generated method stub
		return 0;
	}
}

class SGDDecorator extends Decorator{
	Currency currency;

	public SGDDecorator(Currency currency){
		this.currency = currency;
	}


	public String getDescription(){
		return currency.getCurrencyDescription()+" ,its singapore Dollar";
	}


	@Override
	public double cost(double value) {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class DecoratorDesignPatternMain {
	public static void main(String[] args) {
		// without adding decorators
		Currency curr = new Dollar();
		System.out.println(curr.getCurrencyDescription() +" dollar. "+curr.cost(2.0));

		//adding decorators
		Currency curr2 = new USDDecorator(new Dollar());
		System.out.println(curr2.getCurrencyDescription() +" US dollar. "+curr2.cost(4.0));

		Currency curr3 = new SGDDecorator(new Dollar());
		System.out.println(curr3.getCurrencyDescription() +" SGP dollar. "+curr3.cost(4.0));
	}
}
