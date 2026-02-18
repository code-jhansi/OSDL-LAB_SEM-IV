/*Design and implement a Java-based hotel room management application that
simulates concurrent room booking and room release operations using
multiple threads. The system must ensure data consistency when multiple
customers attempt to book or release rooms simultaneously. A hotel has a limited
number of rooms. Multiple customer threads attempt to book rooms at the same
time. If no rooms are available, the booking thread must wait.
When a room is released by another thread, the waiting booking thread must be
notified and allowed to proceed.*/



// Shared resource: Hotel
class Hotel {
    private int availableRooms;

    // Constructor to set total rooms
    public Hotel(int rooms) {
        this.availableRooms = rooms;
    }

    // Method to book room
    synchronized void bookRoom(String customer) {
        while (availableRooms == 0) { // If no rooms available
            System.out.println(customer + " waiting for room...");
            try {
                wait(); // Wait until a room is released
            } catch (InterruptedException e) {
                System.out.println(customer + " interrupted");
            }
        }

        availableRooms--; // Book room
        System.out.println(customer + " booked a room. Available rooms: " + availableRooms);
    }

    // Method to release room
    synchronized void releaseRoom(String customer) {
        availableRooms++; // Release room
        System.out.println(customer + " released a room. Available rooms: " + availableRooms);

        notifyAll(); // Notify waiting booking threads
    }
}

// Customer thread class
class Customer extends Thread {
    private Hotel hotel;
    private boolean book; // true - book, false - release

    public Customer(Hotel hotel, String name, boolean book) {
        super(name);
        this.hotel = hotel;
        this.book = book;
    }

    @Override
    public void run() {
        if (book) {
            hotel.bookRoom(getName());
        } else {
            hotel.releaseRoom(getName());
        }
    }
}

// Main class - Hotel Management Demo
public class lab4q1 {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(2); // Only 2 rooms available

        // Booking threads
        Customer c1 = new Customer(hotel, "Customer-1", true);
        Customer c2 = new Customer(hotel, "Customer-2", true);
        Customer c3 = new Customer(hotel, "Customer-3", true);

        // Release thread (simulates checkout)
        Customer c4 = new Customer(hotel, "Customer-4", false);

        c1.start();
        c2.start();
        c3.start();

        try { Thread.sleep(2000); } catch (Exception e) {}

        c4.start();
    }
}
