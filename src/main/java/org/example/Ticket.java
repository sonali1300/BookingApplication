package org.example;

public class Ticket {

    String movieName, ticketID;
    int bevQty, snackQty;

    float ticketPrice;

    public Ticket(String movieName, String ticketID, float ticketPrice, int bevQty, int snackQty)
    {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
        this.ticketID = ticketID;
        this.bevQty = bevQty;
        this.snackQty = snackQty;
         }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }



    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }


    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getBevQty() {
        return bevQty;
    }

    public void setBevQty(int bevQty) {this.bevQty = bevQty;}

    public int getSnackQty() {
        return snackQty;
    }

    public void setSnackQty(int snackQty) {
        this.snackQty = snackQty;
    }
}
