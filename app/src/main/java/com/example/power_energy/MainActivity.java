package com.example.power_energy;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import android.widget.Button;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.power_energy.ui.main.SectionsPagerAdapter;
import com.example.power_energy.databinding.ActivityMainBinding;


public class MainActivity<calc> extends AppCompatActivity {
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


  private void carregarTexto() {
    TextView text = (TextView) findViewById(R.id.potencia);
    TextView text1 = (TextView) findViewById(R.id.tempo);
    TextView text2 = (TextView) findViewById(R.id.qtde);
    TextView text3 = (TextView) findViewById(R.id.amperagem);
    text.setText("0");
    text1.setText("0");
    text2.setText("0");
    text3.setText("0");
  }

  public void spinnerTest() {
    Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
    String Text = mySpinner.getSelectedItem().toString();
    Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
  }

  public int editTextToInt(EditText editText) {
    String editTextToString = editText.getText().toString();
    int editTextToInt = 0;
    if (editTextToString.isEmpty() || !editTextToString.matches("[0-9]*")) {
      System.out.println("VAZIO OU COM LETRAS!!!! ->>" + editTextToString);
      carregarTexto();
    } else {
      System.out.println("NÃO VAZIO!!!! ->>" + editTextToString);
      editTextToInt = Integer.parseInt(editTextToString);
    }
    return editTextToInt;
  }


  public void onClickButton() {



    btn_calculo = (Button) findViewById(R.id.btn_calculo);

    final EditText potential = (EditText) findViewById(R.id.potencia);
    final EditText tempo = (EditText) findViewById(R.id.tempo);
    final EditText qtde = (EditText) findViewById(R.id.qtde);
    final EditText ampe = (EditText) findViewById(R.id.amperagem);
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    int potencia = editTextToInt(potential);
    System.out.println("POTENCIA É!!! -->> " + potencia);

    btn_calculo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //String pot = editTextToString(potential);
        //nullTest(pot);
        //String pote = potential.getText().toString();


        //int pot = Integer.parseInt(potential.getText().toString());
        //int temp = Integer.parseInt(tempo.getText().toString());
        //int quant = Integer.parseInt(qtde.getText().toString());
        //int ampera = Integer.parseInt(ampe.getText().toString());
        //int ddp = Integer.parseInt(spinner.getSelectedItem().toString());
        spinnerTest();

        /* if (pote.isEmpty()) {
           int calc1 = (((ampera*quant) * temp) * quant);
           String num1 = Integer.valueOf(calc1).toString();


           TextView text3 = (TextView)findViewById(R.id.calculo);

           text3.setText("Cálculo: " + num1 + " kWh");
        }*/


      }
    });
  }
}