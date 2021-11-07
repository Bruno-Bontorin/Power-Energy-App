package com.example.power_energy;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import android.widget.AdapterView;
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
    Spinner spinner = (Spinner) findViewById(R.id.voltagem);

    // Spinner Drop down elements
    String voltagemArray[] = {"127", "220"};

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, voltagemArray);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);

    // Spinner element
    Spinner aparelho = (Spinner) findViewById(R.id.aparelhos);

    aparelho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        refresh();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parentView) {
      }

    });

    // Spinner Drop down elements
    String aparelhosArray[] = {"Personalizado", "Chuveiro", "Geladeira", "Televisão", "Lâmpada"};

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, aparelhosArray);

    // Drop down layout style - list view with radio button
    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    aparelho.setAdapter(dataAdapter1);
    onClickButton();
  }

  public void getValueSpinnerVoltage() {
    Spinner voltage = (Spinner) findViewById(R.id.voltagem);
    String Text = voltage.getSelectedItem().toString();
    Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
  }

  public int verifyFields(EditText editText) {
    String editTextToString = editText.getText().toString();
    if (TextUtils.isEmpty(editTextToString)) {
      return 1;
    } else {
      if (!editTextToString.matches("[0-9]*")) {
        return 2;
      }
    }
    return 3;
  }

  public int editTextToInt(EditText editText) {
    String editTextToString = editText.getText().toString();
    int editTextToInt = 0;
    switch (verifyFields(editText)) {
      case 1:
        editText.setError("Preenchimento Obrigatório!!");
        System.out.println("Preenchimento Obrigatório!!");
        editTextToInt = 0;
        break;
      case 2:
        editText.setError("Informação incorreta!!");
        System.out.println("Informação incorreta!!");
        editTextToInt = 0;
        break;
      case 3:
        //editText.setError("Retorno da função!!");
        System.out.println("TESTE FUNÇÃO!!!! ->>" + TextUtils.isEmpty(editTextToString));
        System.out.println("NÃO VAZIO!!!! ->>" + editTextToString);
        editTextToInt = Integer.parseInt(editTextToString);
        break;
      default:
        System.out.println("Retorno anormal - consulte ajuda");
        break;
    }

    if (verifyFields(editText) == 1) {

    } else if (verifyFields(editText) == 2) {

    } else if (verifyFields(editText) == 3) {

    }
    return editTextToInt;
  }

  public void setValueByObj(EditText potencia, EditText tempo, EditText quantidade, EditText amperagem) {
    Spinner voltage = (Spinner) findViewById(R.id.voltagem);
    Spinner gadgets = (Spinner) findViewById(R.id.aparelhos);
    String gadgetsValue = gadgets.getSelectedItem().toString();
    switch (gadgetsValue) {
      case "Personalizado":
        potencia.setText("");
        tempo.setText("");
        quantidade.setText("");
        amperagem.setText("");

        System.out.println("");
        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\nEDIT TEXT VALUE: " + potencia.getText().toString() + "\n");
        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\nEDIT TEXT VALUE: " + tempo.getText().toString() + "\n");
        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\nEDIT TEXT VALUE: " + quantidade.getText().toString() + "\n");
        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\nEDIT TEXT VALUE: " + amperagem.getText().toString() + "\n");
        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\nEDIT TEXT VALUE: " + voltage.getSelectedItem().toString() + "\n\n");

        break;
      case "Chuveiro":
        // Difinindo valores fixos
        potencia.setText("2700");
        tempo.setText("0.25");
        quantidade.setText("1");
        amperagem.setText("12");

        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\n\nEDIT TEXT VALUE: " + potencia.getText().toString() + "\n\n");
        break;
      case "Geladeira":
        potencia.setText("2200");
        tempo.setText("24");
        quantidade.setText("1");
        amperagem.setText("10");
        voltage.setSelection(1);

        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\n\nEDIT TEXT VALUE: " + potencia.getText().toString() + "\n\n");
        break;
      case "Televisão":
        potencia.setText("");
        tempo.setText("");
        quantidade.setText("");
        amperagem.setText("");

        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\n\nEDIT TEXT VALUE: " + potencia.getText().toString() + "\n\n");
        break;
      case "Lâmpada":
        potencia.setText("");
        tempo.setText("");
        quantidade.setText("");
        amperagem.setText("");

        System.out.println("\n\nGADGETS VALUE: " + gadgetsValue + "\n\nEDIT TEXT VALUE: " + potencia.getText().toString() + "\n\n");
        break;
      default:
        System.out.println("NÃO VAZIO!!!! ->>" + gadgetsValue);
        break;
    }
  }

  public void refresh() {
    final EditText potential = (EditText) findViewById(R.id.potencia);
    final EditText tempo = (EditText) findViewById(R.id.tempo);
    final EditText qtde = (EditText) findViewById(R.id.qtde);
    final EditText ampe = (EditText) findViewById(R.id.amperagem);
    Spinner spinner = (Spinner) findViewById(R.id.voltagem);
    setValueByObj(potential, tempo, qtde, ampe);
  }

  public void onClickButton() {

    final EditText potential = (EditText) findViewById(R.id.potencia);
    final EditText tempo = (EditText) findViewById(R.id.tempo);
    final EditText qtde = (EditText) findViewById(R.id.qtde);
    final EditText ampe = (EditText) findViewById(R.id.amperagem);
    Spinner spinner = (Spinner) findViewById(R.id.voltagem);


    btn_calculo = (Button) findViewById(R.id.btn_calculo);

    //editTextToInt(potential);
    btn_calculo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getValueSpinnerVoltage();
        int varPot = editTextToInt(potential);
        System.out.println("NÃO VAZIO!!!! ->>" + varPot);
        /*if(varTempo == 0) {
        nao foi possivv....
        } else {
          if (varQtde == 0)
            nao foi possivv....
        } else {
          if (varPot == 0) {
            if(varAmp == 0) {
              nao foi possivv...
            } else {
              função(varPot);
              cal()
            }
          } else  {
            function(varApm);
            cal()
          }
        }*/

      }
    });
  }
}