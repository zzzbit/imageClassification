package Main;

import java.io.IOException;

import FeatureExtract.FeatureMain;
import JniInterface.JniMain;
import MySvm.TrainMain;

public class Offline {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JniMain.main(null);
//			FeatureMain.main(null);
			TrainMain.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
