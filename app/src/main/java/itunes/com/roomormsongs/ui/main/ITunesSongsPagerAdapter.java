package itunes.com.roomormsongs.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



public class ITunesSongsPagerAdapter extends FragmentStatePagerAdapter{

    private static final String[] titles = new String[]{"ITunesSongs1", "ITunesSongs2", "ITunesSongs3"};

    public ITunesSongsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return ITunesSongsListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
