package in.notedown.mi;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gcacace.signaturepad.views.SignaturePad;


/**
 * A simple {@link Fragment} subclass.
 */
public class WritingFragment extends Fragment {


    public WritingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_writing, container, false );

        final SignaturePad writingPad = (SignaturePad) rootView.findViewById( R.id.writingPad );
        FloatingActionButton eraseFAB = (FloatingActionButton) rootView.findViewById( R.id.erase_fab );
        FloatingActionButton saveFAB = (FloatingActionButton) rootView.findViewById( R.id.save_fab );

        eraseFAB.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writingPad.clear();
            }
        } );

        return rootView;
    }

}
