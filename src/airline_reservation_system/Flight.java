package airline_reservation_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Flight {

	List<Passenger> passenger = new ArrayList<Passenger>();
	Seat seat = new Seat();
	String type;
	BufferedReader br;

	Flight(String type) {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		if (type.equalsIgnoreCase("TYPE_1")) {
			this.type = "TYPE1";
			seat.seat_initialize(0, 0, 30);
		}
		if (type.equalsIgnoreCase("TYPE_2")) {
			this.type = "TYPE2";
			seat.seat_initialize(0, 10, 20);

		}
		if (type.equalsIgnoreCase("TYPE_3")) {
			this.type = "TYPE3";
			seat.seat_initialize(10, 10, 10);
		}
	}

	public void get_available_seats() {
		seat.print_seat_availability(this.type);
	}

	public void printPassengersList() {
		System.out.println("Passengers List::");
		for (Passenger p : this.passenger) {
			System.out.println(p.toString());
		}
	}

	public void choice() {

		while (true) {
			try {
				System.out.println("1.Ticket Menu\n2.Passenger Menu\n3.Previous Menu");
				int choice = Integer.parseInt(this.br.readLine());
				switch (choice) {
				case 1:
					ticket_menu();
					break;
				case 2:
					passenger_menu();
					break;
				case 3:
					return;
				default:
					choice();
					break;

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ticket_menu() {
		while (true) {
			System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.Previous Menu");
			try {
				int choice = Integer.parseInt(this.br.readLine());
				switch (choice) {
				case 1:
					System.out.println("1.Enter Passenger Name:::");
					String name = this.br.readLine().trim();
					Passenger p = new Passenger();
					p.setName(name);
					System.out.println("Select Seat From Available List:::");
					this.get_available_seats();
					String b_seatid = this.br.readLine().trim();
					p.setSeat(b_seatid);
					p.setType(seat.book_seat(this.type,b_seatid));
					p.setPnr(this.generatePNR(b_seatid));
					this.passenger.add(p);
					this.printPassengersList();
					break;
				case 2:
					System.out.println("1.Enter PNR:::");
					String pnr = this.br.readLine().trim();
					int index = -1;
					for (int i = 0; i < this.passenger.size(); i++) {
						if (this.passenger.get(i).getPnr().equalsIgnoreCase(pnr))
							index = i;
					}
					if (index > -1) {
						seat.cancel_seat(this.passenger.get(index).getSeat());
						this.passenger.remove(index);
						System.out.println("Seat Cancelled Sucessfully!!!");
					}else{
						System.out.println("Invalid PNR NO!!!");
					}
					this.printPassengersList();
					break;
				case 3:
					return;
				default:
					choice();
					break;

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void passenger_menu() {
		while (true) {
			System.out.println("1.Add  Baggage!!!");
			try {
				int choice = Integer.parseInt(this.br.readLine());
				switch (choice) {
				case 1:
					System.out.println("1.Enter PNR:::");
					String pnr = this.br.readLine();
					for (Passenger p : this.passenger) {
						if (p.getPnr().equalsIgnoreCase(pnr)) {
							System.out.println("Enter Total Weight");
							double weight = Double.valueOf(this.br.readLine());
							double cost = p.buyExcessBaggage(weight);
							System.out.println("Total Cost for excess Baggage:: Rs."+cost);
							System.out.println("Press Y For Confirmation::");
							String confirmation = this.br.readLine();
							if(confirmation.equalsIgnoreCase("Y")){
								p.makePayment(cost);
							}else{
								System.out.println("Payment not done");
								passenger_menu();
								return;
							}
						}
					}
					System.out.println(this.passenger.toString());
					break;

				default:
					choice();
					break;

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private String generatePNR(String seatid) {
		String uuid = seatid.toUpperCase() + this.type.substring(3).toUpperCase().substring(0, 2)
				+ (int) (System.currentTimeMillis() % 10000000);
		return uuid.substring(0, 10);
	}
}
