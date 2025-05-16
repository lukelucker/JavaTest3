package pl.kurs.zadanie01.models;

public class Wizyta {

    private int numerIdentyfikacyjnyLekarza;
    private int numerIdentyfikacyjnyPacjenta;
    private String dataWizyty;

    public Wizyta(int numerIdentyfikacyjnyLekarza, int numerIdentyfikacyjnyPacjenta, String dataWizyty) {
        this.numerIdentyfikacyjnyLekarza = numerIdentyfikacyjnyLekarza;
        this.numerIdentyfikacyjnyPacjenta = numerIdentyfikacyjnyPacjenta;
        this.dataWizyty = dataWizyty;
    }

    public int getNumerIdentyfikacyjnyLekarza() {
        return numerIdentyfikacyjnyLekarza;
    }

    public int getNumerIdentyfikacyjnyPacjenta() {
        return numerIdentyfikacyjnyPacjenta;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    @Override
    public String toString() {
        return "Wizyta{" +
                "numerIdentyfikacyjnyLekarza=" + numerIdentyfikacyjnyLekarza +
                ", numerIdentyfikacyjnyPacjenta=" + numerIdentyfikacyjnyPacjenta +
                ", dataWizyty='" + dataWizyty + '\'' +
                '}';
    }
}
