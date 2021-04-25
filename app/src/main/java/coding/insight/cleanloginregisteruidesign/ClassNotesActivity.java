package coding.insight.cleanloginregisteruidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ClassNotesActivity extends AppCompatActivity {
    private Spinner spinUniversitydown,spinCoursedown,spinBranchdown,spinYeardown,spinSemdown,spinSubdown;

        ArrayList<String> array_Course_down,array_uni_down,array_year_down,array_sem_down;
        ArrayAdapter<String> arrayAdapter_Course_down,arrayAdapter_uni_down,arrayAdapter_year_down,arrayAdapter_sem_down;

        ArrayList<String> array_bot_down,array_sel2_down;
        ArrayAdapter<String> arrayAdapter_branch_down;

        ArrayList<String> array_sel1_down,First_sem_CSEsubject,Second_sem_CSEsubject,Third_sem_CSEsubject,Fourth_sem_CSEsubject,Fifth_sem_CSEsubject,Sixth_sem_CSEsubject;
        ArrayAdapter<String> arrayAdapter_sub_down;

        Button download;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_class_notes);

            spinUniversitydown=findViewById(R.id.spinUniversitydown);
            spinCoursedown=findViewById(R.id.spinCoursedown);
            spinBranchdown=findViewById(R.id.spinBranchdown);
            spinYeardown=findViewById(R.id.spinYeardown);
            spinSemdown=findViewById(R.id.spinSemdown);
            spinSubdown=findViewById(R.id.spinSubjectdown);

            download=findViewById(R.id.download_notes);


            array_uni_down=new ArrayList<>();
            array_uni_down.add("Select University/College");
            array_uni_down.add("Integral University");

            array_year_down=new ArrayList<>();
            array_year_down.add("Select Year");
            array_year_down.add("First Year");
            array_year_down.add("Second Year");
            array_year_down.add("Third Year");
            array_year_down.add("Fourth Year");
            array_year_down.add("Fifth Year");

            array_Course_down=new ArrayList<>();
            array_Course_down.add("Select Course");
            array_Course_down.add("Bachelor of Technology");

            array_sem_down=new ArrayList<>();
            array_sem_down.add("Select Semester");
            array_sem_down.add("First Semester");
            array_sem_down.add("Second Semester");
            array_sem_down.add("Third Semester");
            array_sem_down.add("Fourth Semester");
            array_sem_down.add("Fift Semester");
            array_sem_down.add("Sixth Semester");



            arrayAdapter_Course_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_Course_down);

            arrayAdapter_Course_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);

            spinCoursedown.setAdapter(arrayAdapter_Course_down);



            //===============branch spinner start ===========================
            array_sel2_down=new ArrayList<>();
            array_sel2_down.add("Select Branch");


            array_bot_down=new ArrayList<>();
            array_bot_down.add("Select Branch");
            array_bot_down.add("CSE");



            spinCoursedown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0)
                    {
                        arrayAdapter_branch_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_sel2_down);
                        spinBranchdown.setAdapter(arrayAdapter_branch_down);
                    }
                    if (position==1)
                    {
                        arrayAdapter_branch_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bot_down);
                        spinBranchdown.setAdapter(arrayAdapter_branch_down);

                    }

                    arrayAdapter_branch_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);


                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //===============branch spinner ends ============================


            //========================== University And Year Spinner start =======================
            arrayAdapter_uni_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_uni_down);

            arrayAdapter_uni_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);

            spinUniversitydown.setAdapter(arrayAdapter_uni_down);

            arrayAdapter_year_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_year_down);

            arrayAdapter_year_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);

            spinYeardown.setAdapter(arrayAdapter_year_down);
            //========================== University And Year Spinner Ends ========================


            arrayAdapter_sem_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_sem_down);

            arrayAdapter_sem_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);

            spinSemdown.setAdapter(arrayAdapter_sem_down);


            //===================Subject Spinner Start ==================

            array_sel1_down=new ArrayList<>();
            array_sel1_down.add("Select Subject");

            First_sem_CSEsubject=new ArrayList<>();
            First_sem_CSEsubject.add("Select Subject");
            First_sem_CSEsubject.add("EC-101 Basic Electronics");
            First_sem_CSEsubject.add("PY-101 Engineering Physics");



            Second_sem_CSEsubject=new ArrayList<>();
            Second_sem_CSEsubject.add("Select Subject");
            Second_sem_CSEsubject.add("CH-101 Engineering Chemistry");
            Second_sem_CSEsubject.add("CS-101 Computer Programming C");
            Second_sem_CSEsubject.add("ES-101 Environmental Studies");
            Second_sem_CSEsubject.add("ME-101 Basic Mechanical Engineering");
            Second_sem_CSEsubject.add("MT-112 Mathematics 2");



            Third_sem_CSEsubject=new ArrayList<>();
            Third_sem_CSEsubject.add("Select Subject");
            Third_sem_CSEsubject.add("BM-225 Principles of Managements");
            Third_sem_CSEsubject.add("BM-226 Human Values and Professional Ethics");
            Third_sem_CSEsubject.add("CS-203 Cyber Law and Information Security");
            Third_sem_CSEsubject.add("CS-204 Data Structures using C");
            Third_sem_CSEsubject.add("CS-205 Object Oriented Programming and C++");
            Third_sem_CSEsubject.add("CS-207 Computer Graphics");
            Third_sem_CSEsubject.add("CS-209 Web Technology");
            Third_sem_CSEsubject.add("EC-209 Digital Electronics");




            Fourth_sem_CSEsubject=new ArrayList<>();
            Fourth_sem_CSEsubject.add("Select Subject");
            Fourth_sem_CSEsubject.add("CS-211 Software Engineering");
            Fourth_sem_CSEsubject.add("CS-212 DBMS");
            Fourth_sem_CSEsubject.add("CS-213 Computer Organization");
            Fourth_sem_CSEsubject.add("CS-214 Advanced Computer Programming");
            Fourth_sem_CSEsubject.add("CS-215 Core Java");
            Fourth_sem_CSEsubject.add("ES-202-Disaster Management");
            Fourth_sem_CSEsubject.add("MT-206 Mathematical Analysis");



            Fifth_sem_CSEsubject=new ArrayList<>();
            Fifth_sem_CSEsubject.add("Select Subject");
            Fifth_sem_CSEsubject.add("CS-301 DAA");
            Fifth_sem_CSEsubject.add("CS-303 OS");
            Fifth_sem_CSEsubject.add("CS-304 Automata");
            Fifth_sem_CSEsubject.add("CS-305 Computer Networks");
            Fifth_sem_CSEsubject.add("CS-309 Open Source");
            Fifth_sem_CSEsubject.add("CS-311 SPQM");
            Fifth_sem_CSEsubject.add("CS-312 Digital Image Processing");
            Fifth_sem_CSEsubject.add("DAA Lab");



            Sixth_sem_CSEsubject=new ArrayList<>();
            Sixth_sem_CSEsubject.add("Select Subject");
            Sixth_sem_CSEsubject.add("CS-313 Microprocessor and its Applications");
            Sixth_sem_CSEsubject.add("CS-315 Compiler Design");
            Sixth_sem_CSEsubject.add("CS-317 Articial Intelligence");
            Sixth_sem_CSEsubject.add("CS-332 Visual Programming and Techniques");
            Sixth_sem_CSEsubject.add("CS-333 Graph Theory");
            Sixth_sem_CSEsubject.add("CS-334 Cloud Computing");


            spinSemdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(position==0)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_sel1_down);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==1)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,First_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==2)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,Second_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==3)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,Third_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==4)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,Fourth_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==5)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,Fifth_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    if(position==6)
                    {
                        arrayAdapter_sub_down=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,Sixth_sem_CSEsubject);
                        spinSubdown.setAdapter(arrayAdapter_sub_down);
                    }

                    arrayAdapter_sub_down.setDropDownViewResource(R.layout.custom_spinner_dropdown);

                }



                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //===================Subject Spinner Ends ==================



            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Basic_Electronics_url = "https://drive.google.com/drive/u/1/folders/1tGLTCeu9kRS1N4CkAdAGzdEifjXz6Eyk";
                    String Physics_url="https://drive.google.com/drive/u/1/folders/1YXNKKUUU5JLN49lf1vVEJnOorDjfFnPp";
                    String EngineeringChemistry_url="https://drive.google.com/drive/u/1/folders/1h6QN7ATPrm6QVxgW2Xga-0Wrg3en7qYL";
                    String ComputerProgramming_url="https://drive.google.com/drive/u/1/folders/1M1H2Gn2JWNAkuplKz6pzR_7nDFOZ0bIr";
                    String EnvironmentalStudies_url="https://drive.google.com/drive/u/1/folders/1tgZ3sMMyX0PfO-0L5YF0ix6NA8NoXgN_";
                    String BasicMechanicalEngineering_url="https://drive.google.com/drive/u/1/folders/1uP7a0XBNGZj9AdsjtRF5sLpnIWOkQMij";
                    String Mathematics_2_url="https://drive.google.com/drive/u/1/folders/1hE2jmD48Pg1xu-ytcAvBHmAH3MTDKy6f";
                    String PrinciplesofManagements_url="https://drive.google.com/drive/u/1/folders/10CvG_vLH00lJ6uApKEMpB5A4JTNql_i4";
                    String HumanValues_url="https://drive.google.com/drive/u/1/folders/1RxPobHUth1nnCmMW79xwWcMCIY07LeAF";
                    String CyberLaw_url="https://drive.google.com/drive/u/1/folders/1GloEswTdESu9jrowM8jyBwQCOQoGz_8m";
                    String DataStructures_url="https://drive.google.com/drive/u/1/folders/1pI0Tsg_TmcSDeZymA0pyBHQyAI461Vpb";
                    String ObjectOrientedProgramming_url="https://drive.google.com/drive/u/1/folders/1VNto_vZ0H-3RySg4ndoC7kYFH52MS727";
                    String ComputerGraphics_url="https://drive.google.com/drive/u/1/folders/1MTvOvczGGwwKCBldLOkBC4fH-im5QEUT";
                    String WebTechnology_url="https://drive.google.com/drive/u/1/folders/1w-C1bo5G2y3aO95FsZ1h37frxVn_LUwZ";
                    String DigitalElectronics_url="https://drive.google.com/drive/u/1/folders/1MOJpkskQZxU9XliMSnii69sT6DDpMlI0";
                    String SoftwareEngineering_url="https://drive.google.com/drive/u/1/folders/15WyQkmIs5xZPYnnMxM7Q_elIH4uDPnF5";
                    String DBMS_url="https://drive.google.com/drive/u/1/folders/1CIBD3GJsp-jr0_I2K1yaGKLzMAO61TA2";
                    String ComputerOrganization_url="https://drive.google.com/drive/u/1/folders/19Pnzn61fgZirC5NqZl8O3xSgLc4ZNLov";
                    String AdvancedComputerProgramming_url="https://drive.google.com/drive/u/1/folders/1rIeu-2zFsYoIhZwMo89hp8OR2LOi6J4H";
                    String CoreJava_url="https://drive.google.com/drive/u/1/folders/1r1l4xjRxDmtffEO2cmWEQAT4nZWuiDIy";
                    String DisasterManagement_url="https://drive.google.com/drive/u/1/folders/1CsLY-eNf2MZq7AR3U5weneu4-Xgveqrg";
                    String MathematicalAnalysis_url="https://drive.google.com/drive/u/1/folders/1RGludHtTsVULksdgrsO60ucafiE3nqIg";
                    String DAA_url="https://drive.google.com/drive/u/1/folders/10OJWxqXf5VnwCsne0AWX3TyRisf_bKf0";
                    String OS_url="https://drive.google.com/drive/u/1/folders/1yOVjy35s5bzcZoluXjpaY6T5ubDXoJ52";
                    String Automata_url="https://drive.google.com/drive/u/1/folders/1E3sauygBDlpVLtJ847nRp1YJGz6VMznZ";
                    String ComputerNetworks_url="https://drive.google.com/drive/u/1/folders/16x4JPqZdw5Ot5DZEW_srv6qQC1-nEDoQ";
                    String OpenSource_url="https://drive.google.com/drive/u/1/folders/1We_uhCtILjePckdtn-rJhxN8B3sRyNuC";
                    String SPQM_url="https://drive.google.com/drive/u/1/folders/1mTM3SwSasKrpxhywuYTmiWgc6V7QtYvO";
                    String DigitalImageProcessing_url="https://drive.google.com/drive/u/1/folders/19F_jjLo965HJWIuj4eZShVJm4q-9tlzV";
                    String DAALab_url="https://drive.google.com/drive/u/1/folders/1Czgm8xDg4uh5t0aVABY2b3YQWxo0gNqk";
                    String Microprocessor_url="https://drive.google.com/drive/u/1/folders/1SDH1XLnLcGiNmCJMIBO4yMMrDzXE7oBd";
                    String CompilerDesign_url="https://drive.google.com/drive/u/1/folders/18-rRA1B9tlLjHwoBVU8uu-6NqXRGdX_C";
                    String ArticialIntelligence_url="https://drive.google.com/drive/u/1/folders/10rF3THZiRA_gLN5HGoaxpQFlnVMka_O_";
                    String VisualProgrammingandTechniques_url="https://drive.google.com/drive/u/1/folders/1Xzit1k2l5uve95NpUqTPMUFhsYFYRo7d";
                    String GraphTheory_url="https://drive.google.com/drive/u/1/folders/1bkBrgcqsjY0lH_Oxj88N4wdYxEiXFzI7";
                    String CloudComputing_url="https://drive.google.com/drive/u/1/folders/1A5GXb-gL65occFe_pDvumjWiwK3fIiF8";

                    String sub=spinSubdown.getSelectedItem().toString().trim();


                    Intent i = new Intent(Intent.ACTION_VIEW);

                    if(sub.equals("EC-101 Basic Electronics"))
                        i.setData(Uri.parse(Basic_Electronics_url));
                    else if(sub.equals("PY-101 Engineering Physics"))
                        i.setData(Uri.parse(Physics_url));
                    else if(sub.equals("CH-101 Engineering Chemistry"))
                        i.setData(Uri.parse(EngineeringChemistry_url));
                    else if(sub.equals("CS-101 Computer Programming C"))
                        i.setData(Uri.parse(ComputerProgramming_url));
                    else if(sub.equals("ES-101 Environmental Studies"))
                        i.setData(Uri.parse(EnvironmentalStudies_url));
                    else if(sub.equals("ME-101 Basic Mechanical Engineering"))
                        i.setData(Uri.parse(BasicMechanicalEngineering_url));
                    else if(sub.equals("MT-112 Mathematics 2"))
                        i.setData(Uri.parse(Mathematics_2_url));
                    else if(sub.equals("BM-225 Principles of Managements"))
                        i.setData(Uri.parse(PrinciplesofManagements_url));
                    else if(sub.equals("BM-226 Human Values and Professional Ethics"))
                        i.setData(Uri.parse(HumanValues_url));
                    else if(sub.equals("CS-203 Cyber Law and Information Security"))
                        i.setData(Uri.parse(CyberLaw_url));
                    else if(sub.equals("CS-204 Data Structures using C"))
                        i.setData(Uri.parse(DataStructures_url));
                    else if(sub.equals("CS-205 Object Oriented Programming and C++"))
                        i.setData(Uri.parse(ObjectOrientedProgramming_url));
                    else if(sub.equals("CS-207 Computer Graphics"))
                        i.setData(Uri.parse(ComputerGraphics_url));
                    else if(sub.equals("CS-209 Web Technology"))
                        i.setData(Uri.parse(WebTechnology_url));
                    else if(sub.equals("EC-209 Digital Electronics"))
                        i.setData(Uri.parse(DigitalElectronics_url));

                    else if(sub.equals("CS-211 Software Engineering"))
                        i.setData(Uri.parse(SoftwareEngineering_url));

                    else if(sub.equals("CS-212 DBMS"))
                        i.setData(Uri.parse(DBMS_url));

                    else if(sub.equals("CS-213 Computer Organization"))
                        i.setData(Uri.parse(ComputerOrganization_url));

                    else if(sub.equals("CS-214 Advanced Computer Programming"))
                        i.setData(Uri.parse(AdvancedComputerProgramming_url));

                    else if(sub.equals("CS-215 Core Java"))
                        i.setData(Uri.parse(CoreJava_url));

                    else if(sub.equals("ES-202-Disaster Management"))
                        i.setData(Uri.parse(DisasterManagement_url));

                    else if(sub.equals("MT-206 Mathematical Analysis"))
                        i.setData(Uri.parse(MathematicalAnalysis_url));

                    else if(sub.equals("CS-301 DAA"))
                        i.setData(Uri.parse(DAA_url));

                    else if(sub.equals("CS-303 OS"))
                        i.setData(Uri.parse(OS_url));

                    else if(sub.equals("CS-304 Automata"))
                        i.setData(Uri.parse(Automata_url));

                    else if(sub.equals("CS-305 Computer Networks"))
                        i.setData(Uri.parse(ComputerNetworks_url));

                    else if(sub.equals("CS-309 Open Source"))
                        i.setData(Uri.parse(OpenSource_url));

                    else if(sub.equals("CS-311 SPQM"))
                        i.setData(Uri.parse(SPQM_url));

                    else if(sub.equals("CS-312 Digital Image Processing"))
                        i.setData(Uri.parse(DigitalImageProcessing_url));

                    else if(sub.equals("DAA Lab"))
                        i.setData(Uri.parse(DAALab_url));

                    else if(sub.equals("CS-313 Microprocessor and its Applications"))
                        i.setData(Uri.parse(Microprocessor_url));

                    else if(sub.equals("CS-315 Compiler Design"))
                        i.setData(Uri.parse(CompilerDesign_url));

                    else if(sub.equals("CS-317 Articial Intelligence"))
                        i.setData(Uri.parse(ArticialIntelligence_url));

                    else if(sub.equals("CS-332 Visual Programming and Techniques"))
                        i.setData(Uri.parse(VisualProgrammingandTechniques_url));

                    else if(sub.equals("CS-333 Graph Theory"))
                        i.setData(Uri.parse(GraphTheory_url));

                    else if(sub.equals("CS-334 Cloud Computing"))
                         i.setData(Uri.parse(CloudComputing_url));

                    startActivity(i);
                }
            });

        }
}
