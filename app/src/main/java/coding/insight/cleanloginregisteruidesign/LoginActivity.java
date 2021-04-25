package coding.insight.cleanloginregisteruidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmailLog,editTextPassword;
    Button btnLogin;
    FirebaseAuth mAuth;
    TextView resetpass;

    private ProgressDialog mLodingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        setContentView(R.layout.activity_login);

        editTextEmailLog=findViewById(R.id.editTextEmailLog);
        editTextPassword=findViewById(R.id.editTextPassword);
        btnLogin=findViewById(R.id.cirLoginButton);
        resetpass=findViewById(R.id.resetpassword);
        mAuth=FirebaseAuth.getInstance();
      FirebaseUser user=mAuth.getCurrentUser();

        mLodingBar=new ProgressDialog(LoginActivity.this);

       if(user !=null)
       {
           Intent inten=new Intent(this,DashboardActivity.class);
           inten.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(inten);
           finish();
       }


       resetpass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
               finish();
           }
       });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String   email=editTextEmailLog.getText().toString().trim();
              String  pass=editTextPassword.getText().toString().trim();
                if(email.isEmpty() || !email.contains("@") || email.length()<7)
                    showError(editTextEmailLog,"Enter Valid Email");
                if(pass.isEmpty() || pass.length()<6)
                    showError(editTextPassword,"Password must contains atleast 6 characters");
                else {

                    mLodingBar.setTitle("Logging In");
                    mLodingBar.setMessage("Please wait,checking your credential");
                    mLodingBar.setCanceledOnTouchOutside(false);
                    mLodingBar.show();

                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                finish();
                            } else
                                Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void onLoginClick(View View)
    {

        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        finish();
    }
}