/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.model.response;

/**
 *
 * @author BrayanFSilvaR
 */
public class ResponseGenerico {

    private Boolean bSuccess;
    private String sMsj;

    public Boolean getbSuccess() {
        return bSuccess;
    }

    public void setbSuccess(Boolean bSuccess) {
        this.bSuccess = bSuccess;
    }

    public String getsMsj() {
        return sMsj;
    }

    public void setsMsj(String sMsj) {
        this.sMsj = sMsj;
    }

}
