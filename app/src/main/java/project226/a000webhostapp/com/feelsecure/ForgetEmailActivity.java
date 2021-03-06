package project226.a000webhostapp.com.feelsecure;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetEmailActivity extends AppCompatActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_email);

    }
    public void SubmitClicked(View v){
        EditText emailEdit =(EditText)findViewById(R.id.emailReset);
        String emailS =emailEdit.getText().toString();

        Intent i=new Intent(ForgetEmailActivity.this,forgetPassword.class);
        i.putExtra("email",emailS);
        ForgetEmailActivity.this.startActivity(i);
    }*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_email);

        final EditText emailF = (EditText) findViewById(R.id.emailReset);

        final Button submitF = (Button) findViewById(R.id.submitEmailResetF);
        submitF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailF.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(ForgetEmailActivity.this, ForgetPassword.class);
                                intent.putExtra("email", email);
                                ForgetEmailActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgetEmailActivity.this);
                                builder.setMessage("Email not registered")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ForgetEmailRequest forgetEmailRequest=new ForgetEmailRequest(email,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgetEmailActivity.this);
                queue.add(forgetEmailRequest);

            }
        });
    }

}
