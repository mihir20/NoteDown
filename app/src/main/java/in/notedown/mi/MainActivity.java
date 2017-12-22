package in.notedown.mi;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static FloatingActionButton fab;
    FragmentManager  fragmentManager = getSupportFragmentManager() ;
    HomeFragment homeFragment = new HomeFragment();
    WritingFragment writingFragment = new WritingFragment();

    @Override
    public void onBackPressed() {
        if(homeFragment.isVisible()){
        super.onBackPressed();}
        else if (writingFragment.isVisible()){
            fragmentManager.beginTransaction().replace( R.id.fragment_container,homeFragment )
            .commit();}
        else {super.onBackPressed();}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        fragmentManager.beginTransaction()
                .replace( R.id.fragment_container, homeFragment ,"HOME" )
                .commit();

        //setting click listener on fab
        fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace( R.id.fragment_container, writingFragment ,"WRITING")
                        .commit();

                fab.setVisibility( View.GONE );
            }
        } );

    }

}
