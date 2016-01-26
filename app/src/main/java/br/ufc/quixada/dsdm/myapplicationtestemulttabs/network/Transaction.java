package br.ufc.quixada.dsdm.myapplicationtestemulttabs.network;

import org.json.JSONObject;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;

/**
 * Created by viniciusthiengo on 7/26/15.
 */
public interface Transaction {
    WrapObjToNetwork doBefore();

    void doAfter(JSONObject jsonObject);
}
