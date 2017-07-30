package itunes.com.roomormsongs.ui.detail;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import itunes.com.roomormsongs.R;
import itunes.com.roomormsongs.databinding.ActivitySongDetailBinding;


public class SongDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private static final String KEY_SONG_ID = "key_song_id";

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    ActivitySongDetailBinding binding;

    @Inject
    SongDetailViewModel songDetailViewModel;

    public static Intent newIntent(Context context, int songId) {
        Intent intent = new Intent(context, SongDetailActivity.class);
        intent.putExtra(KEY_SONG_ID, songId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_detail);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int movieId = getIntent().getIntExtra(KEY_SONG_ID, 0);
        songDetailViewModel.getSong(movieId)
                .observe(this, movieEntity -> binding.setSong(movieEntity));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

}
