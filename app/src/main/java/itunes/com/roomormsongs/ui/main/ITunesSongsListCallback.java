package itunes.com.roomormsongs.ui.main;

import android.view.View;

import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;


public interface ITunesSongsListCallback {
    void onSongClicked(ITunesSongEntity ITunesSongEntity, View sharedView);
}
