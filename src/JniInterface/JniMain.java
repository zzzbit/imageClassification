package JniInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import Main.Common;


public class JniMain {

	/**
	 * @param args
	 */
	public void startJniMain() {
		try {
			String imagePath;
			String binaryPath;
			double[][] feature;
			BufferedWriter w1;
			PicOperator mypOperator = new PicOperator();
			
			new File(Common.rightFeaturePath).delete();
			new File(Common.rightTestFeaturePath).delete();
			new File(Common.wrongFeaturePath).delete();
			new File(Common.wrongTestFeaturePath).delete();
			
			File[] files;
			files = new File(Common.rightPicPath).listFiles();
			w1 = new BufferedWriter(new FileWriter(Common.rightFeaturePath));
			new File(Common.rightPicPath+"_b").mkdir();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					imagePath = files[i].getAbsolutePath();
					binaryPath = Common.rightPicPath+"_b\\"+files[i].getName();
					feature = mypOperator.picPreDeal(imagePath, binaryPath);
					for (int j = 1; j < 3; j++){
						for (int k = 0; k < feature[j].length; k++){
							w1.write(feature[j][k] + " ");
						}
					}
					w1.write("\r\n");
					w1.flush();
				}
			}
			
			w1.close();
			
			files = new File(Common.wrongPicPath).listFiles();
			w1 = new BufferedWriter(new FileWriter(Common.wrongFeaturePath));
			new File(Common.wrongPicPath+"_b").mkdir();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					imagePath = files[i].getAbsolutePath();
					binaryPath = Common.wrongPicPath+"_b\\"+files[i].getName();
					feature = mypOperator.picPreDeal(imagePath, binaryPath);
					for (int j = 1; j < 3; j++){
						for (int k = 0; k < feature[j].length; k++){
							w1.write(feature[j][k] + " ");
						}
					}
					w1.write("\r\n");
				}
			}
			w1.flush();
			w1.close();
			
			files = new File(Common.rightTestPicPath).listFiles();
			w1 = new BufferedWriter(new FileWriter(Common.rightTestFeaturePath));
			new File(Common.rightTestPicPath+"_b").mkdir();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					imagePath = files[i].getAbsolutePath();
					binaryPath = Common.rightTestPicPath+"_b\\"+files[i].getName();
					feature = mypOperator.picPreDeal(imagePath, binaryPath);
					for (int j = 1; j < 3; j++){
						for (int k = 0; k < feature[j].length; k++){
							w1.write(feature[j][k] + " ");
						}
					}
					w1.write("\r\n");
				}
			}
			w1.flush();
			w1.close();
			
			files = new File(Common.wrongTestPicPath).listFiles();
			w1 = new BufferedWriter(new FileWriter(Common.wrongTestFeaturePath));
			new File(Common.wrongTestPicPath+"_b").mkdir();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					imagePath = files[i].getAbsolutePath();
					binaryPath = Common.wrongTestPicPath+"_b\\"+files[i].getName();
					feature = mypOperator.picPreDeal(imagePath, binaryPath);
					for (int j = 1; j < 3; j++){
						for (int k = 0; k < feature[j].length; k++){
							w1.write(feature[j][k] + " ");
						}
					}
					w1.write("\r\n");
				}
			}
			w1.flush();
			w1.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new JniMain().startJniMain();
	}

}
