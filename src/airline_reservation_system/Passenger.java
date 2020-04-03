package airline_reservation_system;

public class Passenger {

	// Instance Variables
	private String pnr;
	public String passengerName;
	public String seat;
	public String type;
	public double weight;
	public double charges;

	@Override
	public String toString() {
		return "Passenger [pnr=" + pnr + ", passengerName=" + passengerName + ", seat=" + seat + ", type=" + type
				+ ", weight=" + weight + ", charges=" + charges + "]";
	}

	// Getters
	public String getName() {
		return passengerName;
	}

	public String getSeat() {
		return seat;
	}

	public String getPnr() {
		return pnr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	// Setters
	public void setName(String name) {
		this.passengerName = name;
	}

	public void setSeat(String seating) {
		this.seat = seating;
	}

	public double buyExcessBaggage(double weight) {
		
		this.weight = weight;
		double excess_weight=0;
		if (this.type.equalsIgnoreCase("Economy")) {
			excess_weight = weight-15;
			if(weight>0){
				if(excess_weight<=5) return excess_weight = excess_weight * 2000;
			}
		}
		if (this.type.equalsIgnoreCase("Business")) {
			excess_weight= weight-25;
			if(weight>0){
				if(excess_weight<=10) return excess_weight = excess_weight * 3000;
			}
		}
		if (this.type.equalsIgnoreCase("First")) {
			excess_weight= weight-30;
			if(weight>0){
				if(excess_weight<=10) return excess_weight = excess_weight * 4000;
			}
		}
		return 0;
	}

	public void makePayment(double cost) {
		this.charges = cost;
		System.out.println("Payment Made Sucessfully!!!");
		
	}

}// Passenger