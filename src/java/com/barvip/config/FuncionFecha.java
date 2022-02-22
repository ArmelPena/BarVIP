package com.barvip.config;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionFecha {
    public static SimpleDateFormat FormatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat FormatoFechaDDMMAA = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat FormatoFechaSlash = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat FormatoHora = new SimpleDateFormat("hh:mm:ss a");
    public static SimpleDateFormat FormatoFechaHora = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    public static SimpleDateFormat FormatoFechaEs = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    public static NumberFormat FormatoFloat = NumberFormat.getInstance();
    public static DecimalFormat formateador = new DecimalFormat("###,###,###,###.##");
    public static DecimalFormat formateadorEdit = new DecimalFormat("############.##");
    public static DecimalFormat formatInteger = new DecimalFormat("###,###,###,###");
    public static NumberFormat FormatoMonedaLocal = NumberFormat.getCurrencyInstance(Locale.getDefault());
    
    public static Date dHoy = new Date();
    static Calendar cal = Calendar.getInstance();
    public static int nAño = cal.get(Calendar.YEAR);
    public static java.sql.Date sqlHoy = new java.sql.Date(dHoy.getTime());
    public static String stHora = String.valueOf(cal.get(Calendar.HOUR)) + ":"
            + String.valueOf(cal.get(Calendar.MINUTE)) + ":"
            + String.valueOf(cal.get(Calendar.SECOND)) + ":"
            + String.valueOf(cal.get(Calendar.AM_PM));
    
    public String getFechaActual() {
        //Metodo usado para obtener la fecha actual
        //@return Retorna un <b>STRING</b> con la fecha actual formato "dd-MM-yyyy"
        Date ahora = new Date();
        return FormatoFecha.format(ahora);
    }

    public String getHoraActual() {
        //Metodo usado para obtener la hora actual del sistema
        //@return Retorna un <b>STRING</b> con la hora actual formato "hh:mm:ss"
        Date ahora = new Date();
        return FormatoHora.format(ahora);
    }

    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        //Sumarle dias a una fecha determinada
        //@param fch La fecha para sumarle los dias
        //@param dias Numero de dias a agregar
        //@return La fecha agregando los dias        
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        //Restarle dias a una fecha determinada
        //@param fch La fecha
        //@param dias Dias a restar
        //@return La fecha restando los dias
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
        //Diferencias entre dos fechas
        //@param fechaInicial La fecha de inicio
        //@param fechaFinal  La fecha de fin
        //@return Retorna el numero de dias entre dos fechas
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    // Suma o resta las horas recibidos a la fecha  
    public Date sumarRestarHorasFecha(Date fecha, int horas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    // Suma los días recibidos a la fecha  
    public static Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static Date sumarRestarMinutosFecha(Date fecha, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MINUTE, minutos);  // numero de minutos a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public static java.sql.Date deStringTosqlDate(String stFecha) {
        //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
        //@param La fecha a convertir a formato date
        //@return Retorna la fecha en formato Date        
        java.util.Date dFecha = new java.util.Date();
        try {
            dFecha = FormatoFecha.parse(stFecha);
        } catch (ParseException ex) {
            return null;
        }
        java.sql.Date sqlFecha = new java.sql.Date(dFecha.getTime());
        return sqlFecha;
    }

    public static Date deStringToDate(String stFecha) {
        try {
            //Devuele un java.util.Date desde un String en formato dd/MM/yyyy
            //@param La fecha a convertir a formato date
            //@return Retorna la fecha en formato Date
            SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
            Date dFecha = null;
            dFecha = formatoDate.parse(stFecha);
            return dFecha;
        } catch (ParseException ex) {
            Logger.getLogger(FuncionFecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean esFechaValida00(String fecha) { // dd/MM/yyyy
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean esFechaValida01(String fecha) { // yyyy/MM/dd
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean esFechaValida02(String fecha) { // ddMMyyyy
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean esFechaValida03(String fecha) { // yyyyMMdd
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean esFechaValida04(String fecha) { // yyyy-MM-dd
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String dateToMySQLDate(java.sql.Date fecha) {
        //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return FormatoFecha.format(fecha);
    }

    public static String EstructurarFecha(String stFecha, int nOrdenFecha) {
        //dd/mm/yyyy
        if(stFecha.length() == 9){
            stFecha = '0'+stFecha.trim();
        }
        if(stFecha.length() == 10){
            stFecha = stFecha.substring(6, 10) + "-" + stFecha.substring(3, 5) + "-" + stFecha.substring(0, 2);
        }
        return stFecha;
    }
}
