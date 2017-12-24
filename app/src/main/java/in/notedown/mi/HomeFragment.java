package in.notedown.mi;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private  NotesArrayAdapter notesArrayAdapter;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.fab.setVisibility( View.VISIBLE );


        View rootView = inflater.inflate( R.layout.fragment_home, container, false );

        ArrayList<String> imagePath = new ArrayList<>(  );
        ArrayList<Uri> uris = new ArrayList<>(  );

        recyclerView = rootView.findViewById( R.id.notes_list );

        GridLayoutManager layoutManager = new GridLayoutManager( container.getContext( ),2);

        WritingFragment writingFragment = new WritingFragment();

        File file = writingFragment.getAlbumStorageDir();
        if(file.exists()) {


            String imageFolderPath = file.getPath();
            String[] imageNames = file.list();
            File[] arr = file.listFiles();

            if (imageNames.length >= 1) {
                for (int i = 0; i < imageNames.length; i++) {
                    imagePath.add( imageFolderPath + "/" + imageNames[i]);
                    uris.add( Uri.fromFile( arr[i] ) );
                }
            }
        }

        Log.e( "Uri", "exist: "+ uris );
        Context context = getContext();
        notesArrayAdapter = new NotesArrayAdapter( uris, context );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( notesArrayAdapter );

        return rootView;
    }

}
