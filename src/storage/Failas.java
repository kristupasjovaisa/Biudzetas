package storage;

import domain.models.Irasas;
import domain.models.IslaiduIrasas;
import domain.models.PajamuIrasas;
import helpers.Helper;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Failas {

    private static final String CSV_SEPARATOR = ",";

    public void issaugotiDuomenis(ArrayList<Irasas> irasai) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("irasai.csv"), "UTF-8"));
        for (Irasas irasas : irasai) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(irasas.getNumeris());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(irasas.getSuma());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(irasas.getData());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(irasas.getKategorija());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(irasas.getPapildomaInfo());
            oneLine.append(CSV_SEPARATOR);
            if (irasas instanceof PajamuIrasas) {
                PajamuIrasas pajamuIrasas = (PajamuIrasas) irasas;
                oneLine.append(pajamuIrasas.isPozymisArIBanka());
            } else if (irasas instanceof IslaiduIrasas) {
                IslaiduIrasas islaiduIrasas = (IslaiduIrasas) irasas;
                oneLine.append(islaiduIrasas.getAtsiskaitymoBudas());
            }

            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public ArrayList<Irasas> gautiDuomenis() {
        ArrayList<Irasas> irasai = new ArrayList();
        List<List<String>> irasaiIsCSVfailo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("irasai.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR);
                irasaiIsCSVfailo.add(Arrays.asList(values));
            }

            for (List<String> list : irasaiIsCSVfailo) {
                int numeris = Integer.parseInt(list.get(0));
                double suma = Double.parseDouble(list.get(1));
                LocalDate date = LocalDate.parse(list.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String kategorija = list.get(3);
                String papildomaInformacija = list.get(4);
                String nezinomasSestasElementas = list.get(5);
                if (kategorija.equals(PajamuIrasas.PAJAMOS)) {
                    boolean pozymisArIBanka = Helper.arTaip(nezinomasSestasElementas);
                    PajamuIrasas pajamuIrasas = new PajamuIrasas(numeris, suma, date, pozymisArIBanka, papildomaInformacija);
                    irasai.add(pajamuIrasas);
                } else if (kategorija.equals(IslaiduIrasas.ISLAIDOS)) {
                    IslaiduIrasas islaiduIrasas = new IslaiduIrasas(numeris, suma, date, nezinomasSestasElementas, papildomaInformacija);
                    irasai.add(islaiduIrasas);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        return irasai;
    }
}
