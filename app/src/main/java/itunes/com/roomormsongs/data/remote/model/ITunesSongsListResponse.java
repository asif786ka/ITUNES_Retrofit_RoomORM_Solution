package itunes.com.roomormsongs.data.remote.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ITunesSongsListResponse {

    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
