package MySvm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import Main.Common;
import Main.FileCopy;
import SvmLib.svm;
import SvmLib.svm_model;
import SvmLib.svm_node;


public class PredictMain {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws IOException{
		svm_model model = svm.svm_load_model(Common.modelPath);
		int count1 = 0;
		int count2 = 0;
		int wrong = 0;
		File[] files;
		BufferedReader fp = new BufferedReader(
				new FileReader(
						Common.rightTestFeaturePath));
		new File(Common.rightTestButWrongPath).mkdir();
		files = new File(Common.rightTestPicPath).listFiles();
		while (true) {
			String line = fp.readLine();
			if (line == null)
				break;
			StringTokenizer st = new StringTokenizer(line, " \t\n\r\f:");
			int m = st.countTokens();
			svm_node[] x = new svm_node[m];
			for (int j = 0; j < m; j++) {
				x[j] = new svm_node();
				x[j].index = j + 1;
				x[j].value = Double.valueOf(st.nextToken()).doubleValue();
			}
			
			double c = svm.svm_predict(model, x);
			if ((int)c != 0){
				wrong++;
				FileCopy.copy(files[count1].getAbsolutePath(), Common.rightTestButWrongPath+"\\"+wrong+".jpg");
			}
			count1++;
		}
		fp.close();
		BufferedReader fp2 = new BufferedReader(
				new FileReader(
						Common.wrongTestFeaturePath));
		new File(Common.wrongTestButRightPicPath).mkdir();
		files = new File(Common.wrongTestPicPath).listFiles();
		while (true) {
			String line = fp2.readLine();
			if (line == null)
				break;
			StringTokenizer st = new StringTokenizer(line, " \t\n\r\f:");
			int m = st.countTokens();
			svm_node[] x = new svm_node[m];
			for (int j = 0; j < m; j++) {
				x[j] = new svm_node();
				x[j].index = j + 1;
				x[j].value = Double.valueOf(st.nextToken()).doubleValue();
			}
			
			double c = svm.svm_predict(model, x);
			if ((int)c != 1){
				wrong++;
				FileCopy.copy(files[count2].getAbsolutePath(), Common.wrongTestButRightPicPath+"\\"+wrong+".jpg");
			}
			count2++;
		}
		System.out.println("总数:"+(count1+count2)+" 出错数:"+wrong+" 正确率:"+(1-wrong*1.f/(count1+count2))*100+"%");
		fp2.close();
	}

}
