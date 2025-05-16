package pl.kurs.zadanie01.services;

import pl.kurs.zadanie01.models.Identyfikowalny;
import pl.kurs.zadanie01.models.Lekarz;
import pl.kurs.zadanie01.models.Pacjent;
import pl.kurs.zadanie01.models.Wizyta;
import java.util.*;

public class Statystyki {

    private final Map<Integer, Integer> wizytyNaLekarza = new HashMap<>();
    private final Map<Integer, Integer> wizytyNaPacjenta = new HashMap<>();
    private final Map<String, Integer> specjalizacje = new HashMap<>();
    private final Map<String, Integer> liczbaWizytNaRok = new HashMap<>();
    private final Map<Integer, Set<Integer>> lekarzeNaPacjenta = new HashMap<>();
    private final Map<Integer, Set<Integer>> pacjenciNaLekarza = new HashMap<>();

    public Statystyki(List<Wizyta> wizyty, List<Lekarz> lekarze, List<Pacjent> pacjenci) {
        Map<String, Lekarz> unikalniLekarzePoNIP = new HashMap<>();
        for (Lekarz lekarz : lekarze) {
            String nip = lekarz.getNip();
            if (!unikalniLekarzePoNIP.containsKey(nip)) {
                unikalniLekarzePoNIP.put(nip, lekarz);
            }
        }
        Set<Integer> idLekarzy = new HashSet<>();
        for (Lekarz lekarz : unikalniLekarzePoNIP.values()) {
            idLekarzy.add(lekarz.getNumerIdentyfikacyjny());
        }
        Set<Integer> idPacjentow = new HashSet<>();
        for (Pacjent pacjent : pacjenci) {
            idPacjentow.add(pacjent.getNumerIdentyfikacyjny());
        }
        Map<Integer, String> specjalnosciLekarzy = new HashMap<>();
        for (Lekarz lekarz : unikalniLekarzePoNIP.values()) {
            specjalnosciLekarzy.put(lekarz.getNumerIdentyfikacyjny(), lekarz.getSpecjalnosc());
        }
        for (Wizyta wizyta : wizyty) {
            int idLekarza = wizyta.getNumerIdentyfikacyjnyLekarza();
            int idPacjenta = wizyta.getNumerIdentyfikacyjnyPacjenta();

            if (!idLekarzy.contains(idLekarza) || !idPacjentow.contains(idPacjenta)) {
                continue;
            }
            if (wizytyNaLekarza.containsKey(idLekarza)) {
                int obecna = wizytyNaLekarza.get(idLekarza);
                wizytyNaLekarza.put(idLekarza, obecna + 1);
            } else {
                wizytyNaLekarza.put(idLekarza, 1);
            }
            if (wizytyNaPacjenta.containsKey(idPacjenta)) {
                int obecna = wizytyNaPacjenta.get(idPacjenta);
                wizytyNaPacjenta.put(idPacjenta, obecna + 1);
            } else {
                wizytyNaPacjenta.put(idPacjenta, 1);
            }
            String rok = wizyta.getDataWizyty().substring(0, 4);
            if (liczbaWizytNaRok.containsKey(rok)) {
                int obecna = liczbaWizytNaRok.get(rok);
                liczbaWizytNaRok.put(rok, obecna + 1);
            } else {
                liczbaWizytNaRok.put(rok, 1);
            }
            String specjalizacja = specjalnosciLekarzy.get(idLekarza);
            if (specjalizacja != null) {
                if (specjalizacje.containsKey(specjalizacja)) {
                    int obecna = specjalizacje.get(specjalizacja);
                    specjalizacje.put(specjalizacja, obecna + 1);
                } else {
                    specjalizacje.put(specjalizacja, 1);
                }
            }
            if (!lekarzeNaPacjenta.containsKey(idPacjenta)) {
                lekarzeNaPacjenta.put(idPacjenta, new HashSet<>());
            }
            lekarzeNaPacjenta.get(idPacjenta).add(idLekarza);
            if (!pacjenciNaLekarza.containsKey(idLekarza)) {
                pacjenciNaLekarza.put(idLekarza, new HashSet<>());
            }
            pacjenciNaLekarza.get(idLekarza).add(idPacjenta);
        }
    }

