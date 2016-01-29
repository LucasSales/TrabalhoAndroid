package br.ufc.quixada.dsdm.myapplicationtestemulttabs.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.User;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.domain.WrapObjToNetwork;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;

/**
 * Created by viniciusthiengo on 7/26/15.
 */
public class NetworkConnection {
    public static final String TAG = "LOG";
    public static final List<Amigo> amigos = null;
    private static NetworkConnection instance;
    private Context mContext;
    private RequestQueue mRequestQueue;
    private Transaction mTransaction;


    public NetworkConnection(Context c){
        mContext = c;
        mRequestQueue = getRequestQueue();
    }


    public static NetworkConnection getInstance( Context c ){
        if( instance == null ){
            instance = new NetworkConnection( c.getApplicationContext() );
        }
        return( instance );
    }


    public RequestQueue getRequestQueue(){
        if( mRequestQueue == null ){
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return(mRequestQueue);
    }


    public <T> void addRequestQueue( Request<T> request ){
        getRequestQueue().add(request);
    }


    public void execute1( Transaction t, String tag ){
        mTransaction = t;
        WrapObjToNetwork obj = t.doBefore();
        //execute(obj, tag);

    }



    public void execute( final WrapObjToNetwork obj, String tag, String url ){
        Gson gson = new Gson();

        if( obj == null ){
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("jsonObject", gson.toJson(obj));
        Log.i("LOG", "params: " + gson.toJson(obj).toString());

        CustomRequest request = new CustomRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("LOG","onResponse" + response);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse(): " + error.getMessage());
                    //mTransaction.doAfter(null);
            }
        });//final dos parametros do CustimRequest
        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);
    }

    public void executeBusca( final WrapObjToNetwork obj, String tag, String url ){
        Gson gson = new Gson();

        if( obj == null ){
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("jsonObject", gson.toJson(obj));
        Log.i("LOG", "params: " + gson.toJson(obj).toString());

        CustomRequest request = new CustomRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("LOG","onResponse: " + response.toString());
                        JSONArray pessoas = null;
                        try {
                            pessoas = response.getJSONArray("pessoas");
                            for (int i =0; i < pessoas.length();i++){
                                GsonBuilder builder = new GsonBuilder();
                                Gson gson2 = builder.create();
                                final User ob = gson2.fromJson(pessoas.getJSONObject(i).toString(), User.class);
                                Log.i("Usuairo","Nome: " +ob.getNome());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse(): " + error.getMessage());
                //mTransaction.doAfter(null);
            }
        });//final dos parametros do CustimRequest




        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);


    }


    public void executeBuscaAmigo( final WrapObjToNetwork obj, String tag, String url ){
        Gson gson = new Gson();

        if( obj == null ){
            return;
        }



        HashMap<String, String> params = new HashMap<>();
        params.put("jsonObject", gson.toJson(obj));
        Log.i("LOG", "params: " + gson.toJson(obj).toString());

        CustomRequest request = new CustomRequest(Request.Method.POST,
                url,
                params,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("LOG","onResponse: " + response.toString());
                        JSONArray pessoas = null;
                        try {
                            pessoas = response.getJSONArray("pessoas");
                            for (int i =0; i < pessoas.length();i++){
                                GsonBuilder builder = new GsonBuilder();
                                Gson gson2 = builder.create();

                                //pessoas pq é pessoas q eu to pegando
                                final Amigo ob = gson2.fromJson(pessoas.getJSONObject(i).toString(), Amigo.class);
                                Log.i("Amigo","Nome: " +ob.getId());
                                //amigos.add(ob);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse(): " + error.getMessage());
                //mTransaction.doAfter(null);
            }
        });//final dos parametros do CustimRequest




        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        addRequestQueue(request);
    }
}
