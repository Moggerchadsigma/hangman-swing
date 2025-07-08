package com.example.code;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MyFileReader {
	public static ArrayList<String> words(String fileName) throws FileNotFoundException, URISyntaxException,IOException
	{
		ArrayList<String> result = new ArrayList<String>();
		String strLine;
		FileInputStream fstream = new FileInputStream(new File(MyFileReader.class.getResource(fileName).toURI()));
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		while((strLine = br.readLine()) != null)
		{
			result.add(strLine);
		}
		fstream.close();
		return result;
	}
}
