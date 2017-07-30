package itunes.com.roomormsongs.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import itunes.com.roomormsongs.data.local.SongsDatabase;
import itunes.com.roomormsongs.data.local.dao.ITunesSongDao;
import itunes.com.roomormsongs.data.remote.ApiConstants;
import itunes.com.roomormsongs.data.remote.ITunesDBService;
import itunes.com.roomormsongs.data.remote.RequestInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ITunesDBService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ITunesDBService.class);
    }

    @Provides
    @Singleton
    SongsDatabase provideSongDatabase(Application application) {
        return Room.databaseBuilder(application, SongsDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    ITunesSongDao provideSongDao(SongsDatabase songsDatabase) {
        return songsDatabase.songsDao();
    }

}
