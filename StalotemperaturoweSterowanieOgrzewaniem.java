//Jakub Kaminski
package zadanie4.ogrzewanie;

import zadanie4.sensory.SensorNiedostepny;
import zadanie4.sensory.Termometr;

public class StalotemperaturoweSterowanieOgrzewaniem extends SterowanieOgrzewaniem {
    private double docelowaTemperatura;
    private double minimalnaRoznica;
    private double wspolczynnik;

    private Termometr termometrZewnetrzny;
    private Termometr termometrWewnetrzny;

    public double pobierzDocelowaTemperatura() {
        return docelowaTemperatura;
    }

    public void ustawDocelowaTemperatura(double minimalnaTemperatura) {
        this.docelowaTemperatura = minimalnaTemperatura;
    }

    public Termometr pobierzTermometrZewnetrzny() {
        return this.termometrZewnetrzny;
    }

    public Termometr pobierzTermometrWewnetrzny() {
        return this.termometrWewnetrzny;
    }



    public void ustawTermometrZewnetrzny(Termometr termometrZewnetrzny) {
        this.termometrZewnetrzny = termometrZewnetrzny;
    }

    public void ustawTermometrWewnetrzny(Termometr termometrWewnetrzny) {
        this.termometrWewnetrzny = termometrWewnetrzny;
    }



    public double pobierzMinimalnaRoznica() {
        return this.minimalnaRoznica;
    }

    public void ustawMinimalnaRoznica(double minimalnaRoznica) {
        this.minimalnaRoznica = minimalnaRoznica;
    }


    public double pobierzWspolczynnik() {
        return this.wspolczynnik;
    }

    public void ustawWspolczynnik(double wspolczynnik) {
        this.wspolczynnik = wspolczynnik;
    }


    @Override
    public void sprawdzSensory() {
        try {

            double tempZ = pobierzTermometrZewnetrzny().pobierzTemperature();
            try{
            double tempW = pobierzTermometrWewnetrzny().pobierzTemperature();
            int minimalnaTemperatureWlaczenia = pobierzSterownikKotla().pobierzMinimalnaTemperatureWlaczenia();
            if (tempW >= docelowaTemperatura && docelowaTemperatura - tempZ < minimalnaRoznica) {
                pobierzSterownikKotla().ustawZadanaTemperature(0);
            } else if (tempW < docelowaTemperatura && docelowaTemperatura - tempZ < minimalnaRoznica) {
                float tempNaPiecu = (float) ((docelowaTemperatura - tempW) * wspolczynnik + minimalnaTemperatureWlaczenia);
                pobierzSterownikKotla().ustawZadanaTemperature(Math.round(tempNaPiecu));
            } else {
                float tempNaPiecu = (float) ((docelowaTemperatura - tempZ - minimalnaRoznica) * wspolczynnik + minimalnaTemperatureWlaczenia);
                pobierzSterownikKotla().ustawZadanaTemperature(Math.round(tempNaPiecu));
            }
            } catch (SensorNiedostepny e) {
                System.out.println(e.getMessage());
            }
        } catch (SensorNiedostepny e) {
            System.out.println(e.getMessage());
        }


    }
}
