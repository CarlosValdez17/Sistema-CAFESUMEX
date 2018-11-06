/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos_Configuraciones;

/**
 *
 * @author Carlos Valdez
 */
public class validaConfi {
    public validaConfi(){
    }
    
    public String primerLetraMayuscula(String cadena) {
        try {
            char[] caracteres = cadena.toCharArray();
            caracteres[0] = Character.toUpperCase(caracteres[0]);
            // el -2 es para evitar una excepción al caernos del arreglo
            for (int i = 0; i < cadena.length() - 2; i++) // Es 'palabra'
            {
                if (caracteres[i] == ' ' || caracteres[i] == '_') // Reemplazamos
                {
                    caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
                }
            }
            return new String(caracteres);
        } catch (Exception e) {
            //System.out.println("Excepcion: " + e);
        }
        return null;
    }
    
}
