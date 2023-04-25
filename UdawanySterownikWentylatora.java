//Jakub Kaminski
package zadanie4.efektory;

public class UdawanySterownikWentylatora implements SterownikWentylatora {
	boolean on;

	@Override
	public void ustawWlaczenie(boolean wlaczony) {

		on=wlaczony;

	}
	@Override
	public boolean jestWlaczony() {
		return on;
	}

}
