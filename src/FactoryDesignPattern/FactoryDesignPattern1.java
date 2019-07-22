package FactoryDesignPattern;

interface Shape {
    public void draw();
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("This is rectangle class");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("This is square class");
    }
}

class Circle implements Shape {
    public void draw() {
        System.out.println("This is circle class");
    }
}

class ShapeFactory {
    public Shape getShape(String getShapeType) {
        if(getShapeType == null) {
            return null;
        } else if(getShapeType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        } else if(getShapeType.equalsIgnoreCase("Square")) {
            return new Square();
        } else if(getShapeType.equalsIgnoreCase("Circle")) {
            return new Circle();
        }
        
        return null;   
    }
}

class FactoryDesignPattern1 {
	public static void main (String[] args) {
        ShapeFactory sf = new ShapeFactory();
        
        Shape rectangle = sf.getShape("rectangle");
        rectangle.draw();
        
        Shape square = sf.getShape("square");
        square.draw();
        
        Shape circle = sf.getShape("circle");
        circle.draw();
	}
}
