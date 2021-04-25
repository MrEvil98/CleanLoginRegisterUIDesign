package coding.insight.cleanloginregisteruidesign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class AttendUpdateActivity extends AppCompatActivity {

    Button enrollChange,passChange,btnUpdate;
    EditText edtTextEnroll,edtTextpass;
    TextView universityupdate,courseupdate,branchupdate,yearupdate;




    DocumentReference userData;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String userUID=FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_attend_update);

        btnUpdate=findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);

        enrollChange=findViewById(R.id.enrollChange);
        passChange=findViewById(R.id.passChange);
        edtTextEnroll=findViewById(R.id.edtTextEnroll);
        edtTextpass=findViewById(R.id.edtTextpass);
        edtTextpass.setEnabled(false);
        edtTextEnroll.setEnabled(false);
        universityupdate=findViewById(R.id.spinUniversityupdate);
        courseupdate=findViewById(R.id.spinCourseupdate);
        branchupdate=findViewById(R.id.spinBranchupdate);
        yearupdate=findViewById(R.id.spinYearupdate);



        databaseReference= FirebaseDatabase.getInstance().getReference();

        userData=db.collection("All Users").document(userUID).collection("Services").document("AutoAttendance");

        userData.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                universityupdate.setText(value.getString("University"));
                courseupdate.setText(value.getString("Course"));
                branchupdate.setText(value.getString("Branch"));
                yearupdate.setText(value.getString("Year"));
                edtTextEnroll.setText(value.getString("Enrollment"));
                edtTextpass.setText(value.getString("ILI Password"));
            }
        });



        enrollChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTextEnroll.setEnabled(true);
                btnUpdate.setEnabled(true);

            }
        });
        passChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTextpass.setEnabled(true);
                btnUpdate.setEnabled(true);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enroll = edtTextEnroll.getText().toString().trim();
                String pass = edtTextpass.getText().toString();

                String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("All Users").document(key).collection("Services").document("AutoAttendance");

                String university = universityupdate.getText().toString().trim();
                String course = courseupdate.getText().toString().trim();
                String branch = branchupdate.getText().toString().trim();
                String year = yearupdate.getText().toString().trim();

                studentDetails std = new studentDetails(enroll, pass);


                if (enroll.length() < 10)
                    Toast.makeText(AttendUpdateActivity.this, "Enter Complete Enrollment Numbers", Toast.LENGTH_SHORT).show();
                if (!pass.matches("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$"))
                    Toast.makeText(AttendUpdateActivity.this, "Enter Correct ILI Password (Not IUSMS Password", Toast.LENGTH_SHORT).show();
                else
                {

                    databaseReference.child(university).child(course).child(branch).child(year).child(key).setValue(std);


                HashMap hashMap = new HashMap();
                hashMap.put("Enrollment", enroll);
                hashMap.put("ILI Password", pass);

                documentReference.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(AttendUpdateActivity.this, "Updated...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AttendUpdateActivity.this, AttendUpdateActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AttendUpdateActivity.this, "Failed to Update...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            }
        });


    }
}