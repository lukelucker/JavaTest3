package pl.kurs.zadanie01.services;

import pl.kurs.zadanie01.models.Identyfikowalny;
import pl.kurs.zadanie01.models.Lekarz;
import pl.kurs.zadanie01.models.Pacjent;
import pl.kurs.zadanie01.models.Wizyta;
import java.util.*;
import java.util.function.Function;

public class Statystyki {

    public static <T> T obiektZNajwiekszaLiczbaWizyt(List<T> listaObiektow, Function<T, Integer> licznikWizyt) {
        T topObiekt = null;
        int max = 0;
        for (T obiekt : listaObiektow) {
            int liczba = licznikWizyt.apply(obiekt);
            if (liczba > max) {
                max = liczba;
                topObiekt = obiekt;
            }
        }
        return topObiekt;
    }

    public static String najpopularniejszaSpecjalizacja(List<Lekarz> lekarze) {
        Map<String, Integer> specjalizacje = new HashMap<>();
        for (Lekarz l : lekarze) {
            String spec = l.getSpecjalnosc();
            int liczba = l.getWizyty().size();
            specjalizacje.put(spec, specjalizacje.getOrDefault(spec, 0) + liczba);
        }
        String topSpec = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : specjalizacje.entrySet()) {
            if (entry.getValue() > max) {
                topSpec = entry.getKey();
                max = entry.getValue();
            }
        }
        return topSpec;
    }

    public static int rokZNajwiekszaLiczbaWizyt(List<Wizyta> wizyty) {
        Map<Integer, Integer> rokLicznik = new HashMap<>();
        for (Wizyta w : wizyty) {
            int rok = Integer.parseInt(w.getDataWizyty().substring(0, 4));
            if (!rokLicznik.containsKey(rok)) {
                rokLicznik.put(rok, 0);
            }
            rokLicznik.put(rok, rokLicznik.get(rok) + 1);
        }
        int najpopularniejszyRok = 0;
        int maxWizytWRoku = 0;
        for (Map.Entry<Integer, Integer> entry : rokLicznik.entrySet()) {
            if (entry.getValue() > maxWizytWRoku) {
                maxWizytWRoku = entry.getValue();
                najpopularniejszyRok = entry.getKey();
            }
        }
        return najpopularniejszyRok;
    }

    public static List<Lekarz> top5NajstarszychLekarzy(List<Lekarz> lekarze) {
        List<Lekarz> kopia = new ArrayList<>(lekarze);
        kopia.sort(Comparator.comparing(Lekarz::getDataUrodzenia));
        List<Lekarz> wynik = new ArrayList<>();
        for (int i = 0; i < 5 && i < kopia.size(); i++) {
            wynik.add(kopia.get(i));
        }
        return wynik;
    }

    public static List<Lekarz> top5LekarzyZWizytami(List<Lekarz> lekarze) {
        List<Lekarz> posortowane = new ArrayList<>(lekarze);
        posortowane.sort((l1, l2) -> l2.getWizyty().size() - l1.getWizyty().size());
        List<Lekarz> wynik = new ArrayList<>();
        for (int i = 0; i < 5 && i < posortowane.size(); i++) {
            wynik.add(posortowane.get(i));
        }
        return wynik;
    }

    public static List<Pacjent> znajdzPacjentowZMinimum5Lekarzami(List<Pacjent> pacjenci) {
        List<Pacjent> wynik = new ArrayList<>();
        for (Pacjent p : pacjenci) {
            Set<Lekarz> unikalniLekarze = new HashSet<>();
            for (Wizyta w : p.getWizyty()) {
                unikalniLekarze.add(w.getLekarz());
            }
            if (unikalniLekarze.size() >= 5) {
                wynik.add(p);
            }
        }
        return wynik;
    }

    public static List<Lekarz> lekarzeZJednymPacjentem(List<Lekarz> lekarze) {
        List<Lekarz> wynik = new ArrayList<>();
        for (Lekarz l : lekarze) {
            Set<Pacjent> pacjenciUnikalni = new HashSet<>();
            for (Wizyta w : l.getWizyty()) {
                pacjenciUnikalni.add(w.getPacjent());
            }
            if (pacjenciUnikalni.size() == 1) {
                wynik.add(l);
            }
        }
        return wynik;
    }
}
