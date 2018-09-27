package id.roufroufrouf.javaretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
//    @GET("searchplayers.php")
//    Call<Global> searchPlayers(@Query("t") String status);
    @GET("index.php")
    Call<Global> searchPlayers(@Query("cari") String status);
}
