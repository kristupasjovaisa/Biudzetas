package presentation;

import domain.models.Irasas;
import domain.models.IslaiduIrasas;
import domain.models.PajamuIrasas;
import domain.Biudzetas;
import helpers.Helper;
import storage.Failas;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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
                case PajamuIrasas.PAJAMOS:
                    double pajamuSuma = gautiValiduotaSuma(sc);
                    LocalDate pajamuData = gautiValiduotaData(sc);
                    System.out.println("Iveskite ar pajamos gautos i banka (Taip/Ne)");
                    String pajamuPozymisArIBanka = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String pajamuPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new PajamuIrasas(
                                    pajamuSuma,
                                    pajamuData,
                                    Helper.arTaip(pajamuPozymisArIBanka),
                                    pajamuPapildomaInfo
                            )
                    );
                    break;

                case IslaiduIrasas.ISLAIDOS:
                    double islaiduSuma = gautiValiduotaSuma(sc);
                    LocalDate islaiduData = gautiValiduotaData(sc);
                    System.out.println("Iveskite atsiskaitymo buda");
                    String atsiskaitymoBudas = sc.next();
                    System.out.println("Iveskite papildoma informacija");
                    String islaiduPapildomaInfo = sc.next();

                    biudzetas.pridetiIrasa(
                            new IslaiduIrasas(
                                    islaiduSuma,
                                    islaiduData,
                                    atsiskaitymoBudas,
                                    islaiduPapildomaInfo
                            )
                    );
                    break;

                default:
                    System.out.println(String.format("Nezinoma komanda: %s", komanda));
            }
        }
    }

    private void spausdintiBalansa(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar norite patikrinti balansa? (Taip/Ne)");

        if (Helper.arTaip(sc.next())) {
            System.out.println(String.format("Jusu balansas yra: %.2f", biudzetas.balansas()));
        }
    }

    private void spausdintiPajamasIrIslaidas(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar noretumete atspausdinti pajamu ir islaidu israsa? (Taip/Ne)");
        if (Helper.arTaip(sc.next())) {
            for (Irasas pajamuIrasas : biudzetas.gautiPajamuIrasus()) {
                System.out.println(String.format("Pajamu israsas: %s", pajamuIrasas));
            }
            for (Irasas islaiduIrasas : biudzetas.gautiIslaiduIrasus()) {
                System.out.println(String.format("Islaidu israsas: %s", islaiduIrasas));
            }
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
                case PajamuIrasas.PAJAMOS:
                    System.out.println("Iveskite ID numeri");
                    int pajamuIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(pajamuIsrasoNumeris);
                    System.out.println(String.format("Jusu pajamu israsai po israso, %d pasalinimo %s", pajamuIsrasoNumeris, biudzetas.gautiPajamuIrasus()));
                    break;

                case IslaiduIrasas.ISLAIDOS:
                    System.out.println("Iveskite ID numeri");
                    int islaiduIsrasoNumeris = sc.nextInt();
                    biudzetas.pasilintiIrasa(islaiduIsrasoNumeris);
                    System.out.println(String.format("Jusu islaidu israsai po israso %d pasalinimo %s", islaiduIsrasoNumeris, biudzetas.gautiIslaiduIrasus()));
                    break;

                default:
                    System.out.println(String.format("Nezinoma komanda: %s", komanda));
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
                        System.out.println(String.format("Irasas%d: %s", (i + 1), biudzetas.getIrasai().get(i)));
                    }

                    System.out.println("Iveskite ID numeri");
                    int israsoNumeris = sc.nextInt();
                    Irasas irasas = biudzetas.gautiIrasa(israsoNumeris);
                    if (irasas != null) {
                        System.out.println(String.format("suma: %.2f, redaguoti? (Taip/Ne)", irasas.getSuma()));
                        if (Helper.arTaip(sc.next())) {
                            System.out.println("Iveskite nauja suma");
                            irasas.setSuma(sc.nextDouble());
                        }
                        System.out.println(String.format("data: %s, redaguoti? (Taip/Ne)", irasas.getData()));
                        if (Helper.arTaip(sc.next())) {
                            System.out.println("Iveskite nauja data formatu YYYY-MM-DD");
                            irasas.setData(LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }
                        System.out.println(String.format("papildoma informacija: %s, redaguoti? (Taip/Ne)", irasas.getPapildomaInfo()));
                        if (Helper.arTaip(sc.next())) {
                            System.out.println("Iveskite nauja informacija");
                            irasas.setPapildomaInfo(sc.next());
                        }

                        if (irasas.getKategorija().equals(PajamuIrasas.PAJAMOS)) {
                            PajamuIrasas pajamuIrasas = (PajamuIrasas) irasas;
                            if (pajamuIrasas != null) {
                                System.out.println(String.format("pozymis ar i banka: %s, redaguoti? (Taip/Ne)", Helper.getTaipArbaNe(pajamuIrasas.isPozymisArIBanka())));
                                if (Helper.arTaip(sc.next())) {
                                    System.out.println("Iveskite nauja pozymi");
                                    pajamuIrasas.setPozymisArIBanka(Helper.arTaip(sc.next()));
                                }
                            }
                        }

                        if (irasas.getKategorija().equals(IslaiduIrasas.ISLAIDOS)) {
                            IslaiduIrasas islaiduIrasas = (IslaiduIrasas) irasas;
                            if (islaiduIrasas != null) {
                                System.out.println(String.format("Atsiskaitymo budas: %s, redaguoti? (Taip/Ne)", islaiduIrasas.getAtsiskaitymoBudas()));
                                if (Helper.arTaip(sc.next())) {
                                    System.out.println("Iveskite nauja atsiskaitymo buda");
                                    islaiduIrasas.setAtsiskaitymoBudas(sc.next());
                                }
                            }
                        }
                        System.out.println(String.format("Jusu israsas po redagavimo: %s", irasas));
                    } else {
                        System.out.println("Blogai ivestas numeris");
                    }

                    break;

                default:
                    System.out.println(String.format("Nezinoma komanda: %s", komanda));
            }
        }
    }

    private void issaugotiDuomenis(Scanner sc, Biudzetas biudzetas) {
        System.out.println("Ar norite issaugoti duomenys? (Taip/Ne)");
        if (Helper.arTaip(sc.next())) {
            try {
                Failas failas = new Failas();
                failas.issaugotiDuomenis(biudzetas.getIrasai());
                System.out.println("Jusu duomenys issaugoti");
            } catch (IOException e) {
                System.out.println(String.format("Nepavyko issaugoti duomenu %s", e));
            }
        }
    }

    private void gautiDuomenis(Scanner sc) {
        System.out.println("Ar norite gauti duomenys? (Taip/Ne)");
        if (Helper.arTaip(sc.next())) {
            Failas failas = new Failas();
            List<Irasas> duomenys = failas.gautiDuomenis();
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

    // Mocks

    private void mockIvestiPajamasIrIslaidas(Biudzetas biudzetas) {
        biudzetas.pridetiIrasa(new PajamuIrasas(123456, 100, LocalDate.parse("2000-04-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")), true, "pajamu papildoma informacija"));
        biudzetas.pridetiIrasa(new IslaiduIrasas(987456, 9.33354, LocalDate.parse("2001-06-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "bankinis", "islaidu papildoma informacija"));
    }

    private void mockSpausdintiBalansa(Biudzetas biudzetas) {
        System.out.println("Jusu balansas yra: " + biudzetas.balansas());
    }

    private void mockSpausdintiPajamasIrIslaidas(Biudzetas biudzetas) {
        System.out.println("Pajamu israsas: " + biudzetas.gautiPajamuIrasus());
        System.out.println("Islaidu israsas: " + biudzetas.gautiIslaiduIrasus());
    }
}
