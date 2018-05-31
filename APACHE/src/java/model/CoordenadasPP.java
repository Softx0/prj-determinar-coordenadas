/*
*@author Emmanuel Valenzuela
*
**/
package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CoordenadasPP {
    //Metodo para leer el excel con las coordenadas de Puerto Plata
    public boolean leerExcel(Double Ulongitud, Double Ulatitud) {
        
        ArrayList<Poligono> ListaPoligono = new ArrayList<>(); //Objeto del API collections, ArrayList de tipo poligono
        Poligono punto = new Poligono();
        try {

            FileInputStream f = new FileInputStream("D:\\Programacion\\Programacion en JAVA\\APACHE\\datos.xls");
            HSSFWorkbook libro = new HSSFWorkbook(f);
            HSSFSheet hoja = libro.getSheetAt(0);

            Iterator<Row> filas = hoja.iterator(); 
            Iterator<Cell> celdas; // Iterator es el metodo que viene con Iterator
            //Iterator sirve para recorrer estructuras de datos
            Row fila;
            Cell celda;
            //recorriendo las filas
            while (filas.hasNext()) {

                Poligono pol = new Poligono();
                fila = filas.next();

                celdas = fila.cellIterator();   //Viene con la clase ss.Usermodel.Row
                //recorriendo las celdas
                while (celdas.hasNext()) {

                    celda = celdas.next();

                    switch (celda.getCellType()) {

                        case Cell.CELL_TYPE_NUMERIC:

                            if (pol.longitud == null) {
                                pol.longitud = celda.getNumericCellValue();
                            } else {
                                pol.latitud = celda.getNumericCellValue();
                            }

                            ListaPoligono.add(pol);
                            break;
                    }
                }
            }

            f.close();
            libro.close();

            punto.latitud = Ulatitud;
            punto.longitud = Ulongitud;

            boolean EstaEnPoligono = BuscarEnPoligono(ListaPoligono, punto);  //ArrayList, punto ... Aqui se aplica que el metodo devuelva un true o false para saber si esta dentro o no

            if (EstaEnPoligono) {

                return true;

            } else {
                return false;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    //El siguiente algoritmo se basa en un algoritmo matematico, un poligono irregular convexo
    //dividirlo en triangulos y sacarle el area a cada uno de ellos, luego sumarlos.
    public static boolean BuscarEnPoligono(ArrayList<Poligono> coordenadas, Poligono punto) {
        int i;
        int j;
        boolean result = false;

        for (i = 0, j = coordenadas.size() - 1; i < coordenadas.size(); j = i++) {
            if ((coordenadas.get(i).getLatitud() > punto.getLatitud()) != (coordenadas.get(j).getLatitud() > punto.getLatitud())
                    && (punto.getLongitud() < (coordenadas.get(j).getLongitud() - coordenadas.get(i).getLongitud())
                    * (punto.getLatitud() - coordenadas.get(i).getLatitud()) / (coordenadas.get(j).getLatitud()
                    - coordenadas.get(i).getLatitud()) + coordenadas.get(i).getLongitud())) {
                result = !result;
            }
        }
        return result;
    }
}
