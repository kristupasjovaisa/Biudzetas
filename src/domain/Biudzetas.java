package domain;

import models.Irasas;
import models.IslaiduIrasas;
import models.PajamuIrasas;

import java.util.ArrayList;

public class Biudzetas {
    
    private ArrayList<Irasas> irasai = new ArrayList<Irasas>();

    public ArrayList<Irasas> getIrasai() {
        return irasai;
    }

    public ArrayList<PajamuIrasas> gautiPajamuIrasus() {
        ArrayList<PajamuIrasas> pajamos = new ArrayList<PajamuIrasas>();
        for (Irasas irasas : irasai) {
            if (irasas.getKategorija().equals("pajamos")) {
                PajamuIrasas pajamuIrasas = (PajamuIrasas) irasas;
                if (pajamuIrasas != null) {
                    pajamos.add(pajamuIrasas);
                }
            }
        }
        return pajamos;
    }

    public ArrayList<IslaiduIrasas> gautiIslaiduIrasus() {
        ArrayList<IslaiduIrasas> islaidos = new ArrayList<IslaiduIrasas>();
        for (Irasas irasas : irasai) {
            if (irasas.getKategorija().equals("islaidos")) {
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
            System.out.println("Nerastas pajamu irasas pagal ivesta numeri " + numeris);
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
