package JniInterface;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PicSegment {
	private int height;
	private int width;
	private BufferedImage sourceImg;
	private int rgb;
	private int point[][];
	private double DOOR = 245;
	private int board = 6;
	
	public boolean startSegment(String imagePath,String binaryPath) {
		int i, j;
		try {
			sourceImg = javax.imageio.ImageIO.read(new FileInputStream(
					imagePath));
		} catch (Exception e) {
			System.out.println("自动分割：打开原图失败。");
			return false;
		}
		width = sourceImg.getWidth();
		height = sourceImg.getHeight();
		BufferedImage newImh = new BufferedImage(width,height , 1);
		point = new int[width][height];
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				rgb = sourceImg.getRGB(i, j);
				double gray = (( rgb >> 16 ) & 0xff) * 0.3 + (( rgb >> 8 ) & 0xff) * 0.59 + (rgb & 0xff) * 0.11;
				if (gray > DOOR){
					point[i][j] = 1;
				}
				else {
					point[i][j] = 0;
				}
			}
		}
		for (i = 0; i < width; i++){
			for (j = 0; j < height; j++){
				if (i < board || i >= width-board||j < board||j >= height-board){
					if (point[i][j] == 1){
						newImh.setRGB(i, j, 0xFFFFFF);
					}
					else {
						newImh.setRGB(i, j, 0x0);
					}
					continue;
				}
				if (point[i][j] == 1){
					int count = 0;
					for (int k = i - board; k <= i + board; k++){
						for (int l = j - board; l <= j + board; l++){
							if (point[k][l] == 0){
								count++;
							}
						}
					}
					if (count > 70){
						newImh.setRGB(i, j, 0x0);
					}
					else {
						newImh.setRGB(i, j, 0xFFFFFF);
					}
				}
				else{
					int count = 0;
					for (int k = i - board; k <= i+ board; k++){
						for (int l = j - board; l <= j + board; l++){
							if (point[k][l] == 1){
								count++;
							}
						}
					}
					if (count > 100){
						newImh.setRGB(i, j, 0xFFFFFF);
					}
					else {
						newImh.setRGB(i, j, 0x0);
					}
				}
			}
		}
		
		try {
			ImageIO.write(newImh, "JPEG", new File(binaryPath));
		} catch (IOException e) {
			System.out.println("自动分割：写入二值图图失败。");
			return false;
		}
		return true;
	}
}
