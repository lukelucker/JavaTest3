package pl.kurs.zadanie02.services;

import pl.kurs.zadanie02.models.Osoba;
import pl.kurs.zadanie02.models.Pracownik;
import pl.kurs.zadanie02.models.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Plik {

    public static void zapiszDoPliku(Osoba[] osoby, String nazwaPliku) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nazwaPliku))) {
            for (Osoba osoba : osoby) {
                if (osoba != null) {
                    bw.write(osoba.konwertowanieNaStringa());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Osoba> wczytajZPliku(String nazwaPliku) {
        List<Osoba> osoby = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String linia = null;
            while ((linia = br.readLine()) != null) {
                if (!linia.trim().isEmpty()) {
                    Osoba osoba = Plik.przeksztalcenieStringaNaObiekt(linia);
                    if (osoba != null) {
                        osoby.add(osoba);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return osoby;
    }

    private static Osoba przeksztalcenieStringaNaObiekt(String linia) {
        String[] split = linia.split(",");
        if (split.length != 7) {
            return null;
        }
        Osoba osoba = null;
        if (split[0].equals("Student")) {
            osoba = new Student(split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]));
        } else if (split[0].equals("Pracownik")) {
            osoba = new Pracownik(split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]));
        }
        return osoba;
    }
}

