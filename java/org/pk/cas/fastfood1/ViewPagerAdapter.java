package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.pk.cas.fragments.BryaniFragment;
import org.pk.cas.fragments.IceCreamFragment;
import org.pk.cas.fragments.PizzaFragment;
import org.pk.cas.fragments.SandwichFragment;
import org.pk.cas.fragments.ShakesFragment;
import org.pk.cas.fragments.ZingerFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new BryaniFragment();
            case 1:
                return new SandwichFragment();
            case 2:
                return new PizzaFragment();
            case 3:
                return new ZingerFragment();
            case 4:
                return new IceCreamFragment();
            default:
                return new ShakesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
