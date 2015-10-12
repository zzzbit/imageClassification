package Main;

import FeatureExtract.FeatureMain;
import JniInterface.JniMain;
import MySvm.PredictMain;
import MySvm.TrainMain;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JniMain.main(null);
			TrainMain.main(null);
			PredictMain.main(null);

		} catch (Exception e) {
			System.out.println("³ö´í");
		}
	}
}
