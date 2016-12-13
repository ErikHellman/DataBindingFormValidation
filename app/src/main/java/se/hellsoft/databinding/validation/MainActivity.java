package se.hellsoft.databinding.validation;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import se.hellsoft.databinding.validation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements MainViewModel.ViewModelHolder, MainViewModel.ViewController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainViewModel viewModel = ((MyApp) getApplication()).getMainViewModel();
        viewModel.setViewController(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            onNavigate(R.layout.page_one);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainViewModel viewModel = ((MyApp) getApplication()).getMainViewModel();
        viewModel.setViewController(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @MainThread
    @Override
    public MainViewModel getViewModel() {
        return ((MyApp) getApplication()).getMainViewModel();
    }

    @Override
    public void onNavigate(@LayoutRes int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.layout.page_one:
                transaction.replace(R.id.fragment, PageOne.newInstance());
                break;
            case R.layout.page_two:
                transaction.replace(R.id.fragment, PageTwo.newInstance());
                break;
            default:
                return;
        }
        transaction.commit();
    }
}
