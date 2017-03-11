package com.thomas.utils;

import Jama.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hadoop on 3/11/17.
 */
public class DataReader {

    public static DataInfo getDataInfo(String dataPath) throws Exception {
        int lineCount = 0, colCount = 0;
        String line;
        File file = new File(dataPath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null) {
                if (lineCount == 0) {
                    colCount = line.split(",").length;
                }
                lineCount++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                }
            }
            return new DataInfo(lineCount, colCount);
        }
    }

    public static Matrix getTrainData(String dataFilePath, int m, int n) {
        double [][] data = new double[m][n];
        File file = new File(dataFilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                Object[] t = tempString.split(",");
                for (int i=0; i<n; i++) {
                    data[line][i] = new Double(t[i].toString());
                }
                line++;
            }
            assert (line == m);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            return new Matrix(data);
        }
    }
}
