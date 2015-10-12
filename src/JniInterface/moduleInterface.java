package JniInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;


import Main.Common;
import Segment.SuperPicSegment;

public class moduleInterface {
	static {
		System.loadLibrary("jniPicFeature");
	}

	public native int IFE_Color(String imagePath, String imageBinary,
			double[] result, int[] dimension);

	public native int IFE_Shape(String imagePath, String imageBinary,
			double[] result, int[] dimension);

	public native int IFE_Texture(String imagePath, String imageBinary,
			double[] result, int[] dimension);

	public native int OSA_SegImg2BinImg(String srcImgFileName,
			String dirImgFileName);

	public native int IIS_FirstSeg(String resultAddr, String imageAddr,
			int topX, int topY, int bottomX, int bottomY);

	public static void main(String[] args) {
		File[] files;
		String rootPath = "C:\\Test\\1.1\\segment";
		String imagePath;
		String binaryPath;
		files = new File(rootPath).listFiles();
		long time1 = System.currentTimeMillis();
		// 自动分割
//		for (int i = 0; i < files.length; i++) {
//			imagePath = files[i].getAbsolutePath();
//			if (imagePath.charAt(imagePath.length() - 5) != 'b') {
//				int pos = imagePath.lastIndexOf('.');
//				binaryPath = imagePath.substring(0, pos) + "_b"
//						+ imagePath.substring(pos);
//				new moduleInterface().OSA_SegImg2BinImg(imagePath, binaryPath);
//			}
//		}
		long time2 = System.currentTimeMillis();
		// 手动分割
//		for (int i = 0; i < files.length; i++) {
//			imagePath = files[i].getAbsolutePath();
//			if (imagePath.charAt(imagePath.length() - 5) != 'b') {
//				int pos = imagePath.lastIndexOf('.');
//				binaryPath = imagePath.substring(0, pos) + "_bb"
//						+ imagePath.substring(pos);
//				BufferedImage sourceImg = null;
//				try {
//					sourceImg = javax.imageio.ImageIO.read(new FileInputStream(
//							imagePath));
//				} catch (Exception e) {
//					System.out.println("打开原图失败");
//				}
//				new moduleInterface().IIS_FirstSeg(binaryPath, imagePath, 0, 0,
//						sourceImg.getWidth() - 1, sourceImg.getHeight() - 1);
//			}
//		}
		long time3 = System.currentTimeMillis();
		// Java分割
		for (int i = 0; i < files.length; i++) {
			imagePath = files[i].getAbsolutePath();
			if (imagePath.charAt(imagePath.length() - 5) != 'b') {
				int pos = imagePath.lastIndexOf('.');
				binaryPath = imagePath.substring(0, pos) + "_bbb"
						+ imagePath.substring(pos);
				new SuperPicSegment(10).startSegment(imagePath, binaryPath);
			}
		}
		long time4 = System.currentTimeMillis();
		System.out.println(time2-time1);
		System.out.println(time3-time2);
		System.out.println(time4-time3);
	}
}
