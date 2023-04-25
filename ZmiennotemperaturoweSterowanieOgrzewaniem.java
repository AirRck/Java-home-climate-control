//Jakub Kaminski
package zadanie4.ogrzewanie;

import zadanie4.UzywajacyZegara;
import zadanie4.czas.PoraDoby;
import zadanie4.czas.PrzelicznikCzasu;
import zadanie4.sensory.Zegar;

import java.util.ArrayList;

public class ZmiennotemperaturoweSterowanieOgrzewaniem extends StalotemperaturoweSterowanieOgrzewaniem implements UzywajacyZegara {
    private class TemperaturaDlaPoryDoby {
        final PoraDoby poraDoby;
        final double temp;

        public TemperaturaDlaPoryDoby(PoraDoby poraDoby, double temp) {
            this.poraDoby = new PoraDoby(poraDoby.pobierzPoczatek(), poraDoby.pobierzKoniec());
            this.temp = temp;
        }
    }

    /*
        [1 - 20] -> 10
        [21 - 22] ->20
        PoraDOby -> double
     */
    private Zegar zegar;
    private ArrayList<TemperaturaDlaPoryDoby> ustawieniaTemp = new ArrayList<>();

    @Override
    public void ustawZegar(Zegar zegar) {
        this.zegar = zegar;
    }

    @Override
    public void sprawdzSensory() {
        double docelowaTemperaturaTemp = pobierzDocelowaTemperatura();
        int sekundaDnia = PrzelicznikCzasu.sekundaDnia(zegar.pobierzCzas());
        for (TemperaturaDlaPoryDoby td : ustawieniaTemp) {
            if (td.poraDoby.zawieraSie(sekundaDnia)) {
                ustawDocelowaTemperatura(td.temp);
            }
        }

        super.sprawdzSensory();
        ustawDocelowaTemperatura(docelowaTemperaturaTemp);
    }
// [2 6] [3 5]

    public void dodajOkresZezmienionaTemperatura(PoraDoby pora, double temperatura) throws NiejednoznaczaTemperatura {
        // [2, 5) + [5 10)
        // [2, 2)
        // [23, ) [1, 2]
        for (TemperaturaDlaPoryDoby td : ustawieniaTemp) {
            int ostatniaSekundTd = td.poraDoby.pobierzKoniec() - 1;
            if (ostatniaSekundTd == -1)
                ostatniaSekundTd = 24 * 3600 - 1;

            int ostatniaSekundaPora = pora.pobierzKoniec() - 1;
            if (ostatniaSekundaPora == -1)
                ostatniaSekundaPora = 24 * 3600 - 1;

            if(td.temp != temperatura) {
                if (pora.zawieraSie(td.poraDoby.pobierzPoczatek())
                        || pora.zawieraSie(ostatniaSekundTd)
                        || td.poraDoby.zawieraSie(pora.pobierzPoczatek())
                        || td.poraDoby.zawieraSie(ostatniaSekundaPora)
                ) {
                    throw new NiejednoznaczaTemperatura(td.poraDoby, "konflikt!");
                }
            }

        }
        ustawieniaTemp.add(new TemperaturaDlaPoryDoby(pora, temperatura));
    }

}
