package secura.driver.scuradriver.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import secura.driver.scuradriver.Fragments.Bonus;
import secura.driver.scuradriver.Fragments.Daily;
import secura.driver.scuradriver.Fragments.Weekly;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_TABS = 3; // Number of tabs

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Daily();
            case 1:
                return new Weekly();
            case 2:
                return new Bonus();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Daily";
            case 1:
                return "Weekly";
            case 2:
                return "Bonus";
            default:
                return super.getPageTitle(position);
        }
    }
}
