package Segment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SuperPicSegment {
	private int height;
	private int width;
	private BufferedImage sourceImg;
	private int rgb;
	private int point[][];
	private int visit[][];
	private int draw[][];
	private double gray[][];
	private double DOOR = 15;
	private int ready[][];
	private int listlength = 0;

	public SuperPicSegment(){
		
	}
	public SuperPicSegment(double door){
		DOOR = door;
	}
	private int travel(int xx, int yy, double grayValue,double door) {
		double min = grayValue - door;
		double max = grayValue + door;
		int list[][] = new int[width*height][2];
		int count = -1;
		list[++count][0] = xx;
		list[count][1] = yy;
		point[xx][yy] = 1;
		for (int i = 0; i <= count; i++){
			int x = list[i][0];
			int y = list[i][1];
			if (x - 1 >= 0){
				if (visit[x-1][y]  == 0 && gray[x-1][y] > min && gray[x-1][y] < max){
					list[++count][0] = x-1;
					list[count][1] = y;
					point[x-1][y] = 1;
				}
				visit[x-1][y] = 1;
			}
			if (x + 1 < width){
				if (visit[x+1][y]  == 0 && gray[x+1][y] > min && gray[x+1][y] < max){
					list[++count][0] = x+1;
					list[count][1] = y;
					point[x+1][y] = 1;
				}
				visit[x+1][y] = 1;
			}
			if (y - 1 >= 0){
				if (visit[x][y-1]  == 0 && gray[x][y-1] > min && gray[x][y-1] < max){
					list[++count][0] = x;
					list[count][1] = y-1;
					point[x][y-1] = 1;
				}
				visit[x][y-1] = 1;
			}
			if (y + 1 < height){
				if (visit[x][y+1]  == 0 && gray[x][y+1] > min && gray[x][y+1] < max){
					list[++count][0] = x;
					list[count][1] = y+1;
					point[x][y+1] = 1;
				}
				visit[x][y+1] = 1;
			}
		}
		return count;
	}
	
	private int maxArea(int xx, int yy) {
		int list[][] = new int[width*height][2];
		int count = -1;
		list[++count][0] = xx;
		list[count][1] = yy;
		for (int i = 0; i <= count; i++){
			int x = list[i][0];
			int y = list[i][1];
			draw[x][y] = point[x][y];
			if (x - 1 >= 0){
				if (visit[x-1][y]  == 0 && point[x-1][y] == 0){
					list[++count][0] = x-1;
					list[count][1] = y;
				}
				visit[x-1][y] = 1;
			}
			if (x + 1 < width){
				if (visit[x+1][y]  == 0 && point[x+1][y] == 0){
					list[++count][0] = x+1;
					list[count][1] = y;
				}
				visit[x+1][y] = 1;
			}
			if (y - 1 >= 0){
				if (visit[x][y-1]  == 0 && point[x][y-1] == 0){
					list[++count][0] = x;
					list[count][1] = y-1;
				}
				visit[x][y-1] = 1;
			}
			if (y + 1 < height){
				if (visit[x][y+1]  == 0 && point[x][y+1] == 0){
					list[++count][0] = x;
					list[count][1] = y+1;
				}
				visit[x][y+1] = 1;
			}
		}
		return count;
	}
	
	public void startSegment(String imagePath, String binaryPath) {
		long time1 = System.currentTimeMillis();
		int i, j;
		try {
			sourceImg = javax.imageio.ImageIO.read(new FileInputStream(
					imagePath));
		} catch (Exception e) {
			System.out.println("打开原图失败");
		}
		width = sourceImg.getWidth();
		height = sourceImg.getHeight();
		BufferedImage newImh = new BufferedImage(width, height, 1);
		point = new int[width][height];
		visit = new int[width][height];
		draw = new int[width][height];
		gray = new double[width][height];
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				rgb = sourceImg.getRGB(i, j);
				gray[i][j] = ((rgb >> 16) & 0xff) * 0.3 + ((rgb >> 8) & 0xff)
						* 0.59 + (rgb & 0xff) * 0.11;
			}
		}
		//全为前景
		for (i = 0; i < width; i++){
			for (j = 0; j < height; j++){
				point[i][j] = 0;
				draw[i][j] = 1;
				visit[i][j] = 0;
			}
		}
		
		ready = new int[5000][2]; 
		for (i = 0; i < width/3; i++){
			ready[listlength++][0] = i*3;
			ready[listlength-1][1] = 0;
			ready[listlength++][0] = i*3;
			ready[listlength-1][1] = height-1;
		}
		for (i = 1; i < height/3; i++){
			ready[listlength++][0] = 0;
			ready[listlength-1][1] = i*3;
			ready[listlength++][0] = width-1;
			ready[listlength-1][1] = i*3;
		}
