package pl.kurs.zadanie01.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pacjent implements RejestratorWizyt, Identyfikowalny {

    private int numerIdentyfikacyjny;
    private String nazwisko;
    private String imie;
    private String pesel;
    private String dataUrodzenia;
    private List<Wizyta> wizyty = new ArrayList<>();

    public Pacjent(int numerIdentyfikacyjny, String nazwisko, String imie, String pesel, String dataUrodzenia) {
        this.numerIdentyfikacyjny = numerIdentyfikacyjny;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.dataUrodzenia = dataUrodzenia;
    }

    @Override
    public void dodajWizyte(Wizyta wizyta) {
        wizyty.add(wizyta);
    }

    public int getNumerIdentyfikacyjny() {
        return numerIdentyfikacyjny;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getPesel() {
        return pesel;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public List<Wizyta> getWizyty() {
        return wizyty;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pacjent pacjent = (Pacjent) object;
        return numerIdentyfikacyjny == pacjent.numerIdentyfikacyjny && Objects.equals(nazwisko, pacjent.nazwisko) && Objects.equals(imie, pacjent.imie) && Objects.equals(pesel, pacjent.pesel) && Objects.equals(dataUrodzenia, pacjent.dataUrodzenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerIdentyfikacyjny, nazwisko, imie, pesel, dataUrodzenia);
    }

    @Override
    public String toString() {
        return "Pacjent{" +
                "numerIdentyfikacyjny=" + numerIdentyfikacyjny +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                '}';
    }
}
