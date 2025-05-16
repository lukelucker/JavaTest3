package pl.kurs.zadanie02.models;

public abstract class Osoba {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;

    public Osoba(String imie, String nazwisko, String pesel, String miasto) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miasto = miasto;
    }

    public abstract double podajDochody();

    public String getPlec() {
        char dziesiataCyfra = pesel.charAt(9);
        int cyfra = Character.getNumericValue(dziesiataCyfra);
        return (cyfra % 2 == 0) ? "Kobieta" : "Mężczyzna";
    }

    public static int policzKobiety(Osoba[] osoby) {
        int licznik = 0;
        for (Osoba o : osoby) {
            if (o.getPlec().equals("Kobieta")) {
                licznik++;
            }
        }
        return licznik;
    }

    public abstract String konwertowanieNaStringa();

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel=" + pesel +
                ", miasto='" + miasto + '\'' +
                '}';
    }
}
