/**
 * 
 */
package airline_reservation_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author jaswanth jerripothula
 *
 */
public class Seat {
	private static final int total_rows = 30;
	private int rowstarter = 0;
	private int Economyclass_col_Capacity = 6;
	private int Businessclass_col_Capacity = 4;
	private int Firstclass_col_Capacity = 2;

	HashMap<String, Integer> indexer = new HashMap<String, Integer>();

	String[][] row_map = new String[30][];

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Seat() {
		indexer.put("A", 0);
		indexer.put("B", 1);
		indexer.put("C", 2);
		indexer.put("D", 3);
		indexer.put("E", 4);
		indexer.put("F", 5);
	}

	public static int getTotalRows() {
		return total_rows;
	}

	public String[][] getRow_map() {
		return row_map;
	}

	public void setRow_map(String[][] row_map) {
		this.row_map = row_map;
	}

	public void seat_initialize(int firstclass, int businessclass, int economyclass) {
		if (firstclass + businessclass + economyclass != this.total_rows) {
			System.out.print("Invalid Seat count");
			System.exit(0);
		}
		if (firstclass > 0) {
			while (firstclass-- != 0) {
				row_map[rowstarter] = new String[] { "A", "B" };
				rowstarter++;
			}
		}
		if (businessclass > 0) {
			while (businessclass-- != 0) {
				row_map[rowstarter] = new String[] { "A", "B", "C", "D" };
				rowstarter++;
			}
		}
		if (economyclass > 0) {
			while (economyclass-- != 0) {
				row_map[rowstarter] = new String[] { "A", "B", "C", "D", "E", "F" };
				rowstarter++;
			}
		}

	}

	public void print_seat_availability(String type) {
		if (type.equalsIgnoreCase("TYPE1")) {
			for (int i = 0; i < total_rows; i++) {
				if (i == 0)
					System.out.println("Economy Class");
				for (int j = 0; j < row_map[i].length; j++) {
					if (!row_map[i][j].equalsIgnoreCase("occupied"))
						System.out.print(row_map[i][j] + i + " ");
				}
				System.out.println();
			}
		}
		if (type.equalsIgnoreCase("TYPE2")) {
			for (int i = 0; i < total_rows; i++) {
				if (i == 0)
					System.out.println("Business Class");
				if (i == 10)
					System.out.println("Economy Class");
				for (int j = 0; j < row_map[i].length; j++) {
					if (!row_map[i][j].equalsIgnoreCase("occupied"))
						System.out.print(row_map[i][j] + i + " ");
				}
				System.out.println();
			}
		}
		if (type.equalsIgnoreCase("TYPE3")) {
			for (int i = 0; i < total_rows; i++) {
				if (i == 0)
					System.out.println("First Class");
				if (i == 10)
					System.out.println("Business Class");
				if (i == 20)
					System.out.println("Economy Class");
				for (int j = 0; j < row_map[i].length; j++) {
					if (!row_map[i][j].equalsIgnoreCase("occupied"))
						System.out.print(row_map[i][j] + i + " ");
				}
				System.out.println();
			}
		}

	}

	public String book_seat(String flighttype, String seatid) {
		int row = this.indexer.get(seatid.substring(0, 1));
		int col = Integer.parseInt(seatid.substring(1));
		if (col < total_rows && !this.row_map[col][row].equalsIgnoreCase("occupied"))
			this.row_map[col][row] = "occupied";
		if (flighttype.equalsIgnoreCase("TYPE1")) {
			return "Economy";
		}
		if (flighttype.equalsIgnoreCase("TYPE2")) {
			if(col < 10){
				return "Business";
			} else {
				return "Economy";
			}
		}
		if (flighttype.equalsIgnoreCase("TYPE3")) {
			if(col < 10){
				return "First";
			} else if(col<20){
				return "Business";
			}else{
				return "Economy";
			}
		} else {
			System.out.println("Seat Unavailable");
			System.exit(0);
		}
		return null;
	}

	public void cancel_seat(String seatid) {
		int row = this.indexer.get(seatid.substring(0, 1));
		int col = Integer.parseInt(seatid.substring(1));
		if (col < total_rows && this.row_map[col][row].equalsIgnoreCase("occupied"))
			this.row_map[col][row] = seatid.substring(0, 1);
		else {
			System.out.println("Seat available");
			System.exit(0);
		}

	}

}
