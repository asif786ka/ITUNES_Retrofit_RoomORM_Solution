package itunes.com.roomormsongs.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import itunes.com.roomormsongs.data.local.dao.ITunesSongDao;
import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;


@Database(entities = {ITunesSongEntity.class}, version = 2)
public abstract class SongsDatabase extends RoomDatabase{

    public abstract ITunesSongDao songsDao();
}
