package SingletonDesignPattern;

final class EarlySingleton {
    private static volatile EarlySingleton _instance = new EarlySingleton();
    
    private EarlySingleton() {}
    
    public static EarlySingleton getInstance() {
        return _instance;
    }
    
    public void printMessage() {
        System.out.println("This is Early Initialization of Singleton class");
    }
}

final class LazySingleton {
    private static volatile LazySingleton _instance =null;
    
    private LazySingleton() {}
    
    public static LazySingleton getInstance() {
        if(_instance == null) {
            synchronized(LazySingleton.class) {
                if(_instance == null) {
                    _instance = new LazySingleton();
                }
            }
        }
        
        return _instance;
    }
    
    public void printMessage() {
        System.out.println("This is Lazy Initialization of Singleton class");
    }
}

final class StaticBlockSingleton {
    private static final StaticBlockSingleton INSTANCE;
 
    static {
        try {
            INSTANCE = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Uffff, i was not expecting this!", e);
        }
    }
 
    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }
 
    public void printMessage() {
        System.out.println("This is Static Initialization of Singleton class");
    }
}

enum EnumSingleton {
    _instance;
	
    public void printMessage() {
        System.out.println("This is Enum Initialization of Singleton class");  
    }
}

class SingletonDemo {
	public static void main (String[] args) throws Exception {
        EarlySingleton earlySingletonObject = EarlySingleton.getInstance();
        earlySingletonObject.printMessage();
        
        LazySingleton lazySingletonObject = LazySingleton.getInstance();
        lazySingletonObject.printMessage();
        
        StaticBlockSingleton staticBlockSingletonObject = StaticBlockSingleton.getInstance();
        staticBlockSingletonObject.printMessage();
        
        EnumSingleton enumSingletonObject = EnumSingleton._instance;
        enumSingletonObject.printMessage();
	}
}
