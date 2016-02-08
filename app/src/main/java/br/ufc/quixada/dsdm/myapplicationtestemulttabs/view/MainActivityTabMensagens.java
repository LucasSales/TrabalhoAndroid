package br.ufc.quixada.dsdm.myapplicationtestemulttabs.view;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufc.quixada.dsdm.myapplicationtestemulttabs.adapters.Adaptador_Msn_Lista;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.controle.BroadCastMsg;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM.MainActivity;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM.PushMessage2;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM.QuickstartPreferences;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Amigo;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.AmigoDAO;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.model.Mensagem_Amigos;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.R;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.googleGCM.RegistrationIntentService;
import br.ufc.quixada.dsdm.myapplicationtestemulttabs.service.ServiceLocal;
import de.greenrobot.event.EventBus;


public class MainActivityTabMensagens extends AppCompatActivity {


    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static  ListView listView;
    private static ArrayList<Mensagem_Amigos> listaMensagemAmigo;
    private static TextView tvvazio;
    private  ServiceLocal service;
    private boolean conectado = false;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout t1 = (TabLayout) findViewById(R.id.tabbar);
        t1.setupWithViewPager(mViewPager);

        AmigoDAO aDao = new AmigoDAO(this);
        List<Amigo> a = aDao.buscar();

        if(a.size() > 0)
            Log.i("TEM GENTE","GENTE"+ a.get(0).getNick());

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.

            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        //pegar latitude e longitude

        Intent i = new Intent(this, ServiceLocal.class);
        startService(i);
    }
    //TAVA DANDO ERRO POR ISSO COMENTEI
   /* @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, ServiceLocal.class), mConnection,
                Context.BIND_AUTO_CREATE);

        if(conectado) {
            Location local = MainActivityTabMensagens.this.service.getUltimaLocalizacao();
        }
    }*/
    //SERVICE PARA PEGAR LOCALIZACAO
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ServiceLocal.LocalBinder binder = (ServiceLocal.LocalBinder) service;
            MainActivityTabMensagens.this.service =  binder.getService();
            conectado = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            conectado = false;
        }
    };

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {

                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i("LOG", "This device is not supported.");

                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClickNovoGrupo(View view){
        Intent i;
        i = new Intent( this, ActivityCriarGrupo.class );
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.action_nova_mensagem){
            Intent i;
            i = new Intent( this, ActivityListaAmigos.class );
            startActivity(i);
        }else if (id == R.id.action_settings) {
            Intent i = new Intent(this,ActivityConfiguracoes.class);
            startActivity(i);
        }else if(id == R.id.action_logout){
            finish();
        }else if(id == R.id.action_add_amigo){
            Intent i = new Intent(this,ActivityAdicionarAmigo.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return TabGrupo.newInstance(position+1);
                case 2:
                    return TabAmigos.newInstance(position+1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mensagens";
                case 1:
                    return "Grupos";
                case 2:
                    return "Amigos";
            }
            return null;
        }


    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        BroadCastMsg mRegistrationBroadcastReceiver;
        BroadcastReceiver br;
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mRegistrationBroadcastReceiver =  new BroadCastMsg();

            String a = mRegistrationBroadcastReceiver.getResultData();



            /*br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    String mensagens = intent.getStringExtra("mensagem");
                    //Log.i("BROADCAST","boradcast");


                    Gson gson = new Gson();
                    final String[] ob = gson.fromJson(mensagens, String[].class);

                    List<String> listMensagens = Arrays.asList(ob);
                    for(int i = 0; i<listMensagens.size();i++){
                        Log.i("MSG","mensagens :" + listMensagens.get(i).toString());
                    }

                    Log.i("BROADCAST2", "MANDO PARACA");
                }

            };*/


            tvvazio = (TextView) rootView.findViewById(R.id.textViewVazioMensagem);
            listView = (ListView) rootView.findViewById(R.id.listViewMensagem);
            listaMensagemAmigo = new ArrayList<>();


            Mensagem_Amigos msn = new Mensagem_Amigos();

            msn.setNome_amigo("Lucas Sales");
            msn.setUltima_visualizacao("10:50");
            msn.setUltimo_texto("Testando se funfou");
            msn.setImg_amigo("http://pre07.deviantart.net/e5e6/th/pre/f/2011/036/7/9/homer_simpson___06___simpsons_by_frasier_and_niles-d38uqts.jpg");


            listaMensagemAmigo.add(msn);
            if(!listaMensagemAmigo.isEmpty()){
                Adaptador_Msn_Lista adapter = new Adaptador_Msn_Lista(getActivity(), listaMensagemAmigo);
                listView.setAdapter(adapter);

            }else{
                tvvazio.setText("Nenhuma Nota");
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent inter = new Intent(rootView.getContext(), ActivityBatePapo.class);
                    startActivity(inter);

                }
            });


            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("mensagens "));

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
        }

        @Override
        public void onPause() {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
            super.onPause();
        }

    }

}
