package com.thomas.utils.math;

import Jama.Matrix;

/**
 * Created by thomasyngli on 2017/2/11.
 */
public class MatrixUtils {
    /*
    * @params mat, a matrix.
    * @return mat, actually a vector: mean value of every column.
    * */
    public static Matrix meanCol(Matrix mat) {
        int colNum = mat.getColumnDimension();
        int rowNum = mat.getRowDimension();
        return mat.transpose().times(new Matrix(rowNum, 1, 1.0)).times(1.0/rowNum);
    }
    /*
    * @params mat, a matrix.
    * @return, a column vector.
    * */
    public static Matrix standardDeviationCol(Matrix mat) {
        int colNum = mat.getColumnDimension();
        int rowNum = mat.getRowDimension();

        // compute mean matrix
        Matrix meanMat = new Matrix(rowNum, colNum);
        double [][] mean = MatrixUtils.meanCol(mat).getArray();
        for (int i=0; i<colNum; i++) {
            Matrix tmp = new Matrix(rowNum, 1, mean[i][0]);
            meanMat.setMatrix(0, rowNum-1, new int[]{i}, tmp);
        }

        Matrix tmpMat = mat.minus(meanMat);
        tmpMat.arrayTimesEquals(tmpMat);
        tmpMat = tmpMat.transpose().times(new Matrix(rowNum, 1, 1.0)).arrayRightDivide(new Matrix(colNum, 1, rowNum));
        // sqrt the variance.
        double [][]data = tmpMat.getArray();
        for (int i=0; i<colNum; i++) {
            data[i][0] = Math.sqrt(data[i][0]);
        }
        return new Matrix(data);
    }
    /*
    * @params mat, a matrix.
    * @return a matrix, after feature normalization.
    * */
    public static Matrix featureNormalize(Matrix mat) {
        // normalize the feature except the first one.
        int colNum = mat.getColumnDimension();
        int rowNum = mat.getRowDimension();
        Matrix mean = MatrixUtils.meanCol(mat);
        Matrix std = MatrixUtils.standardDeviationCol(mat);

        // compute mean matrix
        double [][] meanArray = mean.getArray();
        Matrix meanMat = new Matrix(rowNum, colNum);
        for (int i=0; i<colNum; i++) {
            Matrix tmp = new Matrix(rowNum, 1, meanArray[i][0]);
            meanMat.setMatrix(0, rowNum-1, new int[]{i}, tmp);
        }

        // compute std matrix
        double [][] stdArray = std.getArray();
        Matrix stdMat = new Matrix(rowNum, colNum);
        for (int i=0; i<colNum; i++) {
            double stdVal = stdArray[i][0];
            if (stdVal == 0.0) {
                stdVal = 1.0;
                meanMat.setMatrix(0, rowNum-1, new int[]{i}, new Matrix(rowNum, 1, 0.0));
            }
            Matrix tmp = new Matrix(rowNum, 1, stdVal);
            stdMat.setMatrix(0, rowNum-1, new int[]{i}, tmp);
        }
        return mat.minus(meanMat).arrayRightDivide(stdMat);
    }
}
