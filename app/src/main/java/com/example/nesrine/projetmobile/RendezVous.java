package com.example.nesrine.projetmobile;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RendezVous extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText inputNom,inputPrenom,inputCodePostal,inputPhone, inputEmail;
    private TextInputLayout inputLayoutNom,inputLayoutPrenom,inputLayoutCodePostal,inputLayoutPhone, inputLayoutEmail;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);
        Spinner spinner = (Spinner) findViewById(R.id.civil_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.civil_array, R.layout.spinner_item_2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vos coordonnées");
        inputNom= (EditText) findViewById(R.id.input_nom);
        inputPrenom = (EditText) findViewById(R.id.input_prenom);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPhone= (EditText) findViewById(R.id.phone);
        inputCodePostal= (EditText) findViewById(R.id.code_postal);
        inputLayoutNom = (TextInputLayout) findViewById(R.id.input_layout_nom);
        inputLayoutPrenom = (TextInputLayout) findViewById(R.id.input_layout_prenom);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutCodePostal = (TextInputLayout) findViewById(R.id.input_layout_code_postal);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        btnValider = (Button) findViewById(R.id.btn_valider);
        inputNom.addTextChangedListener(new MyTextWatcher(inputNom));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPrenom.addTextChangedListener(new MyTextWatcher(inputPrenom));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputCodePostal.addTextChangedListener(new MyTextWatcher(inputCodePostal));
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }
    private void submitForm() {
        if (!validateNom()) {
            return;
        }
        if (!validatePrenom()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }
        if (!validateCodePostal()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Votre rendez-vous est enregisté!", Toast.LENGTH_SHORT).show();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validateNom() {
        if (inputNom.getText().toString().trim().isEmpty()) {
            inputLayoutNom.setError(getString(R.string.err_msg_nom));
            requestFocus(inputNom);
            return false;
        } else {
            inputLayoutNom.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePrenom() {
        if (inputPrenom.getText().toString().trim().isEmpty()) {
            inputLayoutPrenom.setError(getString(R.string.err_msg_prenom));
            requestFocus(inputPrenom);
            return false;
        } else {
            inputLayoutPrenom.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateCodePostal() {
        if (inputCodePostal.getText().toString().trim().isEmpty()) {
            inputLayoutCodePostal.setError(getString(R.string.err_msg_codepostal));
            requestFocus(inputCodePostal);
            return false;
        } else {
            inputLayoutCodePostal.setErrorEnabled(false);
        }

        return true;
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_nom:
                    validateNom();
                    break;
                case R.id.input_prenom:
                    validatePrenom();
                    break;
                case R.id.input_layout_phone:
                    validatePhone();
                    break;
                case R.id.input_layout_code_postal:
                    validateCodePostal();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



}
