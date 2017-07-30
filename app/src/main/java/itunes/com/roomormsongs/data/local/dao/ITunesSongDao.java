package itunes.com.roomormsongs.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;

@Dao
public interface ITunesSongDao {

    @Query("SELECT * FROM movies")
    LiveData<List<ITunesSongEntity>> loadSongs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSongs(List<ITunesSongEntity> songsEntities);

    @Query("SELECT * FROM movies WHERE id=:id")
    LiveData<ITunesSongEntity> getSong(int id);

}
