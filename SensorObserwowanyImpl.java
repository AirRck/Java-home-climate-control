//Jakub Kaminski
package zadanie4.sensory;

import zadanie4.SprawdzajacySensory;

import java.util.ArrayList;
import java.util.Queue;

public abstract class SensorObserwowanyImpl implements SensorObserwowany
{
    private ArrayList<SprawdzajacySensory> listaSprawdzajacych = new ArrayList<>();
    @Override
    public void dodajObserwartoraSensorow(SprawdzajacySensory sprawdzajacy) {
        listaSprawdzajacych.add(sprawdzajacy);
    }

    @Override
    public void wyczyscObserwatorowSensorow() {
        listaSprawdzajacych.clear();
    }

    protected void powiadom() {

        for (SprawdzajacySensory sprawdzajacy : listaSprawdzajacych) {
            sprawdzajacy.sprawdzSensory();
        }
    }
}
