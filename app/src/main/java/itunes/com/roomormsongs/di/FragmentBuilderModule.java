package itunes.com.roomormsongs.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import itunes.com.roomormsongs.ui.main.ITunesSongsListFragment;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ITunesSongsListFragment contributeSongListFragment();
}
