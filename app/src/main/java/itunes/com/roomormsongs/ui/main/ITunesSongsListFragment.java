package itunes.com.roomormsongs.ui.main;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import itunes.com.roomormsongs.R;
import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;
import itunes.com.roomormsongs.databinding.FragmentItunesSongsListBinding;
import itunes.com.roomormsongs.ui.detail.SongDetailActivity;


public class ITunesSongsListFragment extends LifecycleFragment implements ITunesSongsListCallback {

    @Inject
    ITunesSongsListViewModel ITunesSongsListViewModel;

    FragmentItunesSongsListBinding binding;

    public static ITunesSongsListFragment newInstance() {
        Bundle args = new Bundle();
        ITunesSongsListFragment fragment = new ITunesSongsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItunesSongsListBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.setAdapter(new ITunesSongsListAdapter(this));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ITunesSongsListViewModel
                .getTopSongs()
                .observe(this, listResource -> binding.setResource(listResource));
    }

    @Override
    public void onSongClicked(ITunesSongEntity ITunesSongEntity, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        startActivity(SongDetailActivity.newIntent(getActivity(), ITunesSongEntity.getId()), options.toBundle());
    }
}
