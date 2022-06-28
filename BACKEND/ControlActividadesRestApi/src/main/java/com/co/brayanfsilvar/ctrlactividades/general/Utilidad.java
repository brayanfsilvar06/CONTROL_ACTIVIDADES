/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Brayan F Silva R
 */
public class Utilidad {

    private static final Locale localeCO = new Locale("es", "CO");

    private final SimpleDateFormat sdfFechaDiaMesAnio = new SimpleDateFormat("dd/MM/yyyy", localeCO);
    private final SimpleDateFormat sdfFechaMesDiaAnio = new SimpleDateFormat("MM/dd/yyyy", localeCO);
    private final SimpleDateFormat sdfFechaHoraMayuscula = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss", localeCO);
    private final SimpleDateFormat sdfFechaHoraMinuscula = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", localeCO);
    private static final String RegExp_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$";
    private static final String REG_EXP_NUMBER = "^[0-9]*$";
    private static final String REG_EXP_TEXT = "^[^<>*!¡¿?='&%$#\"|°)(/{}+´,._]+$";
    private static final String REG_EXP_TEXT_CONT_TYPE_FOTO = "^[^<>*!¡¿?='&%$#\"|°)({}+´,._]+$";
    private static final String REG_EXP_TEXT_PASS = "^[^<>'\"|°)(/{}+´,._]+$";
    private static final String REG_EXP_IPv4 = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
    private static final String REG_EXP_BASE64 = "[^-A-Za-z0-9+/=]|=[^=]|={3,}$";
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private Random generadorAleatorios = new Random();
    InputStream inputStream;

    public Utilidad() {
        //Constructor
    }

    public String generarAleatorio(int cant) {
        StringBuilder sbResult = new StringBuilder();
        int numeroAleatorio;
        for (int i = 1; i <= cant; i++) {
            numeroAleatorio = 1 + generadorAleatorios.nextInt(9);
            sbResult.append(numeroAleatorio);
        }
        return sbResult.toString();
    }

