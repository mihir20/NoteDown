package in.notedown.mi;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by mi on 24/12/17.
 */

public class NotesArrayAdapter extends RecyclerView.Adapter {

    private ArrayList<Uri> mfile;


    NotesArrayAdapter(ArrayList<Uri> file){
        this.mfile = file;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.note_container,parent,false );

        Log.e( "RV",": created" );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, int position) {
       // String imagePath = mfile.get( position );
       // Log.e( "ARRAYADAPTER",imagePath );
        //((ViewHolder) mHolder).imageView.setImageBitmap( BitmapFactory.decodeFile( imagePath ) );
        Glide.with( mHolder.itemView.getContext() ).load( mfile.get( position ) )
                .into(((ViewHolder) mHolder).imageView);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // stores and recycles views as they are scrolled off screen
    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super( itemView );
            itemView =  itemView.findViewById( R.id.note_image );

        }
    }
}
