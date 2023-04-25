//Jakub Kaminski
package zadanie4.wentylacja;

import zadanie4.UzywajacyZegara;
import zadanie4.sensory.Higrometr;
import zadanie4.sensory.SensorNiedostepny;
import zadanie4.sensory.Zegar;

import java.util.ArrayList;
import java.util.Date;

public class WilgotnoscioweSterowanieWentylacja extends SterowanieWentylacja implements UzywajacyZegara {
    class Okres {
        Date poczatek;
        Date koniec;

        public Okres(Date poczatek, Date koniec) {
            this.poczatek = poczatek;
            this.koniec = koniec;
        }

        boolean zawieraDate(Date date) {
            return (date.before(koniec)  && (date.after(poczatek) || date.equals(poczatek)));
        }
    }
    private double maksymalnaPozadanaWilgotnosc;
    private Higrometr higrometr;
    private Zegar zegar;
    private ArrayList<Okres> okresyWlaczenia = new ArrayList<>();
    boolean wilgotnoscwlaczony = false;

    public double getMaksymalnaPozadanaWilgotnosc() {
        return maksymalnaPozadanaWilgotnosc;
    }

    public void ustawMaksymalnaPozadanaWilgotnosc(double maksymalnaPozadanaWilgotnosc) {
        this.maksymalnaPozadanaWilgotnosc = maksymalnaPozadanaWilgotnosc;
    }

    public void ustawHigrometr(Higrometr higrometr) {
        this.higrometr = higrometr;
    }


    public void ustawZegar(Zegar zegar) {
        this.zegar = zegar;
    }

    public void sprawdzSensory() {
        try {

        Date czas = zegar.pobierzCzas();

            int czasWlaczenia = 0;
        float wilgotnosc = higrometr.pobierzWilgotnosc();


        if (wilgotnosc > maksymalnaPozadanaWilgotnosc) {
            if (!jakiSterownikWentylatora().jestWlaczony()) {
                wlaczNaOkreslonyCzas(czas, 60 * 15 * 1000);

            }
            else{

                wilgotnoscwlaczony = true;
                wlaczNaOkreslonyCzas(czas, 60 * 15 * 1000);
                wilgotnoscwlaczony = false;




            }
        }

        if (jakiSterownikWentylatora().jestWlaczony()) {

            Okres ostatniOkres = okresyWlaczenia.get(okresyWlaczenia.size() - 1);

            if ((!ostatniOkres.zawieraDate(czas))) {
                jakiSterownikWentylatora().ustawWlaczenie(false);

            }

        }


        else if (!jakiSterownikWentylatora().jestWlaczony()) {






                    Date wczesniej3H = new Date(czas.getTime() - 3600 * 3 * 1000+1);

                    int lastOkres = okresyWlaczenia.size() - 1;
                    while(true) {
                        if (lastOkres < 0)
                            break;
                        // [0 1][3 10][ 100  2000]
                        Okres last = okresyWlaczenia.get(lastOkres);
                        if (last.koniec.before(wczesniej3H)) {
                            //TODO usunac wszystkie okresy przed tym
                            break;
                        } else if (last.poczatek.compareTo(wczesniej3H) >= 0) {
                            czasWlaczenia += (last.koniec.getTime() - last.poczatek.getTime()) / 1000;
                            lastOkres--;
                        } else {
                            czasWlaczenia += (last.koniec.getTime() - wczesniej3H.getTime()) / 1000;
                            break;
                        }
                    }
                    if (czasWlaczenia < 3600 ) {
                        wlaczNaOkreslonyCzas(czas, 60 * 15 * 1000);
                    }

                }

        }catch (SensorNiedostepny sensorNiedostepny) {
            Date czas = zegar.pobierzCzas();
            if (!jakiSterownikWentylatora().jestWlaczony()) {
                wlaczNaOkreslonyCzas(czas, 60 * 15 * 1000);

            }
            else{

                wilgotnoscwlaczony = true;
                wlaczNaOkreslonyCzas(czas, 60 * 15 * 1000);
                wilgotnoscwlaczony = false;




            }
            System.out.println(sensorNiedostepny.getMessage());
        }
    }
// [2 3 - 4 10] // [3 4]
    protected void wlaczNaOkreslonyCzas(Date czas, int dlugoscWMilisekundach) {
        Okres nowyOkres = new Okres(czas, new Date(czas.getTime() + dlugoscWMilisekundach));
        if (jakiSterownikWentylatora().jestWlaczony()) {
            Okres aktywnyOkres = okresyWlaczenia.get(okresyWlaczenia.size() - 1);
            aktywnyOkres.koniec = new Date(Math.max(nowyOkres.koniec.getTime(),
                    aktywnyOkres.koniec.getTime()));
        }

        if(!wilgotnoscwlaczony) {
            okresyWlaczenia.add(nowyOkres);
        }
        jakiSterownikWentylatora().ustawWlaczenie(true);
    }
}
