package ObserverDesignPattern;

public class Newspaper implements Observer {

	@Override
	public void update(float interest) {
		System.out.println("Newspaper: Interest Rate Updated, new Rate is :: "+interest);
	}
}
