package itunes.com.roomormsongs.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import itunes.com.roomormsongs.data.ITunesSongsRepository;
import itunes.com.roomormsongs.data.Resource;
import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;


public class ITunesSongsListViewModel extends ViewModel {
    private final LiveData<Resource<List<ITunesSongEntity>>> topSongs;

    @Inject
    public ITunesSongsListViewModel(ITunesSongsRepository ITunesSongsRepository) {
        topSongs = ITunesSongsRepository.loadTopSongs();
    }

    LiveData<Resource<List<ITunesSongEntity>>> getTopSongs() {
        return topSongs;
    }
}
