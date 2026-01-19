package hu.progmasters.desk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Composition example – all classes in one file
 */
public class Composition {
    public static void main(String[] args) {
        Radio radio = new Radio("Rock FM");
        Computer computer = new Computer(Computer.Brand.PC);
        Photograph photo = new Photograph("Vacation", LocalDate.of(2023, 6, 15));

        Drawer drawer1 = new Drawer();
        Drawer drawer2 = new Drawer();

        drawer1.open();
        drawer1.addPaper(new Paper("exam1"));
        drawer1.addPaper(new Paper("memo12"));
        drawer1.close();

        List<Drawer> drawers = new ArrayList<>();
        drawers.add(drawer1);
        drawers.add(drawer2);

        Desk desk = new Desk(radio, computer, photo, drawers);

        desk.printStatus();
        System.out.println("Tidying up desk...");
        desk.tidyUp();
        desk.printStatus();
    }
}

class Paper {
    private String name;

    public Paper(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Radio {
    private boolean on;
    private String station;

    public Radio(String station) {
        this.station = station;
        this.on = false;
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public void changeStation(String station) {
        this.station = station;
    }

    public boolean isOn() {
        return on;
    }

    public String getStation() {
        return station;
    }
}

class Drawer {
    private boolean open;
    private List<Paper> papers = new ArrayList<>();

    public void open() {
        open = true;
    }

    public void close() {
        open = false;
    }

    public void addPaper(Paper paper) {
        if (open) {
            papers.add(paper);
        }
    }

    public void empty() {
        if (open) {
            papers.clear();
        }
    }

    public boolean isOpen() {
        return open;
    }

    public List<Paper> getPapers() {
        return papers;
    }
}

class Computer {

    enum Brand {
        MAC, PC
    }

    private Brand brand;
    private boolean on;

    public Computer(Brand brand) {
        this.brand = brand;
    }

    public void bootUp() {
        on = true;
    }

    public void shutDown() {
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public Brand getBrand() {
        return brand;
    }
}

class Photograph {
    private String title;
    private LocalDate date;

    public Photograph(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }
}

class Desk {
    private Radio radio;
    private Computer computer;
    private Photograph photograph;
    private List<Drawer> drawers;

    public Desk(Radio radio, Computer computer, Photograph photograph, List<Drawer> drawers) {
        this.radio = radio;
        this.computer = computer;
        this.photograph = photograph;
        this.drawers = drawers;
    }

    public boolean isTidy() {
        if (radio.isOn() || computer.isOn()) {
            return false;
        }
        for (Drawer drawer : drawers) {
            if (drawer.isOpen()) {
                return false;
            }
        }
        return true;
    }

    public void tidyUp() {
        radio.turnOff();
        computer.shutDown();
        for (Drawer drawer : drawers) {
            drawer.close();
        }
    }

    public void printStatus() {
        System.out.println("Desk tidy: " + isTidy());
        System.out.println("Radio: " + (radio.isOn() ? "On" : "Off")
                + ", Station: " + radio.getStation());
        System.out.println("Computer: " + computer.getBrand()
                + ", State: " + (computer.isOn() ? "On" : "Off"));
        System.out.println("Photograph: " + photograph.getTitle()
                + ", Date: " + photograph.getDate());

        for (int i = 0; i < drawers.size(); i++) {
            Drawer d = drawers.get(i);
            System.out.println("Drawer " + (i + 1) + ": "
                    + (d.isOpen() ? "Open" : "Closed")
                    + ", Papers: " + d.getPapers());
        }
        System.out.println();
    }
}
