//Nick DeChant

//This program will ask the user to enter a text document
//that contains DNA information. The program will report
//if the nucleotides are a protein or not, the codons listed,
//an array of each nucleotide counted, and the total mass % of
//each nucleotide in a codon sequence. 

import java.util.*;
import java.io.*;


public class DNA {
	public static void main(String[] args) 
						throws FileNotFoundException{
		Scanner console = new Scanner(System.in);	
		
		Scanner input = giveIntro(console);
		System.out.print("Output file name: ");
		String outputname = console.next();
		PrintStream out = new PrintStream(new File(outputname));
		int junk = 0;
		int[] count = new int[4];
		
		process(input, count, junk, out);	
	}
	
	//Method determines whether or not the DNA 
	//sequence is a valid protein. 
	public static boolean isValidProtein(String nuc, double[] masses) {
		int len = nuc.length();
		
		String startcodon = nuc.substring(0, 3);
		String stopcodon = nuc.substring(len-3, len);
		
		if (startcodon.compareToIgnoreCase("atg") != 0){
			return false;
			}
		if (stopcodon.compareToIgnoreCase("taa") != 0 && stopcodon.compareToIgnoreCase("tag") != 0 && 
			stopcodon.compareToIgnoreCase("tga") != 0){
			return false;
			}
		int numcodons = len / 3;
		
		if (numcodons < 5){
			return false;
			}
		if (masses[1] + masses[2] < 30){
			return false;
			}

		return true;
	}
	
	//Method processes the data for a given input file
	//and sends all output to an output file. 
	public static void process(Scanner input, int[] count, int junk, PrintStream out){
		while (input.hasNextLine()){
			
			String s = input.nextLine();
			
			out.print("Region Name: " + s);
			String n = input.nextLine();
			out.println();
			out.print("Nucleotides: " + n.toUpperCase());
			for (int i = 0; i< n.length(); i++){
					char ch = n.charAt(i);
						if (ch == '-'){
							junk++;
						}else if (ch == 'A' || ch == 'a'){
						count[0]++;
						}else if (ch == 'C' || ch == 'c'){
						count[1]++;
						}else if (ch == 'G' || ch == 'g'){
						count[2]++;
						}else if (ch == 'T' || ch == 't'){
						count[3]++;
					}
				}
			
			out.println();
			out.print("Nuc. Counts: " + Arrays.toString(count));
			double countvalue = countArray(count,junk);
			double[] mass = totalMass(count,countvalue);
			out.println();
			out.println("Total mass%: " + Arrays.toString(mass) + " of " + countvalue);
			out.print("Codons List: ");
			codonsList(n,out);
			out.print("Is Protein?: " );
			if (isValidProtein(n, mass)){
				out.print( "YES" );
			}else{
				out.print( "NO" );
			}	
			out.println();
			out.println();
			count = new int[4];
			junk = 0;
			
		}
	}
	
	//Prints a list of each codon in a sequence of DNA nucleotides.
	public static void codonsList(String n, PrintStream out){
		for (int i = 0; i< n.length(); i++){
			char ch = n.charAt(i);
			if (n.charAt(i) == '-'){
				n = n.replace("-","");
			}
		}
		n = n.toUpperCase();
		
		out.print("[");
		for (int i = 0; i< n.length(); i = i+3){
			String temp;
			temp = n.substring(0 + i, 3 + i);
			out.print(temp);
			if (i < n.length()-3){
				out.print(", ");
			}	
		}
		out.println("]");
	}	
	
	////Calculates the total mass percentage of each nucleotide one
	//at a time. 
	public static double countArray(int[] count, int junk){
	
		double value;
		value = count[0]*(135.128) + count[1]*(111.103) + 
		count[2]*(151.128) + count[3]*(125.107) + junk*(100.00);
		double rounded = Math.round(value*10.0)/10.0;
		
		return rounded;
	}
	
	//Creates an array of the total mass percent of each nucleotide
	//that gets counted in each sequence. 
	public static double[] totalMass(int[] count,double countvalue){
		double[] massarray = new double[4];
			
		massarray[0] = Math.round((count[0]*(135.128)/countvalue)*1000.0)/10.0;
		massarray[1] = Math.round((count[1]*(111.103)/countvalue)*1000.0)/10.0;
		massarray[2] = Math.round((count[2]*(151.128)/countvalue)*1000.0)/10.0;
		massarray[3] = Math.round((count[3]*(125.107)/countvalue)*1000.0)/10.0;
		
		return massarray;
	}
	
	//Prompts for the intro, asks the user to enter a DNA file
	//or any other file to analyze.
	public static Scanner giveIntro(Scanner console) throws FileNotFoundException{
		System.out.println("This program reports information about DNA");
		System.out.println("nucleotide sequences that may encode proteins.");
		System.out.print("Input file name: ");
		String filename = console.next();
		File f = new File(filename);
	
		return new Scanner(f);
	}
}