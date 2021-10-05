package presentation;

import models.Irasas;
import models.IslaiduIrasas;
import models.PajamuIrasas;
import domain.Biudzetas;
import storage.Failas;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BiudzetoPresenteris {

    public BiudzetoPresenteris() {
        Scanner sc = new Scanner(System.in);
        Biudzetas biudzetas = new Biudzetas();
//        mockIvestiPajamasIrIslaidas(biudzetas);
//        mockSpausdintiBalansa(biudzetas);
//        mockSpausdintiPajamasIrIslaidas(biudzetas);

        ivestiPajamasIslaidas(sc, biudzetas);
        spausdintiBalansa(sc, biudzetas);
        spausdintiPajamasIrIslaidas(sc, biudzetas);
        pasalintiPajamasIrIslaidas(sc, biudzetas);
        redaguotiIrasa(sc, biudzetas);
        issaugotiDuomenis(sc, biudzetas);
        gautiDuomenis(sc);

        sc.close();
    }

    private double gautiValiduotaSuma(Scanner sc) {
        double suma = 0;
        boolean runProgram1 = true;
        while (runProgram1) {
            System.out.print("Iveskite suma");
            String sumaString = sc.next();
            try {
                double sumaDouble = Double.parseDouble(sumaString);
                suma = sumaDouble;
                runProgram1 = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Klaida! Ivesta ne skaicius!");
            }
        }
        return suma;
    }

    private LocalDate gautiValiduotaData(Scanner sc) {
        LocalDate data = LocalDate.now();
        boolean runProgram2 = true;
        while (runProgram2) {
            System.out.print("Iveskite data formatu YYYY-MM-DD");
            String dataString = sc.next();
            try {
                LocalDate localDate = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                data = localDate;
                runProgram2 = false;
            } catch (DateTimeParseException exception) {
                System.out.println("Klaida! Ivestas blogas formatas!");
            }
        }
        return data;
    }

    private void ivestiPajamasIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso ivedimui: `pajamos`, `islaidos`, `pereiti`." +
                "\nPasirinkus komanda `pajamos` galesite prideti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite prideti islaidu israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kita komanda");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ( 'pajamos', 'islaidos', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "pajamos":
                    double pajamuSuma = gautiValiduotaSuma(sc);
                    LocalDate pajamuData = gautiValiduotaData(sc);
                    System.out.println("Iveskite ar pajamos gautos i banka (Taip/Ne)");
                    String pajamuPozymisArIBanka = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String pajamuPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new PajamuIrasas(
                                    new Random().nextInt(1000000) + 1000000,
                                    pajamuSuma,
                                    pajamuData,
                                    "pajamos",
                                    arTaip(pajamuPozymisArIBanka),
                                    pajamuPapildomaInfo
                            )
                    );
                    break;

                case "islaidos":
                    double islaiduSuma = gautiValiduotaSuma(sc);
                    LocalDate islaiduData = gautiValiduotaData(sc);
                    System.out.println("Iveskite atsiskaitymo buda");
                    String atsiskaitymoBudas = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String islaiduPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new IslaiduIrasas(
                                    new Random().nextInt(1000000) + 1000000,
                                    islaiduSuma,
                                    islaiduData,
                                    "islaidos",
                                    atsiskaitymoBudas,
                                    islaiduPapildomaInfo
                            )
                    );
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    private void spausdintiBalansa(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar norite patikrinti balansa? (Taip/Ne)");

        if (arTaip(sc.next())) {
            System.out.println("Jusu balansas yra: " + biudzetas.balansas());
        }
    }

    private void spausdintiPajamasIrIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar noretumete atspausdinti pajamu ir islaidu israsa? (Taip/Ne)");
        if (arTaip(sc.next())) {
            System.out.println("Pajamu israsas: " + biudzetas.gautiPajamuIrasus());
            System.out.println("Islaidu israsas: " + biudzetas.gautiIslaiduIrasus());
        }
    }

    private void pasalintiPajamasIrIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso istrinimui: `pajamos`, `islaidos`, `pereiti`." +
                "\nPasirinkus komanda `pajamos` galesite istrinti pajamu israsa." +
                "\nPasirinkus komanda `islaidos` galesite istrinti islaidu israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kitas komandas");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ('pajamos', 'islaidos', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "pajamos":
                    System.out.println("Iveskite ID numeri");
                    int pajamuIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(pajamuIsrasoNumeris);
                    System.out.println("Jusu pajamu israsai po israso " + pajamuIsrasoNumeris + " pasalinimo " + biudzetas.gautiPajamuIrasus());
                    break;

                case "islaidos":
                    System.out.println("Iveskite ID numeri");
                    int islaiduIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(islaiduIsrasoNumeris);
                    System.out.println("Jusu islaidu israsai po israso " + islaiduIsrasoNumeris + " pasalinimo " + biudzetas.gautiIslaiduIrasus());
                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    private void redaguotiIrasa(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Pasirinkite norima komanda israso redagavimui: `redaguoti`, `pereiti`." +
                "\nPasirinkus komanda `redaguoti` galesite redaguoti israsa." +
                "\nPasirinkus komanda `pereiti` pereisite i kitas komandas");

        String komanda = "";
        while (true) {
            System.out.println("Iveskite komanda ('redaguoti', 'pereiti')");
            komanda = sc.next();
            if (komanda.equals("pereiti")) {
                break;
            }

            switch (komanda) {
                case "redaguoti":
                    for (int i = 0; i < biudzetas.getIrasai().size(); i++) {
                        System.out.println("Irasas" + (i + 1) + ": " + biudzetas.getIrasai().get(i));
                    }

                    System.out.println("Iveskite ID numeri");
                    int israsoNumeris = sc.nextInt();
                    Irasas irasas = biudzetas.gautiIrasa(israsoNumeris);
                    if (irasas != null) {
                        System.out.println("suma: " + irasas.getSuma() + ", redaguoti? (Taip/Ne)");
                        if (arTaip(sc.next())) {
                            System.out.println("Iveskite nauja suma");
                            irasas.setSuma(sc.nextDouble());
                        }
                        System.out.println("data: " + irasas.getData() + ", redaguoti? (Taip/Ne)");
                        if (arTaip(sc.next())) {
                            System.out.println("Iveskite nauja data formatu YYYY-MM-DD");
                            irasas.setData(LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }
                        System.out.println("papildoma informacija: " + irasas.getPapildomaInfo() + ", redaguoti? (Taip/Ne)");
                        if (arTaip(sc.next())) {
                            System.out.println("Iveskite nauja informacija");
                            irasas.setPapildomaInfo(sc.next());
                        }

                        if (irasas.getKategorija().equals("pajamos")) {
                            PajamuIrasas pajamuIrasas = (PajamuIrasas) irasas;
                            if (pajamuIrasas != null) {
                                System.out.println("pozymis ar i banka: " + pajamuIrasas.isPozymisArIBanka() + ", redaguoti? (Taip/Ne)");
                                if (arTaip(sc.next())) {
                                    System.out.println("Iveskite nauja pozymi");
                                    pajamuIrasas.setPozymisArIBanka(arTaip(sc.next()));
                                }
                            }
                        }

                        if (irasas.getKategorija().equals("islaidos")) {
                            IslaiduIrasas islaiduIrasas = (IslaiduIrasas) irasas;
                            if (islaiduIrasas != null) {
                                System.out.println("Atsiskaitymo budas: " + islaiduIrasas.getAtsiskaitymoBudas() + ", redaguoti? (Taip/Ne)");
                                if (arTaip(sc.next())) {
                                    System.out.println("Iveskite nauja atsiskaitymo buda");
                                    islaiduIrasas.setAtsiskaitymoBudas(sc.next());
                                }
                            }
                        }

                        System.out.println("Jusu israsas po redagavimo: " + irasas);
                    } else {
                        System.out.println("Blogai ivestas numeris");
                    }

                    break;

                default:
                    System.out.println("Nezinoma komanda: " + komanda);
            }
        }
    }

    private void issaugotiDuomenis(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar norite issaugoti duomenys? (Taip/Ne)");
        if (arTaip(sc.next())) {
            try {
                Failas failas = new Failas();
                failas.issaugotiDuomenis(biudzetas.getIrasai());
                System.out.println("Jusu duomenys issaugoti");
            } catch (IOException e) {
                System.out.println("Nepavyko issaugoti duomenu " + e);
            }
        }
    }

    private void gautiDuomenis(Scanner sc) {
        System.out.println("Ar norite gauti duomenys? (Taip/Ne)");
        if (arTaip(sc.next())) {
            Failas failas = new Failas();
            ArrayList<Irasas> duomenys = failas.gautiDuomenis();
            if (duomenys.size() > 0) {
                System.out.println("Gauti duomenys:");
                for (Irasas irasas : duomenys) {
                    System.out.println(irasas);
                }
            } else {
                System.out.println("Nera irasu");
            }
        }
    }

    private boolean arTaip(String string) {
        return string.toLowerCase().equals("taip");
    }

    // Mocks

    private void mockIvestiPajamasIrIslaidas(Biudzetas biudzetas) {
        biudzetas.pridetiIrasa(new PajamuIrasas(123456, 100, LocalDate.parse("2000-04-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "pajamos", true, "pajamu papildoma informacija"));
        biudzetas.pridetiIrasa(new IslaiduIrasas(987456, 10, LocalDate.parse("2001-06-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "islaidos", "bankinis", "islaidu papildoma informacija"));
    }

    private void mockSpausdintiBalansa(Biudzetas biudzetas) {
        System.out.println("Jusu balansas yra: " + biudzetas.balansas());
    }

    private void mockSpausdintiPajamasIrIslaidas(Biudzetas biudzetas) {
        System.out.println("Pajamu israsas: " + biudzetas.gautiPajamuIrasus());
        System.out.println("Islaidu israsas: " + biudzetas.gautiIslaiduIrasus());
    }
}
