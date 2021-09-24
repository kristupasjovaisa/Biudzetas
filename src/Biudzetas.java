import models.IslaiduIrasas;
import models.PajamuIrasas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Biudzetas {

    private ArrayList<PajamuIrasas> pajamos = new ArrayList<PajamuIrasas>();

    public ArrayList<PajamuIrasas> getPajamos() {
        return pajamos;
    }

    private ArrayList<IslaiduIrasas> islaidos = new ArrayList<IslaiduIrasas>();

    public ArrayList<IslaiduIrasas> getIslaidos() {
        return islaidos;
    }

    public double balansas() {
        double pajamuSuma = 0;
        for (PajamuIrasas pajamuIrasas : pajamos) {
            pajamuSuma += pajamuIrasas.getSuma();
        }

        for (IslaiduIrasas islaiduIrasas : islaidos) {
            pajamuSuma -= islaiduIrasas.getSuma();
        }
        return pajamuSuma;
    }

    public void pasalintiPajamuIsrasa(int numeris) {
        // Randame pajamu israsa
        PajamuIrasas pajamuIrasas = gautiPajamuIrasa(numeris);

        // Jei surastas pajamu israsas mes ji istriname.
        if (pajamuIrasas != null) {
            pajamos.remove(pajamuIrasas);
        } else {
            System.out.println("Nerastas pajamu irasas pagal ivesta numeri " + numeris);
        }
    }

    public void pasalintiIslaiduIsrasa(int numeris) {
        IslaiduIrasas islaiduIrasas = gautiIslaiduIrasa(numeris);
        if (islaiduIrasas != null) {
            islaidos.remove(islaiduIrasas);
        } else {
            System.out.println("Nerastas islaidu irasas pagal ivesta numeri " + numeris);
        }
    }


    public void pridetiPajamuIrasa(double suma, String date, String kategorija, String pozymisArIBanka, String papildomaInfo) {
        pajamos.add(
                new PajamuIrasas(
                        suma,
                        LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        kategorija,
                        pozymisArIBanka.toLowerCase().equals("taip") ? true : false,
                        papildomaInfo
                )
        );
    }

    public void pridetiIslaiduIrasa(double suma, String data, String kategorija, String atsiskaitymoBudas, String papildomaInformacija) {
        islaidos.add(
                new IslaiduIrasas(
                        suma,
                        LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        kategorija,
                        atsiskaitymoBudas,
                        papildomaInformacija
                )
        );
    }

    private PajamuIrasas gautiPajamuIrasa(int numeris) {
        for (PajamuIrasas israsas : pajamos) {
            if (numeris == israsas.getNumeris()) {
                return israsas;
            }
        }

        return null;
    }

    private IslaiduIrasas gautiIslaiduIrasa(int numeris) {
        for (IslaiduIrasas irasas : islaidos) {
            if (numeris == irasas.getNumeris()) {
                return irasas;
            }
        }

        return null;
    }
}
