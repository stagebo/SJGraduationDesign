package com.sj.utils;

import java.util.ArrayList;
import java.util.TreeMap;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import com.sj.log.Log4jUtils;

public class NeuralNetwork {
	public BasicNetwork network = new BasicNetwork();
	public MLDataSet trainingSet = null;
	public boolean isSetData = false;
	public boolean isTrained = false;

	public NeuralNetwork() {
		// BasicLayer 参数： 激活函数、是否偏移、该层神经元数目
		network.addLayer(new BasicLayer(null, true, 32 * 32));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 32 * 32));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 4));
		network.getStructure().finalizeStructure();

		
		// Reset the weight matrix and the bias values.
		// This will use a Nguyen-Widrow randomizer with a range between -1 and 1.
		// If the network does not have an input, output or hidden layers, then
		// Nguyen-Widrow cannot be used and a simple range randomize between -1
		// and 1 will be used.
		network.reset();
	}

	/* 训练数据设置 */
	public void setData(TreeMap<Integer, ArrayList<double[]>> d) {
		int size = 0;
		for (int i = 0; i < 10; i++) {
			ArrayList<double[]> l = d.get(i);
			size += l.size();
		}
		double[][] input = new double[size][32*32], aim = new double[size][32*32];
		int index = 0;
		for (int i = 0; i < 10; i++) {
			ArrayList<double[]> l = d.get(i);
			for (int j = 0; j < l.size(); j++) {
				input[index] = l.get(j);
				aim[index] = getNumMap(i);
			}
		}
		if(trainingSet==null){
			Log4jUtils.sever(this, "setData", "trainingSet对象为空！");
		}
		//System.out.println(input.length+"len"+aim.length);
		trainingSet = new BasicMLDataSet(input, aim);
		isSetData = true;
	}

	public boolean train() {
		if (!isSetData)
			return false;
		final ResilientPropagation train = new ResilientPropagation(network, trainingSet);

		int epoch = 1;

		do {
			train.iteration();
			//System.out.println("Epoch #" + epoch + " Error: " + train.getError());
			Log4jUtils.info(this, "train", "训练次数  #" + epoch + " 本次训练误差: " + train.getError());
			epoch++;
		} while (train.getError() > 0.00001&&epoch<10000);
		train.finishTraining();

		isTrained = true;
		return true;
	}

	public double compute(double[] t) {
		double[] o = new double[4];
		network.compute(t, o);
		double re = getNumMap(o);
		//System.out.print(o[0]+"--"+o[1]+"--"+o[2]+"--"+o[3]);
		Log4jUtils.info(this, "compute", "本次拟合结果："+o[0]+"--"+o[1]+"--"+o[2]+"--"+o[3]);
		return re;
	}

	public int getNumMap(double[] a) {
		String s = "";
		for (int i = 0; i < a.length; i++) {
			int k=a[i]>0.5?1:0;
			s += k;
		}
		switch (s) {
		case "0000":
			return 0;
		case "0001":
			return 1;
		case "0010":
			return 2;
		case "0011":
			return 3;
		case "0100":
			return 4;
		case "0101":
			return 5;
		case "0110":
			return 6;
		case "0111":
			return 7;
		case "1000":
			return 8;
		case "1001":
			return 9;
		default:
			return -1;
		}
	}

	public double[] getNumMap(int i) {
		double[] re = new double[4];
		switch (i) {
		case 0:
			re[0] = 0;
			re[1] = 0;
			re[2] = 0;
			re[3] = 0;
			break;
		case 1:
			re[0] = 0;
			re[1] = 0;
			re[2] = 0;
			re[3] = 1;
			break;
		case 2:
			re[0] = 0;
			re[1] = 0;
			re[2] = 1;
			re[3] = 0;
			break;
		case 3:
			re[0] = 0;
			re[1] = 0;
			re[2] = 1;
			re[3] = 1;
			break;
		case 4:
			re[0] = 0;
			re[1] = 1;
			re[2] = 0;
			re[3] = 0;
			break;
		case 5:
			re[0] = 0;
			re[1] = 1;
			re[2] = 0;
			re[3] = 1;
			break;
		case 6:
			re[0] = 0;
			re[1] = 1;
			re[2] = 1;
			re[3] = 0;
			break;
		case 7:
			re[0] = 0;
			re[1] = 1;
			re[2] = 1;
			re[3] = 1;
			break;
		case 8:
			re[0] = 1;
			re[1] = 0;
			re[2] = 0;
			re[3] = 0;
			break;
		case 9:
			re[0] = 1;
			re[1] = 0;
			re[2] = 0;
			re[3] = 1;
			break;
		default:
			break;
		}
		return re;
	}
}
