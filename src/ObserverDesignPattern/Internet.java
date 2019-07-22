package ObserverDesignPattern;

public class Internet implements Observer {

	@Override
	public void update(float interest) {
		System.out.println("Internet: Interest Rate Updated, new Rate is :: "+interest);
	}
}
