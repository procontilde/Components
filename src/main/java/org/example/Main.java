package org.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner miScan = new Scanner(System.in);
    private static final Map<Integer, SparePart> spareParts = new HashMap<>();
    private static final Map<Integer, Component> components = new HashMap<>();


    public static void main(String[] args) {

        char option;

        do {

            System.out.println("Elija una de las siguientes opciones:\na. Empezar\nb. Info\nc. Salir");
            option = miScan.nextLine().charAt(0);

            switch (option) {

                case 'a':

                    start();
                    break;

                case 'b':

                    System.out.println("@author: MrPró");
                    break;

                case 'c':

                    System.out.println("Hasta pronto");
                    break;

                default:

                    System.out.println("Introduzca una opción válida");
                    break;

            }

        } while (option != 'c');

    }


    public static void start() {

        char option;

        do {

            System.out.println("Elija una de las siguiente opciones:\n"
                                + "a. Alta de SparePart"
                                + "b. Alta de Component"
                                + "c. Buscar y mostrar una pieza"
                                + "d. Generar JSON y grabar a fichero"
                                + "e. Importar una pieza desde JSON"
                                + "f. Añadir una SparePart a un Component"
                                + "g. Quitar una SparePart de un Component"
                                + "h. Buscar una SparePart en un Component"
                                + "i. Salir");

            option = miScan.next().charAt(0);

            switch (option) {

                case 'a':

                    createSparePart();
                    break;

                case 'b':

                    createComponent();
                    break;

                case 'c':

                    displayPiece();
                    break;

                case 'd':

                    generateAndSaveJSON();
                    break;

                case 'e':

                    importPieceFromJSON();
                    break;

                case 'f':

                    addSparePartToComponent();
                    break;

                case 'g':

                    removeSparePartFromComponent();
                    break;

                case 'h':

                    findSparePartInComponent();
                    break;

                case 'i':

                    break;

                default:

                    System.out.println("Debe introducir una opción válida");

            }

        } while (option != 'i');

    }

    private static void createSparePart() {

        System.out.print("Introduzca el código: ");
        int code = Integer.valueOf(miScan.nextLine());
        System.out.print("Introduzca la descripción: ");
        String description = miScan.nextLine();
        System.out.print("Introduzca el precio: ");
        double price = Double.valueOf(miScan.nextLine());
        SparePart sparePart = new SparePart(code, description, price);
        spareParts.put(code, sparePart);
        System.out.println("SparePart creado con éxito");
    }

    private static void createComponent() {

        System.out.print("Introduzca el código: ");
        int code = Integer.valueOf(miScan.nextLine());
        System.out.print("Introduzca la descripción: ");
        String description = miScan.nextLine();
        System.out.print("Introduzca el precio: ");
        double price = Double.valueOf(miScan.nextLine());
        Component component = new Component(code, description, price);
        components.put(code, component);
        System.out.println("Componente creado con éxito.");

    }

    private static void displayPiece() {

        System.out.print("Introduzca el código de la pieza: ");
        int code = Integer.valueOf(miScan.nextLine());

        if (spareParts.containsKey(code)) {

            System.out.println("SparePart encontrado:");
            System.out.println(spareParts.get(code));

        } else if (components.containsKey(code)) {

            System.out.println("Componente encontrado:");
            System.out.println(components.get(code));

        } else {

            System.out.println("El código no está");

        }

    }

    private static void generateAndSaveJSON() {

        try {

            System.out.print("Introduzca el código de la pieza: ");
            int code = Integer.valueOf(miScan.nextLine());
            SparePart sP = spareParts.get(code);

            if (sP != null) {

                Gson gson = new Gson();
                String json = gson.toJson(sP);
                FileWriter writer = new FileWriter("sparepart_" + code + ".json");
                writer.write(json);
                writer.close();
                System.out.println("JSON escrito con éxito");

            } else {

                System.out.println("El código no está");

            }

        } catch (IOException e) {

            e.getMessage();

        }

    }

    private static void importPieceFromJSON() {

        System.out.print("Introduzca el path del archivo JSON: ");
        String path = miScan.next();
        String json = readFileAsString(path);
        SparePart sP = SparePart.formJson(json);

        if (sP != null) {

            System.out.println(sP);

        } else {

            System.out.println("ERROR");

        }

    }

    private static String readFileAsString(String name) {

        StringBuilder content = new StringBuilder();
        BufferedReader bR = null;

        try {

            bR = new BufferedReader(new FileReader(name));
            String line;

            while ((line = bR.readLine()) != null) {

                content.append(line);

            }

            bR.close();
            bR = null;

        } catch (IOException e) {

            e.getMessage();

        } finally {

            try{

                if(bR != null) bR.close();

            }catch (IOException iO){

                iO.getMessage();

            }

        }

        return content.toString();

    }


    private static void addSparePartToComponent() {
        System.out.print("Introduzca el código del componente: ");
        int code = Integer.valueOf(miScan.nextLine());
        Component c = components.get(code);

        if (c != null) {

            System.out.print("Introduzca el código de la SparePart: ");
            int sparePartCode = Integer.valueOf(miScan.nextLine());
            SparePart sP = spareParts.get(sparePartCode);

            if (sP != null) {

                if (c.addSparePart(sP)) {

                    System.out.println("SparePart añadida con éxito");

                } else {

                    System.out.println("La SparePart ya está existe");

                }

            } else {

                System.out.println("No se encontró ninguna SparePart con ése código");

            }

        } else {

            System.out.println("No se encontró ningún componente con ése código");

        }

    }

    private static void removeSparePartFromComponent() {

        System.out.print("Introduzca el código del componente: ");
        int code = Integer.valueOf(miScan.nextLine());
        Component component = components.get(code);

        if (component != null) {

            System.out.print("Introduzca el código de la SparePart: ");
            int sparePartCode = Integer.valueOf(miScan.nextLine());

            if (component.removeSparePart(sparePartCode)) {

                System.out.println("SparePart eliminada con éxito");

            } else {

                System.out.println("No se encontró ninguna SparePart con ése código");

            }

        } else {

            System.out.println("No se encontró ningún Component con con ése código");

        }

    }

    private static void findSparePartInComponent() {

        System.out.print("Introduzca el código del componente: ");
        int code = Integer.valueOf(miScan.nextLine());
        Component component = components.get(code);

        if (component != null) {

            System.out.print("Introduzca el código de la SparePart: ");
            int sparePartCode = Integer.valueOf(miScan.nextLine());
            SparePart sP = component.findSparePart(sparePartCode);

            if (sP != null) {

                System.out.println("SparePart encontrada:");
                System.out.println(sP);

            } else {

                System.out.println("No se encontró ninguna SparePart con ése código");

            }

        } else {

            System.out.println("No se encontró ningún Component con ése código");

        }

    }

}



