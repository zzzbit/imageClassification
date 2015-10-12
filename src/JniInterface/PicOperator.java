package JniInterface;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;


import Main.Common;

public class PicOperator {
	// 实现图片的预处理，分割和提取特征
	/**
	 * 
	 * @param imagePath
	 * @param imageBinary
	 * @return 颜色、形状、纹理特征
	 */
	public double[][] picPreDeal(String imagePath, String imageBinary) {
		moduleInterface jniinterface = new moduleInterface();
//		BufferedImage sourceImg = null;
//		try {
//			sourceImg = javax.imageio.ImageIO.read(new FileInputStream(
//					imagePath));
//		} catch (Exception e) {
//			System.out.println("打开原图失败");
//		}
//		jniinterface.IIS_FirstSeg(imageBinary, imagePath, 0, 0,
//				sourceImg.getWidth() - 1, sourceImg.getHeight() - 1);
//		jniinterface.OSA_SegImg2BinImg(imagePath, imageBinary);
		new PicSegment().startSegment(imagePath, imageBinary);

		double[] Colorresult = new double[100];
		int[] Colordimension = new int[1];
		double[] Shaperesult = new double[100];
		int[] Shapedimension = new int[1];
		double[] Textureresult = new double[300];
		int[] Texturedimension = new int[1];
		double[][] result = new double[3][1];
		// 调用特征提取模块
		jniinterface.IFE_Color(imagePath, imageBinary, Colorresult,
				Colordimension);
		jniinterface.IFE_Shape(imagePath, imageBinary, Shaperesult,
				Shapedimension);
		jniinterface.IFE_Texture(imagePath, imageBinary, Textureresult,
				Texturedimension);
		result[0] = new double[Colordimension[0]];
		System.arraycopy(Colorresult, 0, result[0], 0, Colordimension[0]);
		result[1] = new double[Shapedimension[0]];
		System.arraycopy(Shaperesult, 0, result[1], 0, Shapedimension[0]);
		result[2] = new double[Texturedimension[0]];
		System.arraycopy(Textureresult, 0, result[2], 0, Texturedimension[0]);
		return result;
	}
	
	public static void main(String[] args) {
		File[] files;
		String imagePath;
		String binaryPath;
		BufferedImage sourceImg;
		moduleInterface jniinterface = new moduleInterface();
		files = new File(Common.tmpPicPath).listFiles();
		for (int i = 0; i < files.length; i++) {
			imagePath = files[i].getAbsolutePath();
			if (imagePath.charAt(imagePath.lastIndexOf('.')-1) != 'b') {
				int pos = imagePath.lastIndexOf('.');
				binaryPath = imagePath.substring(0,pos)+"_b"+imagePath.substring(pos);
				try {
					sourceImg = javax.imageio.ImageIO.read(new FileInputStream(
							imagePath));
					jniinterface.IIS_FirstSeg(binaryPath, imagePath, 0, 0,
							sourceImg.getWidth() - 1, sourceImg.getHeight() - 1);
				} catch (Exception e) {
					System.out.println("打开原图失败");
				}
			}
		}
	}
}
