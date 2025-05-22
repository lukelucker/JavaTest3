package pl.kurs.zadanie01.models;
import java.util.*;

public class Lekarz implements RejestratorWizyt, Identyfikowalny{

    private int numerIdentyfikacyjny;
    private String nazwisko;
    private String imie;
    private String specjalnosc;
    private String dataUrodzenia;
    private String nip;
    private String pesel;
    private List<Wizyta> wizyty = new ArrayList<>();

    public Lekarz(int numerIdentyfikacyjny, String nazwisko, String imie, String specjalnosc, String dataUrodzenia, String nip, String pesel) {
        this.numerIdentyfikacyjny = numerIdentyfikacyjny;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.specjalnosc = specjalnosc;
        this.dataUrodzenia = dataUrodzenia;
        this.nip = nip;
        this.pesel = pesel;
    }

    @Override
    public void dodajWizyte(Wizyta wizyta) {
        wizyty.add(wizyta);
    }


    public static List<Lekarz> filtrowaniePoNIP(List<Lekarz> listaLekarzy) {
        Set<String> unikalneNIPy = new HashSet<>();
        List<Lekarz> przefiltrowaniLekarze = new ArrayList<>();
        for (Lekarz lekarz : listaLekarzy) {
            if (!unikalneNIPy.contains(lekarz.getNip())) {
                unikalneNIPy.add(lekarz.getNip());
                przefiltrowaniLekarze.add(lekarz);
            }
        }
        return przefiltrowaniLekarze;
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

    public List<Wizyta> getWizyty() {
        return wizyty;
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
