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

        listaLekarzy = Lekarz.filtrowaniePoNIP(listaLekarzy);

        List<Pacjent> listaPacjentow = OdczytPlikow.wczytajZPliku("pacjenci.txt", linia -> {
            String[] dane = linia.split(" ");
            return new Pacjent(Integer.parseInt(dane[0]), dane[1], dane[2], dane[3], dane[4]);
        });

        List<String> linieWizyt = OdczytPlikow.wczytajJakoLinie("wizyty.txt");
        List<Wizyta> listaWizyt = Wizyta.utworzWizyty(linieWizyt, listaLekarzy, listaPacjentow);

        Lekarz lekarzZNajwiekszaLiczbaWizyt = Statystyki.obiektZNajwiekszaLiczbaWizyt(listaLekarzy, lekarz -> lekarz.getWizyty().size());
        System.out.println(lekarzZNajwiekszaLiczbaWizyt);

        System.out.println("-----------------------");

        Pacjent pacjentZNajwiekszaLiczbaWizyt = Statystyki.obiektZNajwiekszaLiczbaWizyt(listaPacjentow, pacjent -> pacjent.getWizyty().size());
        System.out.println(pacjentZNajwiekszaLiczbaWizyt);

        String najpopularniejszaSpecjalizacja = Statystyki.najpopularniejszaSpecjalizacja(listaLekarzy);
        System.out.println(najpopularniejszaSpecjalizacja);

        int najpopularniejszyRok = Statystyki.rokZNajwiekszaLiczbaWizyt(listaWizyt);
        System.out.println(najpopularniejszyRok);

        List<Lekarz> top5NajstarszychLekarzy = Statystyki.top5NajstarszychLekarzy(listaLekarzy);
        top5NajstarszychLekarzy.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Lekarz> top5LekarzyZWizytami = Statystyki.top5LekarzyZWizytami(listaLekarzy);
        top5LekarzyZWizytami.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Pacjent> pacjenciZMinimum5Lekarzami = Statystyki.znajdzPacjentowZMinimum5Lekarzami(listaPacjentow);
        pacjenciZMinimum5Lekarzami.forEach(System.out::println);

        System.out.println("-----------------------");

        List<Lekarz> lekarzeZJednymPacjentem = Statystyki.lekarzeZJednymPacjentem(listaLekarzy);
        lekarzeZJednymPacjentem.forEach(System.out::println);
    }
}
