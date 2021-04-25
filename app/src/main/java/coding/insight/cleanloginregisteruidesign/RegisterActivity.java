package coding.insight.cleanloginregisteruidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
   EditText editTextName,editTextEmail,editTextRegPassword,editTextCPassword;
   Button btnRegister;
   String userUID;

   private FirebaseAuth mAuth;
   private ProgressDialog mLodingBar;

   FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextCPassword=findViewById(R.id.editTextCPassword);
        editTextEmail=findViewById(R.id.editTextEmailLog);
        editTextName=findViewById(R.id.editTextName);
        editTextRegPassword=findViewById(R.id.editTextRegPassword);

        btnRegister=findViewById(R.id.cirRegisterButton);




        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();




        mLodingBar=new ProgressDialog(RegisterActivity.this);





       // checkCredential();
        changeStatusBarColor();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredential();


            }
        });
    }

    private void checkCredential() {
        String name=editTextName.getText().toString();
        final String email=editTextEmail.getText().toString();
        String pass=editTextRegPassword.getText().toString();
        String cPass=editTextCPassword.getText().toString();



        if(name.isEmpty() ||  name.length()<4 || !name.toString().matches("[a-zA-Z][a-zA-Z ]+[a-zA-Z ]$"))
            showError(editTextName,"Enter Valid Name");
        if(email.isEmpty() || !email.contains("@") || email.length()<7)
            showError(editTextEmail,"Enter Valid Email");
        if(pass.isEmpty() || pass.length()<6)
            showError(editTextRegPassword,"Password must contains atleast 6 characters");
        if(cPass.isEmpty() || !pass.equals(cPass))
            showError(editTextCPassword,"Password didn't match");
        else {
            //startActivity(new Intent(this,DashboardActivity.class));
            mLodingBar.setTitle("Registration");
            mLodingBar.setMessage("Please wait,checking your credential");
            mLodingBar.setCanceledOnTouchOutside(false);
            mLodingBar.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String name=editTextName.getText().toString();
                        final String email=editTextEmail.getText().toString();
                        String pass=editTextRegPassword.getText().toString();
                        String cPass=editTextCPassword.getText().toString();



                        Intent intent=new Intent(RegisterActivity.this,DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        userUID=mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference=fStore.collection("All Users").document(userUID);
                        Map<String,Object> user=new HashMap<>();
                        user.put("Name",name);
                        user.put("Email",email);
                        user.put("Password",pass);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegisterActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                            }
                        });


                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(RegisterActivity.this,"Some Error Occured"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void showError(EditText input, String s) {
       input.setError(s);
       input.requestFocus();
    }

    public void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));

        }
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }
}