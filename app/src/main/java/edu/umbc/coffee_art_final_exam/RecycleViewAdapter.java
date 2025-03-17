package edu.umbc.coffee_art_final_exam;
/*CORY RAFKIN | IS413 | COFFEE ART PROJ FINAL EXAM*/
//comments
/*RecycleViewAdapter, RecycleViewHolder classes set up the layout
* and bind the data for each ViewItem in the RecyclerView. Also contains
* MyClickListener interface for event handler behavior when grid
* item is clicked. This interface is implemented in HomeFragment.java.
*
* RecycleViewAdapter extends the abstract class RecyclerView.Adapter and
* implements its abstract methods: onCreateViewHolder(), onBindViewHolder(),
* getItemCount()*/

//RecyclerView documentation from Android Studio
/*A flexible view for providing a limited window into a large data set.
Glossary of terms:
Adapter:    A subclass of RecyclerView.Adapter responsible for providing views that
            represent items in a data set.
----------------------------------------------------------------------------
Position:   The position of a data item within an Adapter.
-------------------------------------------------------------
Index:      The index of an attached child view as used in a call to ViewGroup.getChildAt.
            Contrast with Position.
------------------------------------------------------------------------------------------
Binding:    The process of preparing a child view to display data corresponding to
            a position within the adapter.
------------------------------------------------------------------------------------
Recycle (view): A view previously used to display data for a specific adapter position may be
                placed in a cache for later reuse to display the same type of data again later.
--------------------------------------------------------------------------------------------
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    /*-----------------------------------------------------------------------*/
    //define interface for event handler behavior
    public interface MyClickListener {
        //position of data w/in an adapter
        void onClickItem(int position);
    }
    /*-----------------------------------------------------------------------*/
    //instance variables
    Context context; //the fragment which instantiates the adapter obj?
    String[] artists; //array to hold artist names
    int[] images; //array to hold coffee images, indexed by id (int)
    MyClickListener iOnClickListener; //listener obj to send to adapter

    //constructor for adapter → context, data, onclick listener
    public RecycleViewAdapter(Context context, String[] artists, int[] images,
                              MyClickListener iOnClickListener)
    {
        this.context = context;
        this.artists = artists;
        this.images = images;
        this.iOnClickListener = iOnClickListener;
    }

    /*When new RecycleViewHolder created, inflate a new view from current context using
     * the xml layout and wrap it with the RecycleViewHolder*/
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create layout inflater from context (where RecycleViewAdapter was instantiated)
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflate a view with row_model.xml layout
        View recycleView = layoutInflater.inflate(R.layout.row_model, parent, false);
        //return new RecycleViewHolder wrapping the view + onclick listener
        return new RecycleViewHolder(recycleView,iOnClickListener);
    }
    //binds data to the view holder at the given position
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.ivThumbnail.setImageResource(images[position]);
        holder.tvArtistName.setText(artists[position]);
    }
    //returns the length of the artists array
    @Override
    public int getItemCount() {
        return artists.length;
    }
    /*-----------------------------------------------------------------------------------
    * Inner class → RecycleViewHolder: container for the current ViewItem, applies the layout
    * from row_model.xml. No data bound at this point. This class implements the constructor
    * from RecyclerView.ViewHolder abstract class and onClick() abstract method from
    * View.OnClickListener interface */
    public static class RecycleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        //nodes in row_model.xml
        ImageView ivThumbnail;
        TextView tvArtistName;

        //onclickListener
        MyClickListener iOnClickListener;

        //constructor, contains row_model.xml nodes + MyClickListener
        public RecycleViewHolder(@NonNull View itemView, MyClickListener iOnClickListener) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbNail);
            tvArtistName = itemView.findViewById(R.id.tvArtist);
            this.iOnClickListener = iOnClickListener;
            itemView.setOnClickListener(this);
        }
        //implement abstract method from interface for onclick behavior
        @Override
        public void onClick(View v) {
            iOnClickListener.onClickItem(getAdapterPosition());
        }
    }
}
