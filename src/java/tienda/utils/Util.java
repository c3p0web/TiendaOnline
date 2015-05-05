/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.utils;

/**
 *
 * @author c3p0
 */
public class Util {
    
    /**Limpia el parámetro o devuelve uno por defecto*/
    public static String getParam(String param, String defecto) {        
        if (param!=null && !param.trim().equals(""))
            param=param.trim();
        else
            param=defecto;
        return param;
    }
    /**Devuelve true si el parámetro existe o false en caso contrario*/
    public static boolean getParamBool(String param) {
        boolean result=false;
        if (param!=null && !param.trim().equals(""))
            result=true;
        return result;
    }  
}
