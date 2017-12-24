package in.notedown.mi;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ShowNoteActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shownote );

        imageView = (ImageView)findViewById( R.id.show_note_img);
        String s = getIntent().getStringExtra( "IP" );

        Glide.with( this.getBaseContext() ).load( s ).into( imageView );
    }
}
