package fr.umontpellier.iut;

import fr.umontpellier.iut.rails.Destinations;

public interface ICouleurWagon {
    static ICouleurWagon[] values() {
        return Destinations.values();
    }
    String toString();
}