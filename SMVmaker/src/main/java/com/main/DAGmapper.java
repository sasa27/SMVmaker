package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class DAGmapper {
	
	public static Set<String> mapping(File file) {
		Set<String> dependencies = new HashSet<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			line = br.readLine();
			while (!line.equals("}")){
				if (!line.contains("->") & !line.contains("fillcolor=grey") & !line.contains("digraph")) { /* it is the case if generated by COnfECt or ASSESS */
					String statename = line.substring(line.indexOf("label=") + 7, line.indexOf("]") - 1);
					dependencies.add(statename);
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("file " + file + " not found: " + e);
		} catch (IOException e){
			System.out.println("error " + e);
		}
		return dependencies;
	}
	
}