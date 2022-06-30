/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.general;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author BrayanFSilvaR
 */
public class ApplicationLog {

    private final Log logAPI;
    private static final String ST_RUTA_CONG_LOG4J = "C:\\CtrlActividades\\LOG_CONF_API.xml";
    
    public ApplicationLog() {
        DOMConfigurator.configure(ST_RUTA_CONG_LOG4J);
        logAPI = LogFactory.getLog(getClass().getName());
    }

    public void logInfo(String sMsj) {
        logAPI.info("-----------------------INICIO LOG INFO--------------------------");
        logAPI.info(sMsj);
        logAPI.info("-----------------------FIN LOG INFO-----------------------------\n");
    }

    public void logWarn(String sMsj) {
        logAPI.warn("-----------------------INICIO LOG ALERTA--------------------------");
        logAPI.warn(sMsj);
        logAPI.warn("-----------------------INICIO LOG ALERTA--------------------------\n");
    }

    public void logError(String sMsj) {
        logAPI.error("-----------------------INICIO LOG ERROR--------------------------");
        logAPI.error(sMsj);
        logAPI.error("-----------------------INICIO LOG ERROR--------------------------\n");
    }

    public void logFatal(String sMsj) {
        logAPI.fatal("-----------------------INICIO LOG FATAL--------------------------");
        logAPI.fatal(sMsj);
        logAPI.fatal("-----------------------INICIO LOG FATAL--------------------------\n");
    }

    public void logDebug(String sMsj) {
        if (logAPI.isDebugEnabled()) {
            logAPI.debug("-----------------------INICIO LOG DEBUG--------------------------");
            logAPI.debug(sMsj);
            logAPI.debug("-----------------------INICIO LOG DEBUG--------------------------\n");
        }
    }

}
