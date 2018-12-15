package lsl.studio.com.bymybeer.rest;
//ROUTES

import java.util.List;

import lsl.studio.com.bymybeer.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PunkAPIService {

    @GET("beers?per_page=15")
    Call<List<Beer>> getListBeer(@Query("page") int page);


    @GET("beers/{id}/")
    Call<List<Beer>> getBeer(@Path("id") int id);

    @GET("beers?per_page=15")
    Call<List<Beer>> getBeerByName(@Query("beer_name") String beer_name,
                                   @Query("page") int page);

    @GET("beers")
    Call<List<Beer>> getAllBeer();


}