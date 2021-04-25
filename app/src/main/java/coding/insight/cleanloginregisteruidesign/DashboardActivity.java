package coding.insight.cleanloginregisteruidesign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //navigation vars
    ImageView naviIcon,auto_icon;
    CardView auto_card,notes_card;
    LinearLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView nameInNavigation1,emailInNavigation1;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseFirestore fStore;
    DocumentReference userData;
    String userUID;

    static final float END_SCALE = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Navi hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        naviIcon = findViewById(R.id.nav_icon);
        auto_icon=findViewById(R.id.auto_icon);
        contentView=findViewById(R.id.content);
        nameInNavigation1=findViewById(R.id.nameInNavigation);
        emailInNavigation1=findViewById(R.id.emailInNavigation);

        mAuth=FirebaseAuth.getInstance();

        //dashboard hooks
        auto_card=findViewById(R.id.auto_card);
        notes_card=findViewById(R.id.Notes_card);



        navigationDrawer();
         userUID=mAuth.getCurrentUser().getUid();

        databaseReference= FirebaseDatabase.getInstance().getReference();

        userData=fStore.getInstance().collection("All Users").document(userUID);

        userData.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               TextView showUserName1=findViewById(R.id.showUserName);
                String[] fullName=value.getString("Name").split(" ");
                showUserName1.setText(fullName[0]);
                //nameInNavigation1.setText(value.getString("Name"));
                //emailInNavigation1.setText(value.getString("Email"));
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView nav_name = (TextView) headerView.findViewById(R.id.nameInNavigation);
                nav_name.setText(fullName[0]+" "+fullName[1]);
                TextView nav_email = (TextView) headerView.findViewById(R.id.emailInNavigation);
                nav_email.setText(value.getString("Email"));
            }
        });


        auto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore rootRef = FirebaseFirestore.getInstance();

                DocumentReference docIdRef = rootRef.collection("All Users").document(userUID).collection("Services").document("AutoAttendance");
                docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                startActivity(new Intent(DashboardActivity.this,AttendUpdateActivity.class));
                            }
                            else {

                                startActivity(new Intent(DashboardActivity.this,AttendActivity.class));

                            }
                        }
                    }
                });

                    }



            });


        notes_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,ClassNotesActivity.class));
            }
        });

        //dashboard clickable ends

    }

    //for closing navigation with back button
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    private void navigationDrawer() {

        //navi drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        naviIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    //animating navigation drawer

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_logout){
            Toast.makeText(DashboardActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            startActivity(new Intent(this,LoginActivity.class));
            finish();

        }

        return true;

    }


}