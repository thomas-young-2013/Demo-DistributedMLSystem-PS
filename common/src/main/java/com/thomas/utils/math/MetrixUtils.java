package com.thomas.utils.math;

import Jama.Matrix;

/**
 * Created by thomasyngli on 2017/2/18.
 */
public class MetrixUtils {
    public static double computeMES(Matrix m1, Matrix m2) {
        Matrix tmp = m1.minus(m2);
        return tmp.transpose().times(tmp).get(0, 0)/m1.getRowDimension();
    }
}
