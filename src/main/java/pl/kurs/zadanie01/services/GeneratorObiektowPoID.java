package pl.kurs.zadanie01.services;

import pl.kurs.zadanie01.models.Identyfikowalny;

import java.util.List;

public class GeneratorObiektowPoID {

    public static <T extends Identyfikowalny> T znajdzPoID(int id, List<T> listaObiektow) {
        for (T obiekt : listaObiektow) {
            if (obiekt.getNumerIdentyfikacyjny() == id) {
                return obiekt;
            }
        }
        return null;
    }
}
