package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.CoordenadasPP;

/**
 * @author Eduardo Emmanuel Valenzuela
 */
@ManagedBean(name = "coordenadasController")
@RequestScoped
public class coordenadasController {

    public Double latitud;
    public Double longitud;

    public coordenadasController() {

    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    //Aqui realizamos la validacion correspondiente y redireccionamos a la pagina correspondiente
    //Si fue verdadero o falso la coordenada introducida.
    public String validacion() throws FileNotFoundException, IOException {
        CoordenadasPP coor = new CoordenadasPP();

        if (coor.leerExcel(longitud, latitud)) {
            return "result/resultTrue";
        }
        return "result/resultFalse";
    }
}
