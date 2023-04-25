//Jakub Kaminski
package zadanie4.ogrzewanie;

import zadanie4.czas.PoraDoby;

public class NiejednoznaczaTemperatura extends Exception {
    private final PoraDoby konflikt;

    public NiejednoznaczaTemperatura() {
        this.konflikt = null;
    }

    public NiejednoznaczaTemperatura(PoraDoby konflikt, String message) {
        super(message);
        this.konflikt = konflikt;
    }

    public PoraDoby pobierzKonflikt() {
        return konflikt;
    }
}
