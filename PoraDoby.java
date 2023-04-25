//Jakub Kaminski
package zadanie4.czas;

public class PoraDoby {

    private int poczatek;
    private int koniec;

    public PoraDoby() {
    }

    public PoraDoby(int poczatek, int koniec) {
        this.poczatek = poczatek;
        this.koniec = koniec;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PoraDoby poraDoby = (PoraDoby) o;
//
//        if (poczatek != poraDoby.poczatek) return false;
//        return koniec == poraDoby.koniec;
//    }
//
    public void ustawPoczatek(int poczatek) {
       this.poczatek = poczatek;
    }

    public void ustawKoniec(int koniec) {
        this.koniec = koniec;
    }

    public int pobierzPoczatek() {
        return poczatek;
    }

    public int pobierzKoniec() {
        return koniec;
    }

    public int dlugosc() {
        return this.koniec - this.poczatek;
    }

    public boolean zawieraSie(int sekundaDnia){
        if (poczatek < koniec) {
            if (sekundaDnia < koniec && sekundaDnia >= poczatek)
                return true;
            else return false;
        } else {
            if (sekundaDnia < koniec || sekundaDnia >= poczatek)
                return true;
            else return false;
        }
    }
}
