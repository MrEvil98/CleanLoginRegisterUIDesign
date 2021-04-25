package coding.insight.cleanloginregisteruidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendActivity extends AppCompatActivity {
    private Spinner spinUniversity,spinCourse,spinBranch,spinYear;
    Button btnEnroll;
    DatabaseReference database;
    EditText editTextEnroll,editTextilipass;
    FirebaseFirestore fStore;

    ArrayList<String> array_Course,array_uni,array_year;
    ArrayAdapter<String> arrayAdapter_Course,arrayAdapter_uni,arrayAdapter_year;

    ArrayList<String> array_bot,array_dip,array_bopth,array_bos,array_bop,array_boc,array_sel;
    ArrayAdapter<String> arrayAdapter_branch;

    private ProgressDialog mLodingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        spinUniversity=findViewById(R.id.spinUniversity);
        spinCourse=findViewById(R.id.spinCourse);
        spinBranch=findViewById(R.id.spinBranch);
        spinYear=findViewById(R.id.spinYear);
        btnEnroll=findViewById(R.id.btnUpdate);


        fStore=FirebaseFirestore.getInstance();


        mLodingBar=new ProgressDialog(AttendActivity.this);


        array_uni=new ArrayList<>();
        array_uni.add("Select University/College");
        array_uni.add("Integral University");
        array_uni.add("Galgotias University");
        array_uni.add("LPU University");

        array_year=new ArrayList<>();
        array_year.add("Select Year");
        array_year.add("First Year");
        array_year.add("Second Year");
        array_year.add("Third Year");
        array_year.add("Fourth Year");
        array_year.add("Fifth Year");

        array_Course=new ArrayList<>();
        array_Course.add("Select Course");
        array_Course.add("Bachelor of Technology");
        array_Course.add("Diploma");
        array_Course.add("Bachelor of Physiotherapy");
        array_Course.add("Bachelor of Science");
        array_Course.add("Bachelor of Pharmacy");
        array_Course.add("Bachelor of Computer Application");

        arrayAdapter_Course=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_Course);

        arrayAdapter_Course.setDropDownViewResource(R.layout.custom_spinner_dropdown);

        spinCourse.setAdapter(arrayAdapter_Course);



        //===============branch spinner start ===========================
        array_sel=new ArrayList<>();
        array_sel.add("Select Branch");


        array_bot=new ArrayList<>();
        array_bot.add("Select Branch");
        array_bot.add("CSE-1");
        array_bot.add("CSE-2");
        array_bot.add("CSE-3");
        array_bot.add("Electical Engg");
        array_bot.add("Civil Engg");
        array_bot.add("Mechanical Engg");
        array_bot.add("Electronics Engg");
        array_bot.add("Agriculture Engg");
        array_bot.add("Biotechnology");

        array_dip=new ArrayList<>();
        array_dip.add("Select Branch");
        array_dip.add("Architecture");
        array_dip.add("CSE");
        array_dip.add("Electrical");
        array_dip.add("Civil");
        array_dip.add("Mechanical");
        array_dip.add("Electronics");
        array_dip.add("Pharmacy");

        array_bopth=new ArrayList<>();
        array_bopth.add("Select Branch");
        array_bopth.add("Bachelor of Physiotherapy");

        array_bos=new ArrayList<>();
        array_bos.add("Select Branch");
        array_bos.add("Agriculture");
        array_bos.add("Computer Science");
        array_bos.add("Physics");
        array_bos.add("Chemistry");
        array_bos.add("Maths");

        array_bop=new ArrayList<>();
        array_bop.add("Select Branch");
        array_bop.add("Bachelor of Pharmacy");

        array_boc=new ArrayList<>();
        array_boc.add("Select Branch");
        array_boc.add("Bachelor of Computer Application");


        spinCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_sel);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }
                if (position==1)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bot);
                    spinBranch.setAdapter(arrayAdapter_branch);

                }
                if (position==2)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_dip);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }
                if (position==3)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bopth);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }
                if (position==4)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bos);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }
                if (position==5)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bop);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }
                if (position==6)
                {
                    arrayAdapter_branch=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_boc);
                    spinBranch.setAdapter(arrayAdapter_branch);
                }


                arrayAdapter_branch.setDropDownViewResource(R.layout.custom_spinner_dropdown);


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //===============branch spinner ends ============================


        //========================== University And Year Spinner start =======================
        arrayAdapter_uni=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_uni);

        arrayAdapter_uni.setDropDownViewResource(R.layout.custom_spinner_dropdown);

        spinUniversity.setAdapter(arrayAdapter_uni);

        arrayAdapter_year=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_year);

        arrayAdapter_year.setDropDownViewResource(R.layout.custom_spinner_dropdown);

        spinYear.setAdapter(arrayAdapter_year);
        //========================== University And Year Spinner Ends ========================


        database= FirebaseDatabase.getInstance().getReference();

        editTextEnroll=findViewById(R.id.edtTextEnroll);
        editTextilipass=findViewById(R.id.edtTextpass);


        //String key=user.getUid();


        btnEnroll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                String enroll=editTextEnroll.getText().toString();
                String pass=editTextilipass.getText().toString();
                String  university= spinUniversity.getSelectedItem().toString();
                String course = spinCourse.getSelectedItem().toString();
                String branch= spinBranch.getSelectedItem().toString();
                String year= spinYear.getSelectedItem().toString();

                //String sz= database.child("size").get().toString();

                if(university.equals("Select University/College"))
                    Toast.makeText(getApplicationContext(),"Select University", Toast.LENGTH_LONG).show();
                else if(course.equals("Select Course"))
                    Toast.makeText(getApplicationContext(),"Select Course", Toast.LENGTH_LONG).show();
                else if(branch.equals("Select Branch"))
                    Toast.makeText(getApplicationContext(),"Select Branch", Toast.LENGTH_LONG).show();
                else if(year.equals("Select Year"))
                    Toast.makeText(getApplicationContext(),"Select Year", Toast.LENGTH_LONG).show();
                else if(enroll.length()<10)
                    Toast.makeText(getApplicationContext(),"Enter Correct Enrollment number", Toast.LENGTH_LONG).show();
                else if(!pass.matches("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$"))
                    Toast.makeText(getApplicationContext(),"Enter Correct ILI Password (Not IUSMS Password)", Toast.LENGTH_LONG).show();

                else
                   {

                       mLodingBar.setTitle("Enrolling");
                       mLodingBar.setMessage("Please wait,checking your credential");
                       mLodingBar.setCanceledOnTouchOutside(false);
                       mLodingBar.show();


                              String key=FirebaseAuth.getInstance().getCurrentUser().getUid();

                       studentDetails std=new studentDetails(enroll,pass);




                       DocumentReference documentReference=fStore.collection("All Users").document(key).collection("Services").document("AutoAttendance");
                       Map<String,Object> user=new HashMap<>();
                       user.put("University",university);
                       user.put("Course",course);
                       user.put("Branch",branch);
                       user.put("Year",year);
                       user.put("Enrollment",enroll);
                       user.put("ILI Password",pass);
                       documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(AttendActivity.this,"Successfully Enrolled...",Toast.LENGTH_SHORT).show();
                           }
                       });

                    database.child(university).child(course).child(branch).child(year).child(key).setValue(std);

                    startActivity(new Intent(AttendActivity.this,AttendUpdateActivity.class));
                    finish();

                }
            }
        });



    }
    }


