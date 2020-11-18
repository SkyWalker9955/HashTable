import java.util.*;
import java.io.*;

class HashT {
	String[] hashTable;
	int table_length;
	int numSmashes; //don't know what is this one for, for now.
	int numSmashesLooking;
	//\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public String[] hashMe(int hash_table_length) {
		//initialize variables
		table_length = hash_table_length;
		numSmashes = 0;
		numSmashesLooking = 0;
		
		//init hashTable
		hashTable = new String[table_length]; // I believe, this one needs hashTable class and its methods 
		//filling table with -1 values
		for (int i = 0; i < table_length; i++) {
			hashTable[i] = "#"; 
		}
		
		/////Need to populate array with every nameCode/////
		File file = new File("C:\\Users\\mikel\\eclipse-workspace\\HashT_SmashT\\src\\names.txt");
		Scanner reader;
		try {
			reader = new Scanner(file);
			
			//The whole conveyor starts here
			while (reader.hasNextLine()) 
			{
				String name = reader.nextLine();
				int denominator = 90;
				//translating to nameCode here to get a name index
				int nameCode = hashFunction(name) / denominator;
				
				//collision management scheme
				if (hashTable[nameCode] == "#") {
					hashTable[nameCode] = name;
				}
				else if (hashTable[nameCode] != "#") {
					numSmashes++; //increase clash counter
					for (int p = nameCode + 1; p<table_length; p++) {
						if (hashTable[p] == "#") {
							hashTable[p] = name;
							break;
						}
						else if (hashTable[p] != "#") {
							numSmashesLooking++;
							
							continue;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return hashTable;
	}
	//\\\\\\\\\\\\\\\\\\\\\\\\\\\
	void showHashTable() {
		int j;
		System.out.println("Nuber of Clashes occured = " + numSmashes + "\n");
		System.out.println("Number of Clashes while looking for Next Available Spot = " + numSmashesLooking + "\n");
		for (j = 0; j < table_length; j++) {
			System.out.println(j + "\t" + hashTable[j]);
		}	
	}
	/////////////////////////////
	public static int hashFunction(String s) {
		String sLow = s.toLowerCase();
		int nameCode = 0;
		if (s.length() >= 3) {
			nameCode = ((sLow.charAt(0) - 'a') * 676) + ((sLow.charAt(1) - 'a') * 26) + 
					((sLow.charAt(2) - 'a') * 1);
		}
		else if (s.length() == 2) {
			nameCode = ((sLow.charAt(0) - 'a') * 676) + ((sLow.charAt(1) - 'a') * 26) + 0;
		}
		else {
			nameCode = ((sLow.charAt(0) - 'a') * 676) + 0 + 0;
		}
		return nameCode;
	}
}
public class HashData {
	////////////////////////////////////////
	public static void main(String[] args) {
		HashT h = new HashT();
		h.hashMe(200);
		h.showHashTable();
	}
}