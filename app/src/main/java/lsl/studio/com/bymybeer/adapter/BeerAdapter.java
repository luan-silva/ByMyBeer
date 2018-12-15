package lsl.studio.com.bymybeer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import lsl.studio.com.bymybeer.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import lsl.studio.com.bymybeer.model.Beer;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beerList;
    private Context context;

    public BeerAdapter(List<Beer> beerList, Context context) {
        this.context = context;
        this.beerList = beerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_beer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Beer beer = beerList.get(position);


        Picasso.with(context)
                .load(new File(beer.getImageUrl()))
                .resize(50, 100)
                .centerCrop()
                .into(holder.beerCover);

        holder.name.setText(beer.getName());
        holder.tagline.setText(beer.getTagline());

    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView beerCover;
        TextView name;
        TextView tagline;
        ImageButton btn_fav;


        ViewHolder(View itemView) {
            super(itemView);
            beerCover = itemView.findViewById(R.id.beer_cover);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            btn_fav = itemView.findViewById(R.id.btn_fav);
            
        }
    }

}
