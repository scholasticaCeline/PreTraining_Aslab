package store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Store {
	
	public static void checkFile() {
		File fp = new File("StoreDetails.txt");
		String id = null, name = null, status = null, temp;
		String [] splitting;
		int price;
		try {
			Scanner read = new Scanner(fp);
			while(read.hasNextLine()) {
				temp = read.nextLine();
				splitting = temp.split(",");
				id = splitting[0];
				name = splitting[1];
				status = splitting[2];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(name.equals("Glazed Donut")) {
			GlazeDonutStore.setCurrID(id);
			if(status.equals("Open")) GlazeDonutStore.setOpen(true);
			else GlazeDonutStore.setOpen(false);					
		} else if(name.equals("Filled Donut")) {
			FilledDonutStore.setCurrID(id);
			if(status.equals("Open")) FilledDonutStore.setOpen(true);
			else FilledDonutStore.setOpen(false);					
		} else {
			ChocolateDonutStore.setCurrID(id);
			if(status.equals("Open")) ChocolateDonutStore.setOpen(true);
			else ChocolateDonutStore.setOpen(false);					
		} 
	}

	public static void writeToFile(String currID, String string, boolean isOpen) {
		File fp = new File("StoreDetails.txt");
		try {
			FileWriter fw = new FileWriter(fp, true);
			if(isOpen) fw.write(currID + "," + string + "," + "Open\n");
			else fw.write(currID + "," + string + "," + "Close\n");
            fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
