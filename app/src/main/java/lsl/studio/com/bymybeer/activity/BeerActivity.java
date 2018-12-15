package lsl.studio.com.bymybeer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import lsl.studio.com.bymybeer.adapter.BeerAdapter;
import lsl.studio.com.bymybeer.model.Beer;
import lsl.studio.com.bymybeer.rest.ControlBeerList;
import lsl.studio.com.bymybeer.rest.PunkAPIService;
import lsl.studio.com.bymybeer.R;
import lsl.studio.com.bymybeer.adapter.BeerAdapter;
import lsl.studio.com.bymybeer.model.Beer;
import lsl.studio.com.bymybeer.rest.ControlBeerList;
import lsl.studio.com.bymybeer.rest.PunkAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerActivity extends AppCompatActivity {
    private BeerAdapter adapter;
    private Beer beer;
    ImageButton btnFavorite;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        btnFavorite = findViewById(R.id.btn_favorite);
        int id = getIntent().getExtras().getInt("id");

        PunkAPIService service = ControlBeerList.getPunkAPIClient();

        Call<List<Beer>> call = service.getBeer(id);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response1) {
                List<Beer> Titou = response1.body();
                beer=response1.body().get(0);
                gestionFav();
                generateDataList(beer);
            }
            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Toast.makeText(BeerActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
        private void gestionFav(){

        btnFavorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(beer.getIsFavourite()){
                    beer.setFavourite(false);
                    Toast.makeText(BeerActivity.this,beer.getName()+" has been removed from your favorite beers",Toast.LENGTH_LONG).show();
                }
                else{
                    beer.setFavourite(true);
                    Toast.makeText(BeerActivity.this,beer.getName()+" has been added to your favorite beers",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


        private void generateDataList(Beer beer) {

            if (beer.getImageUrl() == null) {
                Picasso.with(this).load(beer.getImageUrl());
            } else {
                Picasso.with(this).load(beer.getImageUrl())
                        .into((ImageView) findViewById(R.id.beer_cover));
            }
            ((TextView) findViewById(R.id.name)).setText(beer.getName());
            ((TextView) findViewById(R.id.tagline)).setText(beer.getTagline());
            ((TextView) findViewById(R.id.description)).setText(beer.getDescription());

        }

}
