package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Reservation implements Stock {

    BufferedReader buff;
    InputStreamReader isr;
    MongoDatabase mongoDatabase;

    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);


    int selectedOperation;

    public Reservation() {
        if (isr == null) {
            isr = new InputStreamReader(System.in);
        }
        if (buff == null) {
            buff = new BufferedReader(isr);
        }

        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");  //connection created
            mongoDatabase = mongoClient.getDatabase("customers");           //database created
            mongoDatabase.createCollection("Customer_data");                //table created

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTicket() {
        String mvname = "", tkid = "";
        int bev = 0, snacks = 0;
        float tkcost = 0.0f;
        try {
            log.log(Level.INFO,"Enter the name of the movie :");
            mvname = buff.readLine();
            log.log(Level.INFO,"Enter the ID of the ticket :");
            tkid = buff.readLine();
            log.log(Level.INFO,"Enter the price of the ticket :");
            tkcost = Float.parseFloat(buff.readLine());
            log.log(Level.INFO,"Enter the number of drinks:");
            bev = Integer.parseInt(buff.readLine());
            log.log(Level.INFO,"Enter the number of snacks:");
            snacks = Integer.parseInt(buff.readLine());
        } catch (IOException e) {
            e.getStackTrace();
        }
        Ticket tk = new Ticket(mvname, tkid, tkcost, bev, snacks);

        try {
            Document document = new Document();
            document.append("movieName", tk.getMovieName());
            document.append("ticketID", tk.getTicketID());
            document.append("ticketPrice", tk.getTicketPrice());
            document.append("Beverage", tk.getBevQty());
            document.append("Snacks", tk.getSnackQty());
            mongoDatabase.getCollection("Customer_data").insertOne(document);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void deleteTicket() {
        String rm = "";
        log.log(Level.INFO,"Enter the ID of the ticket you want to delete");
        try {
            rm = buff.readLine();
        } catch (Exception e) {
            e.getStackTrace();
        }

        try {
            mongoDatabase.getCollection("Customer_data").deleteOne(eq("ticketID",rm));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addBeverage() {
        String bev = "", id = "";
        int qty = 0;
        try {
            log.log(Level.INFO,"Please enter the quantity");
            qty = Integer.parseInt(buff.readLine());
            log.log(Level.INFO,"Please enter the ticket ID");
            id = buff.readLine();
        } catch (Exception e) {
           e.getStackTrace();
        }
        log.log(Level.INFO,"Thanks for your order");
        try {
            mongoDatabase.getCollection("Customer_data").insertOne((Document) eq("beverage", id));

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addSnacks() {
        String snk = "", id = "";
        int qty = 0;
        try {
            log.log(Level.INFO,"Please enter the quantity");
            qty = Integer.parseInt(buff.readLine());
            log.log(Level.INFO,"Please enter the ticket ID");
            id = buff.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.log(Level.INFO,"Thanks for your order");
        try {
            mongoDatabase.getCollection("Customer_data").insertOne((Document) eq("snacks", id));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {

        Reservation res = new Reservation();



        String add = "", del = "", bev = "", snacks = "";

        while (true) {
            res.log.log(Level.INFO,"Welcome to Central Movie Hall.\n Please choose an operation to perform\n1. Book ticket\n2. Delete ticket\n3. Add beverage\n4. Add snacks");
            try {
                res.selectedOperation = Integer.parseInt(res.buff.readLine());
            } catch (IOException e) {
                e.getStackTrace();
            }
            switch (res.selectedOperation) {
                case 1:
                    res.addTicket();
                    break;

                case 2:
                    res.deleteTicket();
                    break;

                case 3:
                    res.addBeverage();
                    break;

                case 4:
                    res.addSnacks();
                    break;

                default:
                    break;
            }
            res.log.log(Level.INFO,"Press e to exit the application");
            String exit = "";
            try {
                exit = res.buff.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (exit.equals("e"))
                break;

        }
    }

}
