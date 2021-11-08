package com.example.power_energy;

import android.content.res.Resources;
import android.os.Bundle;

import android.text.TextUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import android.widget.Button;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.power_energy.ui.main.SectionsPagerAdapter;
import com.example.power_energy.databinding.ActivityMainBinding;

/* Imports Unused */
/*
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
*/

public class MainActivity extends AppCompatActivity {
  Button btn_calculation;

  private ActivityMainBinding binding;
  public String[] gadgetsArray;
  public String[] voltageArray;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /* Binding */
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    /* Get String.xml Resources */
    Resources resources = getResources();

    /* Set arrays of resources  */
    gadgetsArray = resources.getStringArray(R.array.gadgets_name_array);
    voltageArray = resources.getStringArray(R.array.voltage_array);

    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
    ViewPager viewPager = binding.viewPager;
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = binding.tabs;
    tabs.setupWithViewPager(viewPager);

    /* Function fab icon */
    /* FloatingActionButton fab = binding.fab; */
    /*    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });*/

    // Spinner element
    Spinner spinner = findViewById(R.id.voltage);

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, voltageArray);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Attaching data adapter to spinner
    spinner.setAdapter(dataAdapter);

    // Spinner element
    Spinner gadget = findViewById(R.id.gadgets);

    gadget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        refresh();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parentView) {
        System.out.println("\n\n Nothing Selected -> Function: onNothingSelected \n\n");
        Toast.makeText(getApplicationContext(), "\n\n Nothing Selected -> Function: onNothingSelected \n\n", Toast.LENGTH_LONG).show();
      }

    });

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gadgetsArray);

    // Drop down layout style - list view with radio button
    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    gadget.setAdapter(dataAdapter1);
    onClickButton();
  }

  public void getValueSpinnerVoltage() {
    Spinner voltage = findViewById(R.id.voltage);
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
        editText.setError("Required");
        System.out.println("Required!");
        editTextToInt = 0;
        break;
      case 2:
        editText.setError("Incorrect Information");
        System.out.println("Incorrect Information");
        editTextToInt = 0;
        break;
      case 3:
        System.out.println("\n\nReturn function: verifyFields -> " + TextUtils.isEmpty(editTextToString) + "\n\n");
        System.out.println("\n\nNot Empty -> " + editTextToString + "\n\n");
        editTextToInt = Integer.parseInt(editTextToString);
        break;
      default:
        System.out.println("\n\n Abnormal return - see help... -> Default break function: editTextToInt \n\n");
        break;
    }
    return editTextToInt;
  }

  public void setValueByObj(EditText time, EditText amperage, EditText amount, EditText potency) {
    /* Get String.xml Resources */
    Resources resources = getResources();

    /* Create Spinner */
    Spinner voltage = findViewById(R.id.voltage);
    Spinner gadgets = findViewById(R.id.gadgets);
    String gadgetsValue = gadgets.getSelectedItem().toString();

    switch (gadgetsValue) {
      case "Custom":
        /* Set value array of resources  */
        String[] customObjArray = resources.getStringArray(R.array.custom_obj_array);

        /* Set values through Array */
        voltage.setSelection(Integer.parseInt(customObjArray[0]));
        time.setText(customObjArray[1]);
        amperage.setText(customObjArray[2]);
        amount.setText(customObjArray[3]);
        potency.setText(customObjArray[4]);
        break;
      case "Shower":
        /* Set value array of resources  */
        String[] showerObjArray = resources.getStringArray(R.array.shower_obj_array);

        /* Set values through Array */
        voltage.setSelection(Integer.parseInt(showerObjArray[0]));
        time.setText(showerObjArray[1]);
        amperage.setText(showerObjArray[2]);
        amount.setText(showerObjArray[3]);
        potency.setText(showerObjArray[4]);
        break;
      case "Refrigerator":
        /* Set value array of resources  */
        String[] refrigeratorObjArray = resources.getStringArray(R.array.refrigerator_obj_array);

        /* Set values through Array */
        voltage.setSelection(Integer.parseInt(refrigeratorObjArray[0]));
        time.setText(refrigeratorObjArray[1]);
        amperage.setText(refrigeratorObjArray[2]);
        amount.setText(refrigeratorObjArray[3]);
        potency.setText(refrigeratorObjArray[4]);
        break;
      case "Television":
        /* Set value array of resources  */
        String[] televisionObjArray = resources.getStringArray(R.array.television_obj_array);

        /* Set values through Array */
        voltage.setSelection(Integer.parseInt(televisionObjArray[0]));
        time.setText(televisionObjArray[1]);
        amperage.setText(televisionObjArray[2]);
        amount.setText(televisionObjArray[3]);
        potency.setText(televisionObjArray[4]);
        break;
      case "Lamp":
        /* Set value array of resources  */
        String[] lampObjArray = resources.getStringArray(R.array.lamp_obj_array);

        /* Set values through Array */
        voltage.setSelection(Integer.parseInt(lampObjArray[0]));
        time.setText(lampObjArray[1]);
        amperage.setText(lampObjArray[2]);
        amount.setText(lampObjArray[3]);
        potency.setText(lampObjArray[4]);
        break;
      default:
        System.out.println("\n\n Nothing to execute... -> Default break funtion: setValueByObj \n\n");
        break;
    }
  }

  public void refresh() {
    /* Get String.xml Resources */
    Resources resources = getResources();

    /* Set value array of resources  */
    gadgetsArray = resources.getStringArray(R.array.gadgets_name_array);

    /* Set edit text value to text */
    final EditText potency = findViewById(R.id.potency);
    final EditText time = findViewById(R.id.time);
    final EditText amount = findViewById(R.id.amount);
    final EditText amperage = findViewById(R.id.amperage);

    /* Performing function */
    setValueByObj(time, amperage, amount, potency);
  }

  public void onClickButton() {

    /* Set edit text value to text */
    final EditText potency = findViewById(R.id.potency);
/*    final EditText time = findViewById(R.id.time);
    final EditText amount = findViewById(R.id.amount);
    final EditText amperage = findViewById(R.id.amperage);*/


    btn_calculation = findViewById(R.id.btn_calculation);

    //editTextToInt(potential);
    btn_calculation.setOnClickListener(view -> {
      getValueSpinnerVoltage();
      int varPot = editTextToInt(potency);
      System.out.println("\n\n Not Empty... -> " + varPot + " \n\n");
    });
  }
}