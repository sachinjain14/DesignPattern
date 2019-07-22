//Source :: http://www.oodesign.com/proxy-pattern.html

package ProxyDesignPattern;

interface Image {
	public void showImage();
}

class HighResolution implements Image {
	
	public HighResolution(String imageFilePath) {
		loadImage(imageFilePath);
	}
	
	private void loadImage(String imageFilePath) {
		System.out.println("HighResolution class loadImage() function is called.");
	}
	
	@Override
	public void showImage() {
		System.out.println("HighResolution class showImage() function is called.");
	}
}

class ImageProxy implements Image {
	private String imageFilePath;
	private Image proxifiedImage;
	
	public ImageProxy(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	@Override
	public void showImage() {
		proxifiedImage = new HighResolution(imageFilePath);
		proxifiedImage.showImage();
	}
}

public class ProxyDesignPatternExample1 {
	public static void main(String[] args) {
		// assuming that the user selects a folder that has 3 images	
		//create the 3 images 	
		Image highResolutionImage1 = new ImageProxy("Path1");
		Image highResolutionImage2 = new ImageProxy("Path2");
		Image highResolutionImage3 = new ImageProxy("Path3");
		
		// assume that the user clicks on Image one item in a list
		// this would cause the program to call showImage() for that image only
		// note that in this case only image one was loaded into memory
		highResolutionImage1.showImage();
		System.out.println();
		
		// consider using the high resolution image object directly
		Image highResolutionImageNoProxy1 = new HighResolution("Path1");
		Image highResolutionImageNoProxy2 = new HighResolution("Path2");
		Image highResolutionImageBoProxy3 = new HighResolution("Path3");
		
		
		// assume that the user selects image two item from images list
		highResolutionImageNoProxy2.showImage();
		
		// note that in this case all images have been loaded into memory 
		// and not all have been actually displayed
		// this is a waste of memory resources
	}
}
