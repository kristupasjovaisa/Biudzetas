package domain;

import domain.models.Irasas;
import domain.models.IslaiduIrasas;
import domain.models.PajamuIrasas;

import java.util.ArrayList;
import java.util.List;

public class Biudzetas {

    private List<Irasas> irasai = new ArrayList<>();

    public List<Irasas> getIrasai() {
        return irasai;
    }

    public List<PajamuIrasas> gautiPajamuIrasus() {
        List<PajamuIrasas> pajamos = new ArrayList<>();
        for (Irasas irasas : irasai) {
            if (irasas.getKategorija().equals(PajamuIrasas.PAJAMOS)) {
                PajamuIrasas pajamuIrasas = (PajamuIrasas) irasas;
                if (pajamuIrasas != null) {
                    pajamos.add(pajamuIrasas);
                }
            }
        }
        return pajamos;
    }

    public List<IslaiduIrasas> gautiIslaiduIrasus() {
        List<IslaiduIrasas> islaidos = new ArrayList<>();
        for (Irasas irasas : irasai) {
            if (irasas.getKategorija().equals(IslaiduIrasas.ISLAIDOS)) {
                IslaiduIrasas islaiduIrasas = (IslaiduIrasas) irasas;
                if (islaiduIrasas != null) {
                    islaidos.add(islaiduIrasas);
                }
            }
        }
        return islaidos;
    }

    public double balansas() {
        double pajamuSuma = 0;
        for (PajamuIrasas pajamuIrasas : gautiPajamuIrasus()) {
            pajamuSuma += pajamuIrasas.getSuma();
        }

        for (IslaiduIrasas islaiduIrasas : gautiIslaiduIrasus()) {
            pajamuSuma -= islaiduIrasas.getSuma();
        }

        return pajamuSuma;
    }

    public void pasilintiIrasa(int numeris) {
        Irasas irasas = gautiIrasa(numeris);

        if (irasas != null) {
            irasai.remove(irasas);
        } else {
            System.out.println(String.format("Nerastas pajamu irasas pagal ivesta numeri %d", numeris));
        }
    }

    public void pridetiIrasa(Irasas irasas) {
        irasai.add(irasas);
    }

    public Irasas gautiIrasa(int numeris) {
        for (Irasas israsas : irasai) {
            if (numeris == israsas.getNumeris()) {
                return israsas;
            }
        }

        return null;
    }

    public void atnaujintiIrasa(Irasas irasas) {
        int indeksas = -1;

        for (int i = 0; i < irasai.size(); i++) {
            if (irasai.get(i).equals(irasas)) {
                indeksas = i;
            }
        }

        if (indeksas > -1) {
            irasai.set(indeksas, irasas);
        }
    }
}
