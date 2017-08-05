package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.ChaptersFragment;
import fragment.GeneralFragment;

/**
 * Created by Panda on 20-07-2017.
 */

public class MangaFragmentAdapter extends FragmentPagerAdapter{

    private int fragNumber = 2;

    public MangaFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return new GeneralFragment();
            case 1:

                return new ChaptersFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragNumber;
    }
}
