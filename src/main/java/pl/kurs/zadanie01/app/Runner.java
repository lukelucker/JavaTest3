package pl.kurs.zadanie01.app;

import pl.kurs.zadanie01.models.Lekarz;
import pl.kurs.zadanie01.models.Pacjent;
import pl.kurs.zadanie01.models.Wizyta;
import pl.kurs.zadanie01.services.OdczytPlikow;
import pl.kurs.zadanie01.services.Statystyki;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        List<Lekarz> listaLekarzy = OdczytPlikow.wczytajZPliku("lekarze.txt", linia -> {
            String[] dane = linia.split(" ");
            return new Lekarz(Integer.parseInt(dane[0]), dane[1], dane[2], dane[3], dane[4], dane[5], dane[6]);
        });

        List<Pacjent> listaPacjentow = OdczytPlikow.wczytajZPliku("pacjenci.txt", linia -> {
            String[] dane = linia.split(" ");
            return new Pacjent(Integer.parseInt(dane[0]), dane[1], dane[2], dane[3], dane[4]);
        });

        List<Wizyta> listaWizyt = OdczytPlikow.wczytajZPliku("wizyty.txt", linia -> {
            String[] dane = linia.split(" ");
            return new Wizyta(Integer.parseInt(dane[0]), Integer.parseInt(dane[1]), dane[2]);
        });

        Statystyki statystyki = new Statystyki(listaWizyt, listaLekarzy, listaPacjentow);

        Lekarz lekarzZNajwiekszaLiczbaWizyt = statystyki.znajdzObiektZNajwiekszaLiczbaWizyt(listaLekarzy, statystyki.getWizytyNaLekarza());
        System.out.println(lekarzZNajwiekszaLiczbaWizyt);

        System.out.println("-----------------------");

        Pacjent pacjentZNajwiekszaLiczbaWizyt = statystyki.znajdzObiektZNajwiekszaLiczbaWizyt(listaPacjentow, statystyki.getWizytyNaPacjenta());
        System.out.println(pacjentZNajwiekszaLiczbaWizyt);

        String najpopularniejszaSpecjalizacja = statystyki.znajdzNajpopularniejszaSpecjalizacje();
        System.out.println(najpopularniejszaSpecjalizacja);

        int najczestszyRokWizyt = statystyki.znajdzRokZNajwiekszaLiczbaWizyt();
        System.out.println(najczestszyRokWizyt);

        List<Lekarz> top5NajstarszychLekarzy = statystyki.znajdz5NajstarszychLekarzy(listaLekarzy);
        top5NajstarszychLekarzy.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Lekarz> top5LekarzyZNajwiekszaLiczbaWizyt = statystyki.znajdz5LekarzyZNajwiekszaLiczbaWizyt(listaLekarzy);
        top5LekarzyZNajwiekszaLiczbaWizyt.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Pacjent> pacjenciZMin5Lekarzami = statystyki.znajdzPacjentowZMinimum5Lekarzami(listaPacjentow);
        pacjenciZMin5Lekarzami.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Lekarz> lekarzeZJednymPacjentem = statystyki.znajdzLekarzyZJednymPacjentem(listaLekarzy);
        lekarzeZJednymPacjentem.forEach(System.out::println);
    }
}
