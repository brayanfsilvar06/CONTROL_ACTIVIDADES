/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.model.request;

/**
 *
 * @author BrayanFSilvaR
 */
public class RequestAuthAPI {

    private String sUser;
    private String sPass;

    public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    @Override
    public String toString() {
        return "RequestAuthAPI{" + "sUser=" + sUser + ", sPass=" + sPass + '}';
    }

}
