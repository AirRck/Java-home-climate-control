//Jakub Kaminski
package zadanie4.wentylacja;

import zadanie4.SprawdzajacySensory;
import zadanie4.efektory.SterownikWentylatora;

public abstract class SterowanieWentylacja implements SprawdzajacySensory {
    private SterownikWentylatora sterownikWentylatora;

    public SterowanieWentylacja() {
    }

    public SterownikWentylatora jakiSterownikWentylatora() {
        return sterownikWentylatora;
    }

    public void ustawSterownikWentylatora(SterownikWentylatora wentylator) {
        this.sterownikWentylatora = wentylator;
    }
}
