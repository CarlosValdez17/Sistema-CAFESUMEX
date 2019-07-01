/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idioma;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author USUARIO
 */
public class Propiedades extends Properties {

    public Propiedades(String idioma) {
        switch (idioma) {
            case "Español":
                getProperties("español.properties");
                break;

            case "Ingles":
                getProperties("ingles.properties");
                break;

            case "Oromo":
                getProperties("oromo.properties");
                break;
            case "Ingles Etiopia":
                getProperties("inglesetiopia.properties");
                break;
        }
    }

    private void getProperties(String idioma) {
        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (IOException ex) {

        }
    }
}