    public <T extends Identyfikowalny> T znajdzObiektZNajwiekszaLiczbaWizyt(List<T> obiekty, Map<Integer, Integer> wizytyNaObiekt) {
        T najlepszy = null;
        int max = -1;
        for (T obiekt : obiekty) {
            int id = obiekt.getNumerIdentyfikacyjny();
            if (wizytyNaObiekt.containsKey(id)) {
                int liczba = wizytyNaObiekt.get(id);
                if (liczba > max) {
                    max = liczba;
                    najlepszy = obiekt;
                }
            }
        }
        return najlepszy;
    }

    public String znajdzNajpopularniejszaSpecjalizacje() {
        String najlepsza = null;
        int max = -1;
        for (Map.Entry<String, Integer> entry : specjalizacje.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                najlepsza = entry.getKey();
            }
        }
        return najlepsza;
    }

    public int znajdzRokZNajwiekszaLiczbaWizyt() {
        String najlepszyRok = null;
        int max = -1;
        for (Map.Entry<String, Integer> entry : liczbaWizytNaRok.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                najlepszyRok = entry.getKey();
            }
        }
        return najlepszyRok != null ? Integer.parseInt(najlepszyRok) : -1;
    }

    public List<Lekarz> znajdz5NajstarszychLekarzy(List<Lekarz> lekarze) {
        List<Lekarz> kopia = new ArrayList<>(lekarze);
        kopia.sort(Comparator.comparing(Lekarz::getDataUrodzenia));
        List<Lekarz> wynik = new ArrayList<>();
        for (int i = 0; i < 5 && i < kopia.size(); i++) {
            wynik.add(kopia.get(i));
        }
        return wynik;
    }

    public List<Lekarz> znajdz5LekarzyZNajwiekszaLiczbaWizyt(List<Lekarz> lekarze) {
        List<Lekarz> kandydaci = new ArrayList<>();
        for (Lekarz l : lekarze) {
            if (wizytyNaLekarza.containsKey(l.getNumerIdentyfikacyjny())) {
                kandydaci.add(l);
            }
        }
        kandydaci.sort((l1, l2) -> Integer.compare(wizytyNaLekarza.get(l2.getNumerIdentyfikacyjny()), wizytyNaLekarza.get(l1.getNumerIdentyfikacyjny())));
        List<Lekarz> wynik = new ArrayList<>();
        for (int i = 0; i < 5 && i < kandydaci.size(); i++) {
            wynik.add(kandydaci.get(i));
        }
        return wynik;
    }

    public List<Pacjent> znajdzPacjentowZMinimum5Lekarzami(List<Pacjent> pacjenci) {
        List<Pacjent> wynik = new ArrayList<>();
        for (Pacjent p : pacjenci) {
            Set<Integer> lekarzeSet = lekarzeNaPacjenta.get(p.getNumerIdentyfikacyjny());
            if (lekarzeSet != null && lekarzeSet.size() >= 5) {
                wynik.add(p);
            }
        }
        return wynik;
    }

    public List<Lekarz> znajdzLekarzyZJednymPacjentem(List<Lekarz> lekarze) {
        List<Lekarz> wynik = new ArrayList<>();
        for (Lekarz l : lekarze) {
            Set<Integer> pacjenciSet = pacjenciNaLekarza.get(l.getNumerIdentyfikacyjny());
            if (pacjenciSet != null && pacjenciSet.size() == 1) {
                wynik.add(l);
            }
        }
        return wynik;
    }

    public Map<Integer, Integer> getWizytyNaLekarza() {
        return wizytyNaLekarza;
    }

    public Map<Integer, Integer> getWizytyNaPacjenta() {
        return wizytyNaPacjenta;
    }
}
