package se.hellsoft.databinding.validation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.hellsoft.databinding.validation.databinding.PageTwoBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PageTwo extends Fragment {

    public PageTwo() {
    }

    public static PageTwo newInstance() {
        return new PageTwo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        MainViewModel viewModel = ((MainViewModel.ViewModelHolder) activity).getViewModel();

        PageTwoBinding binding = PageTwoBinding.inflate(inflater);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
