package pl.kurs.zadanie01.models;
import java.util.Objects;

public class Lekarz implements Identyfikowalny{

    private int numerIdentyfikacyjny;
    private String nazwisko;
    private String imie;
    private String specjalnosc;
    private String dataUrodzenia;
    private String nip;
    private String pesel;

    public Lekarz(int numerIdentyfikacyjny, String nazwisko, String imie, String specjalnosc, String dataUrodzenia, String nip, String pesel) {
        this.numerIdentyfikacyjny = numerIdentyfikacyjny;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalnosc = specjalnosc;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
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

    public String getSpecjalnosc() {
        return specjalnosc;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getNip() {
        return nip;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Lekarz lekarz = (Lekarz) object;
        return numerIdentyfikacyjny == lekarz.numerIdentyfikacyjny && Objects.equals(nazwisko, lekarz.nazwisko) && Objects.equals(imie, lekarz.imie) && Objects.equals(specjalnosc, lekarz.specjalnosc) && Objects.equals(dataUrodzenia, lekarz.dataUrodzenia) && Objects.equals(nip, lekarz.nip) && Objects.equals(pesel, lekarz.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerIdentyfikacyjny, nazwisko, imie, specjalnosc, dataUrodzenia, nip, pesel);
    }

    @Override
    public String toString() {
        return "Lekarz{" +
                "numerIdentyfikacyjny=" + numerIdentyfikacyjny +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", specjalnosc='" + specjalnosc + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                ", nip='" + nip + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}
