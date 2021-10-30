package com.example.power_energy;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import android.widget.Button;

import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.power_energy.ui.main.SectionsPagerAdapter;
import com.example.power_energy.databinding.ActivityMainBinding;


public class MainActivity<calc> extends AppCompatActivity implements OnItemSelectedListener{
    Button btn_calculo;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


            // Spinner element
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            // Spinner click listener
            spinner.setOnItemSelectedListener(this);

            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("110");
            categories.add("220");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories1 = new ArrayList<String>();
        categories1.add("Chuveiro");
        categories1.add("Geladeira");
        categories1.add("Televisão");
        categories1.add("Lâmpada");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);


            onClickButton();
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

        private void carregarTexto(){
            TextView text = (TextView)findViewById(R.id.potencia);
            TextView text1 = (TextView)findViewById(R.id.tempo);
            TextView text2 = (TextView)findViewById(R.id.qtde);
            TextView text3 = (TextView)findViewById(R.id.amperagem);
            text.setText("0");
            text1.setText("0");
            text2.setText("0");
            text3.setText("0");

        }


        public void onClickButton(){

        carregarTexto();

        btn_calculo = (Button) findViewById(R.id.btn_calculo);

            final EditText potential = (EditText) findViewById(R.id.potencia);
            final EditText tempo = (EditText) findViewById(R.id.tempo);
            final EditText qtde = (EditText) findViewById(R.id.qtde);
            final EditText ampe = (EditText) findViewById(R.id.amperagem);
            Spinner spinner = (Spinner) findViewById(R.id.spinner);


            btn_calculo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        String pote = potential.getText().toString();

                        int pot = Integer.parseInt(potential.getText().toString());
                        int temp = Integer.parseInt(tempo.getText().toString());
                        int quant = Integer.parseInt(qtde.getText().toString());
                        int ampera = Integer.parseInt(ampe.getText().toString());
                        int ddp = Integer.parseInt(spinner.getSelectedItem().toString());


                        /* if (pote.isEmpty()) {
                           int calc1 = (((ampera*quant) * temp) * quant);
                           String num1 = Integer.valueOf(calc1).toString();


                           TextView text3 = (TextView)findViewById(R.id.calculo);

                           text3.setText("Cálculo: " + num1 + " kWh");
                        }*/

                        if (pot != 0) {
                            if (temp != 0) {
                                int calc = ((pot * temp));
                                if (quant != 0) {
                                    int resu = calc*quant;
                                    String num = Integer.valueOf(resu).toString();
                                    TextView text3 = (TextView) findViewById(R.id.calculo);
                                    text3.setText("Cálculo: " + num + " kWh");
                                }else{
                                    Toast.makeText(getApplicationContext(), "Insira algum valor em Quantidade", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Insira algum valor em Tempo", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            if (ampera != 0){
                                    if (temp != 0) {
                                        if (quant != 0) {
                                            int calc1 = (((ddp*ampera)*temp)*quant);
                                            String num = Integer.valueOf(calc1).toString();
                                            TextView text3 = (TextView) findViewById(R.id.calculo);
                                            text3.setText("Cálculo: " + num + " kWh");
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Insira algum valor em Quantidade", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Insira algum valor em Tempo", Toast.LENGTH_LONG).show();
                                    }
                            }else{
                                Toast.makeText(getApplicationContext(), "Insira algum valor em Ameperagem", Toast.LENGTH_LONG).show();
                            }
                        }

                            if (tempo.length() == 0) {
                                Toast.makeText(getApplicationContext(), "Erro:Campo Nulo2", Toast.LENGTH_LONG).show();
                            } else if (qtde.length() == 0) {
                                Toast.makeText(getApplicationContext(), "Erro:Campo Nulo3", Toast.LENGTH_LONG).show();
                            } else if (ampe.length() == 0) {
                                Toast.makeText(getApplicationContext(), "Erro:Campo Nulo4", Toast.LENGTH_LONG).show();
                            } else {
                                int calc = ((pot * temp) * quant);
                                String num = Integer.valueOf(calc).toString();


                                TextView text5 = (TextView) findViewById(R.id.calculo);

                                text5.setText("Cálculo: " + num + " kWh");

                                //Toast.makeText(getApplicationContext(), "Resultado: " + num + " kWh", Toast.LENGTH_LONG).show();//
                            }
                }
            });
    }

}