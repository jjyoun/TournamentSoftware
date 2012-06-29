package controller;

import model.Participant;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class ReadEntriesFromTextFile {
	private static BufferedReader inputStream;
	public static TreeMap<String, Participant> readEntriesFromTextFile(String file) throws IOException{


		TreeMap<String, Participant> mapOfParticipants = new TreeMap<String, Participant>();

		try {
			inputStream = new BufferedReader(new FileReader(file));


			String l = inputStream.readLine(); //reads the first line and does nothing; these are the column headers.
			while ((l = inputStream.readLine()) != null) {

				String[] values = l.split(",");
				for (int i=0; i < values.length; i++){
					values[i] = values[i].trim();
				}
				String lastName = values[0];
				String firstName = values[1];
				String fullName = firstName + " " + lastName; //chose this format because txt file gives partner names in this format.
				String gender = values[2]; //M = male, F = female
				String phone = values[3];
				String email = values[4];
				String club = values[5];
				String groupEntry = values[6];
				String singlesEvents = values[7];
				String doublesEvents = values[8];
				String doublesPartner = values[9];
				String mixedEvents = values[10];
				String allEvents = singlesEvents + " " + doublesEvents + " " + mixedEvents;
				String mixedPartner = values[11];
				int numEvents = (values[12].equals(""))? 0 : Integer.parseInt(values[12]);
				String tShirtSize = values[13];
				boolean purchasedTShirt = Boolean.parseBoolean(values[14]);
				int fee = Integer.parseInt(values[15]);
				boolean paid;
				String waitListedEvents;
				Participant thisParticipant = new Participant(lastName, firstName, gender, email, allEvents, doublesPartner, mixedPartner);
				/*Potential Problem: What if I have two people with the exact same first and last name?
				 * Solution1: make the keys unique (e.g. email address). Problem is, look-up time in the hash table becomes O(n) rather than
				 * O(1), as now when I access the partner I'll have to individually look up each key-value pair.
				 * Solution2: append numbers to the person's last name. e.g. Chang (2). Problem: rather difficult for the program
				 * to determine partnerings of people with same names. (e.g. p1 is partners with Kevin Chang, but there are two Kevin
				 * Changs). We can get around this by having user manually attach associations for people with identical names.
				 */

				mapOfParticipants.put(thisParticipant.toString().toLowerCase(), thisParticipant);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return mapOfParticipants;


	}
}


