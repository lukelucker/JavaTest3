package pl.kurs.zadanie02.models;

public class Student extends Osoba {

    private String grupa;
    private double stypendium;

    public Student(String imie, String nazwisko, String pesel, String miasto, String grupa, double stypendium) {
        super(imie, nazwisko, pesel, miasto);
        this.grupa = grupa;
        this.stypendium = stypendium;
    }

    @Override
    public String konwertowanieNaStringa() {
        return getClass().getSimpleName() + "," + super.getImie() + "," + super.getNazwisko() + "," + super.getPesel() + "," + super.getMiasto() + "," + grupa + "," + stypendium;
    }

    @Override
    public double podajDochody() {
        return stypendium;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public double getStypendium() {
        return stypendium;
    }

    public void setStypendium(double stypendium) {
        this.stypendium = stypendium;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", grupa = " + grupa + '\'' +
                ", stypendium = " + stypendium +
                "}";
    }
}