//		ready[listlength++][0] = 0;
//		ready[listlength-1][1] = 0;
//		ready[listlength++][0] = width-1;
//		ready[listlength-1][1] = 0;
//		ready[listlength++][0] = 0;
//		ready[listlength-1][1] = height-1;
//		ready[listlength++][0] = width-1;
//		ready[listlength-1][1] = height-1;
		int x = 0,y = 0,flag,sum = 0;
		long time2 = System.currentTimeMillis();
		while(true){
			flag = 0;
			for (i = 0; i < listlength; i++){
				if (point[ready[i][0]][ready[i][1]] == 0){
					x = ready[i][0];
					y = ready[i][1];
					flag = 1;
					break;
				}
			}
			if (flag == 0){
				break;
			}
			for (i = 0; i < width; i++){
				for (j = 0; j < height; j++){
					visit[i][j] = point[i][j];
				}
			}
			sum += travel(x, y, gray[x][y], DOOR);
		}
		long time3 = System.currentTimeMillis();
//		toSobel();
//		toEdge();
		////////////////////////////行列扫描
//		for (int k = 0; k < height; k++){
//			for (i = 0; i < width; i++){
//				if (edge[i][k] == 1){
//					break;
//				}
//				newImh.setRGB(i, k, 0xFFFFFF);
//			}
//			for (j = width-1; j >= 0; j--){
//				if (edge[j][k] == 1){
//					break;
//				}
//				newImh.setRGB(j, k, 0xFFFFFF);
//			}
//		}
		///////////////////////////
		
		///////////////////////////只是边界
//		for (i = 0; i < width; i++){
//			for (j = 0; j <height; j++){
//				if(edge[i][j] == 1){
//					newImh.setRGB(i, j, 0x0);
//				}
//				else {
//					newImh.setRGB(i, j, 0xFFFFFF);
//				}
//			}
//		}
		////////////////////////////
		
		////////////////////////////涂抹算法
//		for (i = 0; i < width; i++){
//			for (j = 0; j < height; j++){
//				if (i < board || i >= width-board||j < board||j >= height-board){
//					if (point[i][j] == 1){
//						newImh.setRGB(i, j, 0xFFFFFF);
//					}
//					else {
//						newImh.setRGB(i, j, 0x0);
//					}
//					continue;
//				}
//				//如果是白的
//				if (point[i][j] == 1){
//					int count = 0;
//					for (int k = i - board; k <= i + board; k++){
//						for (int l = j - board; l <= j + board; l++){
//							if (point[k][l] == 0){
//								count++;
//							}
//						}
//					}
//					if (count > 70){
//						newImh.setRGB(i, j, 0x0);
//					}
//					else {
//						newImh.setRGB(i, j, 0xFFFFFF);
//					}
//				}
//				//如果是黑的
//				else{
//					int count = 0;
//					for (int k = i - board; k <= i+ board; k++){
//						for (int l = j - board; l <= j + board; l++){
//							if (point[k][l] == 1){
//								count++;
//							}
//						}
//					}
//					if (count > 100){
//						newImh.setRGB(i, j, 0xFFFFFF);
//					}
//					else {
//						newImh.setRGB(i, j, 0x0);
//					}
//				}
//			}
//		}
		///////////////////////////
		
		//////////////////////////显示结果
		int num = maxArea(width/2, height/2);
		if (num*1./(height*width) < 0.1){
			for (i = 0; i < width; i++){
				for (j = 0; j < height; j++){
					draw[i][j] = 1;
				}
			}
			num = maxArea(width/2, height/4);
			if (num*1./(height*width) < 0.1) {
				for (i = 0; i < width; i++){
					for (j = 0; j < height; j++){
						draw[i][j] = 1;
					}
				}
				num = maxArea(width/2, (height*3)/4);
				if (num*1./(height*width) < 0.1) {
					for (i = 0; i < width; i++){
						for (j = 0; j < height; j++){
							draw[i][j] = 1;
						}
					}
					num = maxArea(width/4, height/2);
					if (num*1./(height*width) < 0.1) {
						for (i = 0; i < width; i++){
							for (j = 0; j < height; j++){
								draw[i][j] = 1;
							}
						}
						num = maxArea(width*3/4, height*3/4);
					}
				}
			}
		}
		for (i = 0; i <width; i++){
			for (j = 0; j < height; j++){
				if (draw[i][j] == 1){
					newImh.setRGB(i, j, 0xFFFFFF);
				}
				else {
					newImh.setRGB(i, j, 0x0);
				}
			}
		}
		try {
			ImageIO.write(newImh, "JPEG", new File(binaryPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		long time4 = System.currentTimeMillis();
//		System.out.println("赋初值、灰度值："+(time2-time1));
//		System.out.println("对于每个点进行广搜："+(time3-time2));
//		System.out.println("最大联通分量，写入文件："+(time4-time3));
//		System.out.println(sum);
	}
	

	public static void main(String[] args) {
		new SuperPicSegment().startSegment("C:\\1.jpg", "C:\\1_b.jpg");
	}
}
