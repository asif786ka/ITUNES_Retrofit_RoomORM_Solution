package itunes.com.roomormsongs.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import itunes.com.roomormsongs.data.ITunesSongsRepository;
import itunes.com.roomormsongs.data.Resource;
import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;


public class SongDetailViewModel extends ViewModel{
    private final LiveData<Resource<ITunesSongEntity>> songDetail = new MutableLiveData<>();
    private final ITunesSongsRepository ITunesSongsRepository;

    @Inject
    public SongDetailViewModel(ITunesSongsRepository ITunesSongsRepository) {
        this.ITunesSongsRepository = ITunesSongsRepository;
    }

    public LiveData<ITunesSongEntity> getSong(int id){
        return ITunesSongsRepository.getSong(id);
    }
}
