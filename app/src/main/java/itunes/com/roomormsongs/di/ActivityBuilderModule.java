package itunes.com.roomormsongs.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import itunes.com.roomormsongs.ui.detail.SongDetailActivity;
import itunes.com.roomormsongs.ui.main.MainActivity;

/**
 * Created by mertsimsek on 30/05/2017.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract SongDetailActivity movieDetailActivity();
}
