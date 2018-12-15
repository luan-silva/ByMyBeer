package lsl.studio.com.bymybeer.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import lsl.studio.com.bymybeer.CheckNetwork;
import lsl.studio.com.bymybeer.adapter.CustomAdapter;
import lsl.studio.com.bymybeer.model.Beer;
import lsl.studio.com.bymybeer.R;
import lsl.studio.com.bymybeer.rest.ControlBeerList;
import lsl.studio.com.bymybeer.rest.PunkAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    int pageNumber = 1;
    ProgressDialog progressDialog;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView nosignal = (ImageView)findViewById(R.id.nosignal);

        if(CheckNetwork.isInternetAvailable(MainActivity.this)) //returns true if internet available
        {
            nosignal.setVisibility(View.GONE);

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();
            final ImageView btnNext = findViewById(R.id.nextbutton);
            final ImageView btnBack = findViewById(R.id.backbutton);

            final PunkAPIService service = ControlBeerList.getPunkAPIClient();


            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pageNumber > 1) pageNumber -= 1;
                    Call<List<Beer>> call1 = service.getListBeer(pageNumber);
                    call1.enqueue(new Callback<List<Beer>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Beer>> call1, @NonNull Response<List<Beer>> response) {
                            generateDataList(response.body());
                            Toast.makeText(MainActivity.this, "pageNumber: " + pageNumber, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Beer>> call1, @NonNull Throwable t) {
                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pageNumber += 1;
                    Call<List<Beer>> call1 = service.getListBeer(pageNumber);
                    call1.enqueue(new Callback<List<Beer>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Beer>> call1, @NonNull Response<List<Beer>> response) {
                            generateDataList(response.body());
                            Toast.makeText(MainActivity.this, "pageNumber: " + pageNumber, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Beer>> call1, @NonNull Throwable t) {
                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


            Call<List<Beer>> call = service.getListBeer(pageNumber);
            call.enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                    progressDialog.dismiss();
                    generateDataList(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });

        } else{
            nosignal.setVisibility(View.VISIBLE);
        }
    }

    private void generateDataList(List<Beer> beerList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, beerList, new CustomAdapter.PostItemListener() {
            @Override
            public void onPostClick(int id) {
                Intent beerActivity = new Intent(MainActivity.this, BeerActivity.class);
                beerActivity.putExtra("id",id);
                startActivity(beerActivity);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
