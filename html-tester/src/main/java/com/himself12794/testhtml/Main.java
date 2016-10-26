package com.himself12794.testhtml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
	
	private static final Map<String, String> ASSOCIATIONS = new HashMap<>();
	
	static {
		
		try {
			
			Document doc = Jsoup.connect("http://unstats.un.org/unsd/methods/m49/m49alpha.htm").get();

			Elements childs = doc.select("table tbody table tbody").get(3).getElementsByTag("tr");
			
			childs.listIterator(1).forEachRemaining(tr -> { 
				String country = tr.child(1).text().trim();
				String code	   = tr.child(2).text().trim();
				if (!"".equals(code) && !"".equals(country)) {
					ASSOCIATIONS.put(code, country); 
				}
			});
			
		} catch (IOException e) {
			System.err.println("Could not connect to ISO-3 website");
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {

		System.out.print("Enter a country ISO-3 code to find the name, or enter\n" + 
						   "a country name to search for its ISO-3 code.\n" +
						   "Enter no input to end: ");
		
		try (Scanner sc = new Scanner(System.in)) {
			
			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				
				if (input.length() == 0) break;
				
				input = input.length() > 3 ? input : input.toUpperCase();
				
				boolean found = false;
				
				if (ASSOCIATIONS.containsKey(input)) {
					System.out.println(input + " -> " + ASSOCIATIONS.get(input));
					found = true;
				} else if (input.length() > 3) {
					
					for (Entry<String, String> entry : ASSOCIATIONS.entrySet()) {
						if (entry.getValue().equalsIgnoreCase(input)) {
							System.out.println(input + " -> " + entry.getKey());
							found = true;
							break;
						}
					}
					
				}
				
				if (!found) System.out.println("The input " + input + " is neither a country name or code");
				
			}
			
		}
		
	}
	
	public static void sort(String word) {
		
	}
	
	public static boolean areAngrams(String word1, String word2) {
		
		if (word1.length() == word2.length()) {
			
			
			
		}
		
		return false;
	}
		
}
