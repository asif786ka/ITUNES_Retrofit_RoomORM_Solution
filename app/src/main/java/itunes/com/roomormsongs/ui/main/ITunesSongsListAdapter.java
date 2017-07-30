package itunes.com.roomormsongs.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import itunes.com.roomormsongs.data.local.entity.ITunesSongEntity;
import itunes.com.roomormsongs.databinding.ItemSongListBinding;
import itunes.com.roomormsongs.ui.BaseAdapter;



public class ITunesSongsListAdapter extends BaseAdapter<ITunesSongsListAdapter.SongViewHolder, ITunesSongEntity> {

    private List<ITunesSongEntity> songEntities;

    private final ITunesSongsListCallback ITunesSongsListCallback;

    public ITunesSongsListAdapter(@NonNull ITunesSongsListCallback ITunesSongsListCallback) {
        songEntities = new ArrayList<>();
        this.ITunesSongsListCallback = ITunesSongsListCallback;
    }

    @Override
    public void setData(List<ITunesSongEntity> songEntities) {
        this.songEntities = songEntities;
        notifyDataSetChanged();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return SongViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, ITunesSongsListCallback);
    }

    @Override
    public void onBindViewHolder(SongViewHolder viewHolder, int i) {
        viewHolder.onBind(songEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return songEntities.size();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {

        public static SongViewHolder create(LayoutInflater inflater, ViewGroup parent, ITunesSongsListCallback callback) {
            ItemSongListBinding itemSongListBinding = ItemSongListBinding.inflate(inflater, parent, false);
            return new SongViewHolder(itemSongListBinding, callback);
        }

        ItemSongListBinding binding;

        public SongViewHolder(ItemSongListBinding binding, ITunesSongsListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onSongClicked(binding.getSong(), binding.imageViewCover));
        }

        public void onBind(ITunesSongEntity ITunesSongEntity) {
            binding.setSong(ITunesSongEntity);
            binding.executePendingBindings();
        }
    }
}
