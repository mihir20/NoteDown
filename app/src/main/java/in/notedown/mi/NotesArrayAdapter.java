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

public class NotesArrayAdapter extends RecyclerView.Adapter<NotesArrayAdapter.ViewHolder> {

    private ArrayList<Uri> mfile;
    private Context mContext;

    NotesArrayAdapter(ArrayList<Uri> file, Context context){
        this.mfile = file;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from( mContext );
        View view = inflater.inflate( R.layout.note_container,parent,false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(NotesArrayAdapter.ViewHolder mHolder, int position) {
       // String imagePath = mfile.get( position );
       // Log.e( "ARRAYADAPTER",imagePath );
        //((ViewHolder) mHolder).imageView.setImageBitmap( BitmapFactory.decodeFile( imagePath ) );

        Glide.with(mContext ).load( mfile.get( position ) )
                .into(mHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mfile.size();
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

       private ImageView imageView;

       public ViewHolder(View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.note_image );

        }
    }
}
