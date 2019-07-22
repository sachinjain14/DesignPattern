//Source :: https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm

package StarategyDesignPattern;

interface Strategy {
	public int doOperation(int value1, int value2);
}

class OperationAdd implements Strategy {

	@Override
	public int doOperation(int value1, int value2) {
		// TODO Auto-generated method stub
		return value1+value2;
	}
}

class OperationSubstract implements Strategy {

	@Override
	public int doOperation(int value1, int value2) {
		// TODO Auto-generated method stub
		return value1-value2;
	}
}

class OperationMultiply implements Strategy {

	@Override
	public int doOperation(int value1, int value2) {
		// TODO Auto-generated method stub
		return value1*value2;
	}
}

class Context {
	Strategy strategy;

	Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public int executeStrategy(int num1, int num2){
		return strategy.doOperation(num1, num2);
	}
}

public class StarategyDesignPatternExample1 {
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());		
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubstract());		
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiply());		
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

	}
}
