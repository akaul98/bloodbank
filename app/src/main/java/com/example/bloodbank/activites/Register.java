package com.example.bloodbank.activites;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;
import com.example.bloodbank.utils.VolleySingleton;
import com.example.bloodbank.utils.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.example.bloodbank.R.layout.activity_register2;

public class Register extends AppCompatActivity {
    private  EditText nameEt,cityEt, emailEt,bloodgroupEt,phoneEt,passwordEt,confirmpasswordEt;
    private  Button submitEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register2);
        nameEt= findViewById(R.id.Name);
        cityEt= findViewById(R.id.City);
        emailEt= findViewById(R.id.email);
        bloodgroupEt= findViewById(R.id.bloodgroup);
        phoneEt= findViewById(R.id.Phone);
        passwordEt= findViewById(R.id.password);
        confirmpasswordEt=findViewById(R.id.confirm_password);
        submitEt=findViewById(R.id.submit);
        submitEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,city,email,bloodgroup,phone,password,confirmpassword;
                name= nameEt.getText().toString();
                city= cityEt.getText().toString();
                email= emailEt.getText().toString();
                bloodgroup=bloodgroupEt.getText ().toString();
                phone= phoneEt.getText().toString();
                password= passwordEt.getText().toString();
                confirmpassword= confirmpasswordEt.getText().toString();
                   if (isvalid(name,city,email,bloodgroup,phone,password,confirmpassword)){
                       register(name,city,email,bloodgroup,phone,password);
                }

            }
        });

    }
    private void register(final String name,final String city, final String email,final String bloodgroup,final String phone,final String password){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, endpoint.register_url,
                    new Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           if(response.equals("Success")){
                                Toast.makeText(Register.this,response,Toast.LENGTH_SHORT).show();
                               Intent reg= new Intent(Register.this,activity_login.class);
                               startActivity(reg);
                                //startActivity(new Intent(Register.this, activity_login.class));
                                //startActivity(new Intent(Register.this,MainActivity.class));
                                Register.this.finish();
                            }else{
                                Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                    Log.d("VOLLEY", error.getMessage());
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("city", city);
                        params.put("email",email);
                        params.put("bloodgroup", bloodgroup);
                        params.put("phone", phone);
                        params.put("password", password);

                        return params;
                }
            };
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }






    private boolean isvalid(String name, String city, String email,String bloodgroup,String phone,String password,String confirmpassword){
        List<String> valid_bloodgroup= new ArrayList<>();
        valid_bloodgroup.add("A+");
        valid_bloodgroup.add("A-");
        valid_bloodgroup.add("B+");
        valid_bloodgroup.add("B-");
        valid_bloodgroup.add("AB+");
        valid_bloodgroup.add("AB-");
        valid_bloodgroup.add("O+");
        valid_bloodgroup.add("O-");

        if (name.isEmpty()){
            showmessage("Name is empty");
            return false;
        }
        else if (city.isEmpty()){
            showmessage("City is empty");
            return false;
        }




        else if(!valid_bloodgroup.contains(bloodgroup)){
            showmessage(" Blood group invalid choose from"+valid_bloodgroup);
            return false;
        }
        else if(phone.isEmpty() || phone.length()!=10){
            showmessage("Phone number  is not valid");
            return false;
        }
        else if (password.isEmpty()){
            showmessage("password  is empty");
            return false;
        }
       else if (!confirmpassword.equals(password)){
            showmessage("passwords to not match");
            return false;
        }


    return true;
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    private void showmessage(String msg){
        Toast.makeText(Register.this,msg,Toast.LENGTH_SHORT).show();

    }
}