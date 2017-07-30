package itunes.com.roomormsongs.data.remote;

import itunes.com.roomormsongs.data.remote.model.ITunesSongsListResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ITunesDBService {

    @GET("itunes-music/top-songs/50/explicit/json")
    Call<ITunesSongsListResponse> loadSongs();

}
