package pl.kurs.zadanie01.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class OdczytPlikow {

    public static <T> List<T> wczytajZPliku(String nazwaSciezki, Function<String, T> generatorObiektuZLini) {
        List<T> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nazwaSciezki)))
        {
            String linia;
            while ((linia = br.readLine()) != null) {
                T obiekt = generatorObiektuZLini.apply(linia);
                lista.add(obiekt);
            }
        }
        catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
        return lista;
    }

    public static List<String> wczytajJakoLinie(String nazwaPliku) {
        try {
            return Files.readAllLines(Paths.get(nazwaPliku));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
