package se.hellsoft.databinding.validation;

import android.app.Application;

public class MyApp extends Application {

    private MainViewModel mainViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mainViewModel = new MainViewModel();
    }

    // We should use DI instead :)
    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }
}
