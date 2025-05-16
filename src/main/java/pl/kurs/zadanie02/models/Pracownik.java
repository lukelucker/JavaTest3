package pl.kurs.zadanie02.models;

public class Pracownik extends Osoba {

    private String stanowisko;
    private double pensja;

    public Pracownik(String imie, String nazwisko, String pesel, String miasto, String stanowisko, double pensja) {
        super(imie, nazwisko, pesel, miasto);
        this.stanowisko = stanowisko;
        this.pensja = pensja;
    }

    @Override
    public String konwertowanieNaStringa() {
        return getClass().getSimpleName() + "," + super.getImie() + "," + super.getNazwisko() + "," + super.getPesel() + "," + super.getMiasto() + "," + stanowisko + "," + pensja;
    }

    @Override
    public double podajDochody() {
        return pensja;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return super.toString() +
                " ,stanowisko = " + stanowisko + '\'' +
                ", pensja = " + pensja +
                "}";
    }
}
