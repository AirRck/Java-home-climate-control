//Jakub Kaminski
package zadanie4.wentylacja;

import zadanie4.UzywajacyZegara;
import zadanie4.czas.PoraDoby;
import zadanie4.czas.PrzelicznikCzasu;
import zadanie4.sensory.Zegar;

import java.util.ArrayList;

public class CzasoweSterowanieWentylacja extends SterowanieWentylacja implements UzywajacyZegara {
    private Zegar zegar;
    ArrayList<PoraDoby> okresyCzasowe = new ArrayList<>();
    public void ustawZegar(Zegar zegar) {
        this.zegar = zegar;
    }

    @Override
    public void sprawdzSensory() {
        int sekundaDnia = PrzelicznikCzasu.sekundaDnia(zegar.pobierzCzas());
        for (PoraDoby pd : okresyCzasowe) {
            if (pd.zawieraSie(sekundaDnia)) {
                jakiSterownikWentylatora().ustawWlaczenie(true);
                return;
            }
        }

        jakiSterownikWentylatora().ustawWlaczenie(false);
    }

    public void dodajOkresWlaczenia(PoraDoby okresWlaczenia) {
        okresyCzasowe.add(okresWlaczenia);
    }
    public PoraDoby[]	pobierzOkresyWlaczenia() {
        return (PoraDoby[]) okresyCzasowe.toArray();
    }

    public void usunOkresWlaczenia(PoraDoby okres) {
        okresyCzasowe.remove(okres);
    }
}
