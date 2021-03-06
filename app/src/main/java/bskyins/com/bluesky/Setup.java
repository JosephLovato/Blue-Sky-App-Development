package bskyins.com.bluesky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Setup extends AppCompatActivity {
    //Declare sharedPreferences object
    SharedPreferences sharedpreferences;

    //Declare all three EditText Objects
    EditText companyName;
    EditText contactName;
    EditText email;
    EditText agencyEmail;

    //Declare all text keys for shared preferences strings
    public static final String mypreference = "mypref";
    public static final String CompanyName = "insuredCompanyNameKey";
    public static final String ContactName = "insuredContactNameKey";
    public static final String Email = "insuredEmailKey";
    public static final String AgencyEmail = "agencyEmailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);

        //Action Bar Setup
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        //set all Edit Text Objects to their respective Edit Text xml objects
        companyName = (EditText) findViewById(R.id.setup_insured_company_name);
        contactName = (EditText) findViewById(R.id.setup_insured_contact_name);
        email = (EditText) findViewById(R.id.setup_insured_email);
        agencyEmail = (EditText) findViewById(R.id.setup_agency_email);

        //set up sharedPreferences to "mypref" and mode Private
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        //If preferences have been saved before, load them into the EditText objects
        if (sharedpreferences.contains(CompanyName)) {
            companyName.setText(sharedpreferences.getString(CompanyName, ""));
        }
        if (sharedpreferences.contains(ContactName)) {
            contactName.setText(sharedpreferences.getString(ContactName, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));
        }
        if(sharedpreferences.contains(AgencyEmail)) {
            agencyEmail.setText(sharedpreferences.getString(AgencyEmail, ""));
        }
    }

    //save information when user pressed save button, then send them back to the Main Activity
    public void saveOnButtonClick(View view) {
        //save user information
        String cpn = companyName.getText().toString();
        String ctn = contactName.getText().toString();
        String e = email.getText().toString();
        String ae = agencyEmail.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(CompanyName, cpn);
        editor.putString(ContactName, ctn);
        editor.putString(Email, e);
        editor.putString(AgencyEmail, ae);
        editor.commit();

        //Toast for user to be sure information is saved
        Context context = getApplicationContext();
        CharSequence text = "Your information has been saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast autoToast = Toast.makeText(context, text, duration);
        autoToast.show();

        //sends user to Main Screen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
