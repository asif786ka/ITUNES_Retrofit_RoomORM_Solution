package itunes.com.roomormsongs.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import itunes.com.roomormsongs.ui.detail.SongDetailViewModel;
import itunes.com.roomormsongs.ui.main.ITunesSongsListViewModel;
import itunes.com.roomormsongs.viewmodel.ITunesSongsViewModelFactory;



@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ITunesSongsListViewModel.class)
    abstract ViewModel bindsSongsListViewModel(ITunesSongsListViewModel ITunesSongsListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SongDetailViewModel.class)
    abstract  ViewModel bindsSongDetailViewModel(SongDetailViewModel songDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ITunesSongsViewModelFactory ITunesSongsViewModelFactory);
}
