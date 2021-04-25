package coding.insight.cleanloginregisteruidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    ImageView back;
    Button next;
    private FirebaseAuth mAuth;
    TextInputEditText inMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        back=findViewById(R.id.forget_password_back_btn);
        next=findViewById(R.id.forget_password_next_btn);
        inMail=findViewById(R.id.inMail);

        mAuth=FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=inMail.getText().toString().trim();

                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ForgetPasswordActivity.this, "Email Sent Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgetPasswordActivity.this,EmailSentActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ForgetPasswordActivity.this, "Error ! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }

}