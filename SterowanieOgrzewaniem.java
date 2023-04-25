//Jakub Kaminski
package zadanie4.ogrzewanie;

import zadanie4.SprawdzajacySensory;
import zadanie4.efektory.SterownikKotla;

public abstract class SterowanieOgrzewaniem implements SprawdzajacySensory {
    private SterownikKotla sterownikKotla;

    public SterowanieOgrzewaniem() {
    }

    public SterowanieOgrzewaniem(SterownikKotla sterownikKotla) {
        this.sterownikKotla = sterownikKotla;
    }

    public SterownikKotla pobierzSterownikKotla() {
        return sterownikKotla;
    }

    public void ustawSterownikPieca(SterownikKotla sterownikKotla) {
        this.sterownikKotla = sterownikKotla;
    }
}
