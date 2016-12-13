package se.hellsoft.databinding.validation;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import se.hellsoft.databinding.validation.databinding.PageOneBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PageOne extends Fragment {

    private static final String TAG = "PageOne";

    public PageOne() {
    }

    @BindingAdapter("bind:error")
    public static void textInputLayoutErrorText(TextInputLayout textInputLayout, String text) {
        Log.d(TAG, "Set error text to " + text);
        textInputLayout.setError(text);
    }

    public static PageOne newInstance() {
        return new PageOne();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        MainViewModel viewModel = ((MainViewModel.ViewModelHolder) activity).getViewModel();

        PageOneBinding pageOneBinding = PageOneBinding.inflate(inflater);
        pageOneBinding.setViewModel(viewModel);
        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, countries);
        pageOneBinding.country.setAdapter(countryAdapter);
        return pageOneBinding.getRoot();
    }
}
