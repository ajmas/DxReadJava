package ajmas74.dxread;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.image.BufferedImage;


public class ColorGradient implements ColorMap {
	
	int[] colorMap;
	float[] fractions;
	Color[] colors;
	int colorCount;
	
	public ColorGradient (float[] fractions, Color[] colors, int colorCount) {
		this.fractions = fractions;
		this.colors = colors;
		this.colorCount = colorCount;
		initialise();
	}
	
	void initialise() {
		int width = colorCount;
		int height = 1;
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
				
		Paint p = new LinearGradientPaint( 0, 0, width, 0, fractions, colors );
		
        g2.setPaint(p);
        g2.fillRect(0, 0, width, height);

        g2.dispose();
        
        colorMap = new int[width];
        
        for (int i=0; i<width; i++) {
        	colorMap[i] = bufferedImage.getRGB(i, 0);
        }                
	}
	
	@Override
	public int indexToRgb(int index) {
		if (index > colorCount) {
			throw new RuntimeException("Color index exceeds color count");
		}
		return colorMap[index];
	}
}