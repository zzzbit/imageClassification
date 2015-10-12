package MySvm;

import java.io.IOException;

import Main.Common;
import SvmService.svm_train;


public class TrainMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		String[] arg = { Common.rightFeaturePath+"|"+Common.wrongFeaturePath, // 存放SVM训练模型用的数据的路径
		 Common.modelPath }; // 存放SVM通过训练数据训
		 System.out.println("........SVM运行开始.........."); // 创建一个训练对象
		 svm_train.main(arg); // 调用
	}

}
