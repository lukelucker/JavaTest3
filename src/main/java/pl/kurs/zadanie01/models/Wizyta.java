package pl.kurs.zadanie01.models;

import pl.kurs.zadanie01.services.GeneratorObiektowPoID;

import java.util.ArrayList;
import java.util.List;

public class Wizyta extends GeneratorObiektowPoID {

    private Lekarz lekarz;
    private Pacjent pacjent;
    private String dataWizyty;

    public Wizyta(Lekarz lekarz, Pacjent pacjent, String dataWizyty) {
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.dataWizyty = dataWizyty;
    }

    public static List<Wizyta> utworzWizyty(List<String> linieZPliku, List<Lekarz> lekarze, List<Pacjent> pacjenci) {
        List<Wizyta> wizyty = new ArrayList<>();
        for (String linia : linieZPliku) {
            String[] dane = linia.trim().split("\\s+");
            if (dane.length != 3) {
                System.out.println("Nieprawidłowy format linii: " + linia);
                continue;
            }
            try {
                int idLekarza = Integer.parseInt(dane[0]);
                int idPacjenta = Integer.parseInt(dane[1]);
                String data = dane[2];
                Lekarz lekarz = GeneratorObiektowPoID.znajdzPoID(idLekarza, lekarze);
                Pacjent pacjent = GeneratorObiektowPoID.znajdzPoID(idPacjenta, pacjenci);
                if (lekarz != null && pacjent != null) {
                    Wizyta wizyta = new Wizyta(lekarz, pacjent, data);
                    wizyty.add(wizyta);
                    lekarz.dodajWizyte(wizyta);
                    pacjent.dodajWizyte(wizyta);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return wizyty;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public String getDataWizyty() {
        return dataWizyty;
    }

    @Override
    public String toString() {
        return "Wizyta{" +
                "lekarz=" + lekarz.getNazwisko() + " (" + lekarz.getSpecjalnosc() + ")" +
                ", pacjent=" + pacjent.getImie() + " " + pacjent.getNazwisko() + " (" + pacjent.getPesel() + ")" +
                ", dataWizyty='" + dataWizyty + '\'' +
                '}';
    }
}
