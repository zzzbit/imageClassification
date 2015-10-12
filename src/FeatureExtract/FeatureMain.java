package FeatureExtract;

import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Common;

public class FeatureMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startFeatureMain();
	}

	public static void startFeatureMain() {
		try {
			// BufferedImage[] imarray = new BufferedImage[10000];
			// FqImage[] imharray = new FqImage[10000];
			new File(Common.rightFeaturePath).delete();
			new File(Common.rightFeatureSumPath).delete();
			new File(Common.rightTestFeaturePath).delete();
			new File(Common.wrongFeaturePath).delete();
			new File(Common.rightTestFeatureSumPath).delete();
			new File(Common.wrongTestFeaturePath).delete();
			File[] files;
			files = new File(Common.rightPicPath).listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					BufferedImage im = ImageIO.read(files[i]);
					FqImage imh = new FqImage(im);
					printFeatureToFile(imh, Common.rightFeaturePath);
					printSumToFile(imh, Common.rightFeatureSumPath);
				}
			}
			files = new File(Common.wrongPicPath).listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					BufferedImage im = ImageIO.read(files[i]);
					FqImage imh = new FqImage(im);
					printFeatureToFile(imh, Common.wrongFeaturePath);
				}
			}
			files = new File(Common.rightTestPicPath).listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					BufferedImage im = ImageIO.read(files[i]);
					FqImage imh = new FqImage(im);
					printFeatureToFile(imh, Common.rightTestFeaturePath);
					printSumToFile(imh, Common.rightTestFeatureSumPath);
				}
			}
			files = new File(Common.wrongTestPicPath).listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					BufferedImage im = ImageIO.read(files[i]);
					FqImage imh = new FqImage(im);
					printFeatureToFile(imh, Common.wrongTestFeaturePath);
				}
			}
			// for( int i = 1 ; i <= 10 ; i++ ){
			// System.out.print( i + " " );
			// System.out.println(
			// imharray[2].cutSmallest().dColorJu(imharray[i].cutSmallest()));
			// }

			// System.out.println(imh.height); System.out.println(imh.width);
			// System.out.println();

			// printRGB(imh);
			// printAlpha(imh);
			// printRed(imh);
			// printGreen(imh);
			// printBlue(imh);

			// printGray(imh);
			// printHHSV(imh.cutSmallest());
			// printSHSV(imh);
			// printVHSV(imh);
			// System.out.println(imh.absDistance(imh2));
			// printSobel(imh);
			// printEdge(imh);
			// printEdgeToPic(imharray[3]);
			// printSmallestToPic(imh);
			// printColorJu(imh); System.out.println();
			// printColorJu(imh2); System.out.println();
			// System.out.println(imh.cutSmallest().dColorJu(imh2.cutSmallest()));
			// printHuJu(imh);
			// System.out.println( imh.dHuJu(imh2) );

			// try{
			// PrintWriter out = new PrintWriter( new BufferedWriter( new
			// FileWriter("iodemo.txt") ) );
			// out.print("abc");
			// out.close();
			// }catch(EOFException e){
			// System.err.println("end of str");
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void printGray(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("g" + imh.points[i][j].getGray());
			}
			System.out.println();
		}
	}

	public static void printRGB(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("rgb" + imh.points[i][j].getRGB());
			}
			System.out.println();
		}

	}

	public static void printHHSV(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("h" + imh.points[i][j].getHHSV());
			}
			System.out.println();
		}

	}

	public static void printSHSV(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("s" + imh.points[i][j].getSHSV());
			}
			System.out.println();
		}

	}

	public static void printVHSV(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("v" + imh.points[i][j].getVHSV());
			}
			System.out.println();
		}

	}

	public static void printAlpha(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("a" + imh.points[i][j].getAlpha() + "a");
			}
			System.out.println();
		}

	}

	public static void printRed(FqImage imh) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("r" + imh.points[i][j].getRed() + "r");
			}
			System.out.println();
		}

	}

	public static void printGreen(FqImage imh) {
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("g" + imh.points[i][j].getGreen() + "g");
			}
			System.out.println();
		}
	}

	public static void printBlue(FqImage imh) {
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("b" + imh.points[i][j].getBlue() + "b");
			}
			System.out.println();
		}
	}

	public static void printSobel(FqImage imh) {
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				System.out.print("s" + imh.sobels[i][j]);
			}
			System.out.println();
		}
	}

	public static void printEdge(FqImage imh) {
		int i, j;
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				if (imh.edge[i][j] == 1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static void printEdgeToPic(FqImage imh) {
		int i, j;
		BufferedImage newImh = new BufferedImage(imh.width, imh.height, 1);
		for (i = 0; i < imh.width; i++) {
			for (j = 0; j < imh.height; j++) {
				if (imh.edge[i][j] == 1)
					newImh.setRGB(i, j, 0xFFFFFF);
				else
					newImh.setRGB(i, j, 0x00);
			}
		}
		try {
			ImageIO.write(newImh, "JPEG", new File("images\\edge.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printSmallestToPic(FqImage imh) {
		FqImage subImh = imh.cutSmallest();
		try {
			ImageIO.write(subImh.imh, "JPEG", new File("images\\small.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static double[] getFeature(FqImage imh) {
		double[] feature = new double[7];
		int count = 0;
//		for (int i = 1; i <= 3; i++) {
//			feature[count++] = imh.colorJuH[i];
//			feature[count++] = imh.colorJuS[i];
//			feature[count++] = imh.colorJuV[i];
//		}
		for (int i = 1; i <= 7; i++) {
			feature[count++] = imh.huJu[i];
		}
		return feature;
	}

	// 将特征写入文件
	public static void printFeatureToFile(FqImage imh, String path) {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
			double[] feature = getFeature(imh);
			int i;
			for (i = 0; i < feature.length - 1; i++) {
				w.write(feature[i] + " ");
			}
			w.write(feature[i] + "\r\n");
			w.flush();
			w.close();
		} catch (Exception e) {
			System.out.println("打开文件出错");
		}
	}

	// 将和写入文件
	public static void printSumToFile(FqImage imh, String path) {
		try {
			double sum = 0;
			BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
			double[] feature = getFeature(imh);
			for (int i = 0; i < feature.length; i++) {
				sum += feature[i];
			}
			w.write(sum + "\r\n");
			w.flush();
			w.close();
		} catch (Exception e) {
			System.out.println("打开文件出错");
		}
	}

	public static void printColorJu(FqImage imh) {
		for (int i = 1; i <= 3; i++) {
			System.out.println(imh.colorJuH[i] + "," + imh.colorJuS[i] + ","
					+ imh.colorJuV[i]);
		}
	}

	public static void printHuJu(FqImage imh) {
		for (int i = 1; i <= 7; i++)
			System.out.print(imh.huJu[i] + "  ");
		System.out.println();
	}

}
