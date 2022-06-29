/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.general;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author BrayanFSilvaR
 */
public class ConnectionDBJPA {

    private static final String ST_NOMBRE_PERSISTENCIA = "CtrlActividadesPU";
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(ST_NOMBRE_PERSISTENCIA);
            }
            if (em == null) {
                em = emf.createEntityManager();
            }
        } catch (Exception e) {
            throw e;
        }
        return em;
    }

    public void closeEntityManager() {
        try {
            if (emf != null) {
                if (emf.isOpen()) {
                    emf.close();
                }
                emf = null;
            }
            if (em != null) {
                if (em.isOpen()) {
                    em.close();
                }
                em = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