    public Integer factorial(Integer n) {
        Integer result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public long generarLlave(int canDigitos) {
        long intNumber = 1;
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(canDigitos);
        intNumber = Long.parseLong(format.format(intNumber).replace(",", ""));
        return (long) (intNumber * Math.random());
    }

    public ArrayList<String> contrasenaRandom(int cantC, String cantSalt) {
        EncriptarSha xtran = new EncriptarSha();
        ArrayList<String> lista = new ArrayList<>();

        int nRand, lMunRand, lMayRand, numRand;
        String contra = "", passSha;
        ArrayList array = new ArrayList();
        boolean flag = false;

        String numeros[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String lMinusculas[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String lMayusculas[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String total[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        nRand = (int) Math.round(Math.random() * (numeros.length - 1));
        lMunRand = (int) Math.round(Math.random() * (lMinusculas.length - 1));
        lMayRand = (int) Math.round(Math.random() * (lMayusculas.length - 1));

        String vecTemp[] = {numeros[nRand], lMinusculas[lMunRand], lMayusculas[lMayRand]};

        for (int i = 0; i < cantC; i++) {
            if (i < vecTemp.length) {
                while (false == flag) {
                    numRand = (int) Math.round(Math.random() * (vecTemp.length - 1));
                    if (array.indexOf(numRand) == -1) {
                        contra += vecTemp[numRand];
                        array.add(numRand);
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            } else {
                numRand = (int) Math.round(Math.random() * (total.length - 1));
                contra += total[numRand];
            }

            flag = false;
        }
        passSha = xtran.encriptar(contra, cantSalt);

        lista.add(contra);
        lista.add(passSha);

        return lista;
    }

    public String ocultarCorreo(String correo) {
        int p1, div2;
        String cadena1, xVar = "", cadena2, cadena3, cadena4;
        p1 = correo.indexOf("@");
        cadena1 = correo.substring(0, p1);
        int tam1 = 0;
        tam1 = cadena1.length();
        div2 = (tam1 / 3) + 1;
        cadena2 = cadena1.substring(0, div2 - 1);
        for (int i = 0; i < div2; i++) {
            xVar += "x";
        }
        cadena3 = cadena2 + xVar;
        cadena4 = cadena3 + correo.substring(cadena3.length(), correo.length());

        return cadena4;
    }

    public String capitalizarFrase(String cadena) {
        cadena = cadena.toLowerCase();
        String[] splitCadena = cadena.split(" ");
        String fraseCapitalizada = "";

        for (int i = 0; i < splitCadena.length; i++) {
            String primeraLetra = splitCadena[i].substring(0, 1).toUpperCase();
            String restoDeLaCadena = splitCadena[i].substring(1);
            if (splitCadena.length == 1 || i == 0) {
                fraseCapitalizada += primeraLetra + restoDeLaCadena;
            } else {
                fraseCapitalizada += " ";
                fraseCapitalizada += primeraLetra + restoDeLaCadena;
            }
        }
        return fraseCapitalizada;
    }

    public String encodeBase64(String sParEncode) {
        String rtaEncode64 = "";
        rtaEncode64 = Base64.getEncoder().encodeToString(sParEncode.getBytes());
        return rtaEncode64;
    }

    public String decodeBase64(String sParDecode) {
        String rtadecode64 = "";
        byte[] decodedBytes = Base64.getDecoder().decode(sParDecode);
        rtadecode64 = new String(decodedBytes);
        return rtadecode64;
    }

    public String dateToStringFormatFechaHora(Date date) {
        String sFechaFormat = "";
        sFechaFormat = sdfFechaHoraMayuscula.format(date);
        return sFechaFormat.toUpperCase();
    }

    public String dateToStringFormatFechaHoraMin(Date date) {
        String sFechaFormat = "";
        sFechaFormat = sdfFechaHoraMinuscula.format(date);
        return sFechaFormat.toUpperCase();
    }

    public Date stringToDateFechaHoraMinus(String sFechaTemp) throws ParseException {
        Date date = null;
        date = sdfFechaHoraMinuscula.parse(sFechaTemp);
        return date;
    }

    public String dateToStringFormatFecha(Date date) {
        String sFechaFormat = "";
        sFechaFormat = sdfFechaDiaMesAnio.format(date);
        return sFechaFormat.toUpperCase();
    }

    public String dateToStringFormatFechaMesDiaAnio(Date date) {
        String sFechaFormat = "";
        sFechaFormat = sdfFechaMesDiaAnio.format(date);
        return sFechaFormat.toUpperCase();
    }

    public String valorPattern(String monto) throws ParseException {
        String sValorPattern;
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(pattern);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        df.setDecimalFormatSymbols(symbols);
        double dMonto = df.parse(monto).doubleValue();
        BigDecimal bdMonto = BigDecimal.valueOf(dMonto);
        bdMonto = bdMonto.setScale(2, RoundingMode.HALF_UP);
        sValorPattern = df.format(bdMonto);
        return sValorPattern;
    }

    public Boolean isNumber(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_NUMBER);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();        
    }

    public Boolean isEmail(String input) {        
        Pattern pattern = Pattern.compile(RegExp_EMAIL);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Boolean isText(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_TEXT);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Boolean isTextContentTypeFoto(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_TEXT_CONT_TYPE_FOTO);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Boolean isClaveAceptada(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_TEXT_PASS);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Boolean isIPv4(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_IPv4);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Boolean isBase64(String input) {
        Pattern pattern = Pattern.compile(REG_EXP_BASE64);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();        
    }

    public String limpiarData(String sDato) {
        String sDataTemp = sDato.replace("<script>", "");
        return sDataTemp.replace("</script>", "");
    }

    public String sDecodeUTF8(String sCadena) {
        String sCadenaEncodingUTF8 = "";
        sCadenaEncodingUTF8 = new String(sCadena.getBytes(), UTF_8);
        return sCadenaEncodingUTF8;
    }

    public String sEncodeUTF8(String sCadena) {
        String sCadenaEncodingUTF8 = "";
        sCadenaEncodingUTF8 = Base64.getEncoder().encodeToString(sCadena.getBytes());
        return sCadenaEncodingUTF8;
    }

    public String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }
    
    public String obtenerMsjExcepcion(Exception e){
        return (e.getMessage() != null && !e.getMessage().isEmpty()) ? e.getMessage() : e.toString();
    }
}
