package in.notedown.mi;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class WritingFragment extends Fragment {
    private static final int REQUEST_EXTERNAL_STAORGE= 1;
    private static String[] PERMISSION_STORAGE= {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public WritingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_writing, container, false );

        final SignaturePad writingPad =  rootView.findViewById( R.id.writingPad );
        FloatingActionButton eraseFAB =  rootView.findViewById( R.id.erase_fab );
        FloatingActionButton saveFAB =   rootView.findViewById( R.id.save_fab );

        eraseFAB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writingPad.clear();
            }
        } );

        saveFAB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bmp = writingPad.getSignatureBitmap();

                int permission = ActivityCompat.checkSelfPermission( getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE );

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( getActivity(),
                            PERMISSION_STORAGE,
                            REQUEST_EXTERNAL_STAORGE );
                } else {
                    if (addJpgNoteToGallery( bmp )) {
                        Toast.makeText( getContext(), "Note saved into the Gallery", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( getContext(), "Unable to store the note", Toast.LENGTH_SHORT ).show();
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace( R.id.fragment_container,
                            new HomeFragment() ).commit();
                }
            }
        } );

        return rootView;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File( Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("NoteDown", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor( Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgNoteToGallery(Bitmap note) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("NoteDown"), String.format("Note_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(note, photo);
            scanMediaFile(photo);
            Toast.makeText( getContext(),""+photo,Toast.LENGTH_LONG ).show();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        this.getActivity().sendBroadcast(mediaScanIntent);
    }
}
