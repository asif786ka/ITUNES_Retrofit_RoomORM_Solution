package itunes.com.roomormsongs.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import itunes.com.roomormsongs.data.local.dao.ITunesSongDao;
import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;
import itunes.com.roomormsongs.data.remote.ITunesDBService;
import itunes.com.roomormsongs.data.remote.model.ITunesSongsListResponse;
import itunes.com.roomormsongs.data.remote.model.Result;
import retrofit2.Call;


public class ITunesSongsRepository {

    private final ITunesSongDao ITunesSongDao;
    private final ITunesDBService ITunesDBService;

    @Inject
    public ITunesSongsRepository(ITunesSongDao ITunesSongDao, ITunesDBService ITunesDBService) {
        this.ITunesSongDao = ITunesSongDao;
        this.ITunesDBService = ITunesDBService;
    }

    public LiveData<Resource<List<ITunesSongEntity>>> loadTopSongs() {
        return new NetworkBoundResource<List<ITunesSongEntity>, ITunesSongsListResponse>() {

            @Override
            protected void saveCallResult(@NonNull ITunesSongsListResponse item) {


                List<ITunesSongEntity> songEntities = new ArrayList<ITunesSongEntity>();

                List<Result> ituneList = item.getFeed().getResults();


                ITunesSongEntity ITunesSongEntity;
                for (int i = 0; i < ituneList.size(); i++) {
                    Result ituneSong = ituneList.get(i);
                    ITunesSongEntity = new ITunesSongEntity();
                    ITunesSongEntity.setPosterPath(ituneSong.getArtworkUrl100().toString());
                    ITunesSongEntity.setTitle(ituneSong.getName());
                    ITunesSongEntity.setBackdropPath(ituneSong.getArtworkUrl100().toString());
                    ITunesSongEntity.setId(Integer.parseInt(ituneSong.getId()));
                    ITunesSongEntity.setOverview(ituneSong.getArtistName());
                    songEntities.add(ITunesSongEntity);
                }


                ITunesSongDao.saveSongs(songEntities);
            }

            @NonNull
            @Override
            protected LiveData<List<ITunesSongEntity>> loadFromDb() {
                return ITunesSongDao.loadSongs();
            }

            @NonNull
            @Override
            protected Call<ITunesSongsListResponse> createCall() {
                return ITunesDBService.loadSongs();
            }
        }.getAsLiveData();
    }

    public LiveData<ITunesSongEntity> getSong(int id){
        return ITunesSongDao.getSong(id);
    }
}
