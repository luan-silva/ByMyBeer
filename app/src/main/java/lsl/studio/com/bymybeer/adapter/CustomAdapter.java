package lsl.studio.com.bymybeer.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import lsl.studio.com.bymybeer.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import lsl.studio.com.bymybeer.model.Beer;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Beer> dataList;
    private Context context;
    PostItemListener mItemListener;

    public CustomAdapter(Context context, List<Beer> dataList, PostItemListener itemListener){
        this.context = context;
        this.dataList = dataList;
        mItemListener = itemListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;
        PostItemListener mItemListener;

        TextView txtTitle;
        private ImageView coverImage;
        ImageButton btn_fav;


        CustomViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.name_beer);
            coverImage = mView.findViewById(R.id.image_beer);
            btn_fav = mView.findViewById(R.id.btn_fav);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Beer beer = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(beer.getId());

            notifyDataSetChanged();
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.beer_item, parent, false);
        return new CustomViewHolder(view, this.mItemListener);
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.txtTitle.setText(dataList.get(position).getName());


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context)).loggingEnabled(true);
        builder.build().load(dataList.get(position).getImageUrl())
                .placeholder((R.drawable.placeholder))
                .error(R.drawable.placeholder)
                .into(holder.coverImage);

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    private Beer getItem(int adapterPosition) {
        return dataList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(int id);
    }

}
