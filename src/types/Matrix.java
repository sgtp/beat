/**
 * Matrix.java
 * A list or a matrix of strings (result of select queries or of extraction of items from an ontology)
 * Copyright 2007 Andrea Splendiani
 * This software is released under LGPL
 * 
 * TODO rename ? 
 * 
 */
package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import Utils.TimeCounter;

import system.BaseBeat;

public class Matrix extends FrameworkInformationEntityImpl {
	String[][] myMatrix=null;
	
	public Matrix(String[] array) {
		super();
		myMatrix=new String[array.length][1];
		for(int i=0;i<array.length;i++) myMatrix[i][0]=array[i];
		
		isVolatile=true;
		type=FrameworkTypes.ARRAY;
	}
	public Matrix(String[][] matrix) {
		super();
		myMatrix=matrix;
		
		isVolatile=true;
		type=FrameworkTypes.MATRIX;
		
	}
	public Matrix(File matrixFile) {
		super();
		// TODO read from file
		name=matrixFile.getName();
		isVolatile=false;
		int maxWidth=0;
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(matrixFile));
			String line = inFile.readLine();
			line = inFile.readLine();
			ArrayList<String[]> tempTable=new ArrayList<String[]>();
			while(line!=null) {
				String[] tempLine=line.split("\t");
				if(maxWidth<tempLine.length) maxWidth=tempLine.length;
				tempTable.add(tempLine);
				line = inFile.readLine();
			}
			
			myMatrix=new String[tempTable.size() ][maxWidth];
			for(int i=0;i<tempTable.size();i++) {
				int j=0;
				for(;j<tempTable.get(i).length;j++) {
					myMatrix[i][j]=tempTable.get(i)[j];
				}
				for(;j<maxWidth;j++) {
					myMatrix[i][j]="";
				}
			}
		}
		catch (Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in reading file</Message>");
		}
		if(maxWidth==1) type=FrameworkTypes.ARRAY;
		else type=FrameworkTypes.MATRIX;
	}

	public void delete() {
		if(isPermanent()) {
			String myDirName=BaseBeat.getMatrixDirName();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Deleting from : "+myDirName+"</Message>");
			File fo=new File(myDirName+"/"+name);
			fo.delete();
			BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Done</Message>");
		}

	}
	
	public FrameworkInformationEntityImpl getCopyInMemory() {
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>This is a copy in memory!!!</Message>");
		TimeCounter tc=TimeCounter.getTimeCounter();
		String[][] newMatrix=new String[myMatrix.length][myMatrix[0].length];
		for (int i=0; i<myMatrix.length;i++)
			for(int j=0;j<myMatrix[0].length;j++) newMatrix[i][j]=myMatrix[i][j];
		BaseBeat.getSystemNotifier().notifyProgressMessage("<message>done. Time elapsed "+tc.getElapsedTimeMsec()+" msec</message>");
		return new Matrix(newMatrix);
	}

	public boolean makePersistent() {
		String myDirName=BaseBeat.getMatrixDirName();
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Saving to : "+myDirName+"</Message>");
		try {
			FileWriter fo=new FileWriter(new File(myDirName+"/"+name));
			for (int i=0; i<myMatrix.length;i++) {
				for(int j=0;j<myMatrix[0].length;j++)  {
					fo.write(myMatrix[i][j]);
					if(j<myMatrix[0].length-1 ) fo.write("\t");
				}
				fo.write("\n");
			}
			fo.close();
		}
		catch(Exception e) {
			BaseBeat.getSystemNotifier().notifyDebugMessage("<Message>Problems in writing file</Message>");
			return false;
		}
		this.isVolatile=false;
		BaseBeat.getSystemNotifier().notifyProgressMessage("<Message>Done</Message>");
		return true;
	}
	public String[][] getMatrixStrings() {
		return myMatrix;
	}
	
	
	public String getSpecificDescription() {
		String description=super.getSpecificDescription();
		String matrixType="";
		
		if(myMatrix[0].length==1) matrixType="Array";
		else matrixType="matrix";
		description+="<type>"+matrixType+"</type>\n";
		description+="<numberOfRows> "+myMatrix.length+"</numberOfRows><numberOfColumns>"+myMatrix[0].length+"</numberOfColumns>\n";
		return description;
		
	}
	public String getSizeString() {
		if(myMatrix!=null) {
			if(myMatrix[0]!=null) {
				return myMatrix[0].length+" x "+myMatrix.length;
			} else return "nada";
		}
		else return "nada";
	}

}
