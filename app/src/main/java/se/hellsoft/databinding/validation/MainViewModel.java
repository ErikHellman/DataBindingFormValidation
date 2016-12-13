package se.hellsoft.databinding.validation;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

public class MainViewModel {
    private static final String TAG = "MainViewModel";
    public ObservableField<String> fullName = new ObservableField<>();
    public ObservableBoolean fullNameError = new ObservableBoolean(false);
    public ObservableField<String> streetAddress = new ObservableField<>();
    public ObservableBoolean streetAddressError = new ObservableBoolean(false);
    public ObservableField<String> postalCode = new ObservableField<>();
    public ObservableBoolean postalCodeError = new ObservableBoolean(false);
    public ObservableField<String> cityName = new ObservableField<>();
    public ObservableBoolean cityNameError = new ObservableBoolean(false);
    public ObservableField<String> countryName = new ObservableField<>();
    public ObservableBoolean countryNameError = new ObservableBoolean(false);
    public ObservableField<String> emailAddress = new ObservableField<>();
    public ObservableBoolean emailAddressError = new ObservableBoolean(false);
    public ObservableField<String> phoneNumber = new ObservableField<>();
    public ObservableBoolean phoneNumberError = new ObservableBoolean(false);
    public ObservableBoolean dataValid = new ObservableBoolean(false);
    public ObservableBoolean operationCompleted = new ObservableBoolean(false);
    @Nullable
    private ViewController viewController;

    public MainViewModel() {
    }

    public <T> void onFieldChanged(ObservableField<T> field, ObservableBoolean errorIndicator,
                                   boolean hasFocus, Validator<T> validator) {
        if (hasFocus) {
            errorIndicator.set(false);
        } else {
            T value = field.get();
            errorIndicator.set(!validator.validate(value));
            Log.d(TAG, "Field is invalid: " + errorIndicator.get());
            dataValid.set(validateForm());
        }
    }

    public Validator<String> minLength(final int min) {
        return new Validator<String>() {
            @Override
            public boolean validate(String value) {
                return value != null && value.length() >= min;
            }
        };
    }

    public void onFullNameFocusChanged(boolean hasFocus) {
        onFieldChanged(fullName, fullNameError, hasFocus, minLength(3));
    }

    public void onStreetAddressFocusChanged(boolean hasFocus) {
        onFieldChanged(streetAddress, streetAddressError, hasFocus, minLength(3));
    }

    public void onPostalCodeFocusChanged(boolean hasFocus) {
        onFieldChanged(postalCode, postalCodeError, hasFocus, minLength(3));
    }

    public void onCityNameFocusChanged(boolean hasFocus) {
        onFieldChanged(cityName, cityNameError, hasFocus, minLength(3));
    }

    public void onCountryNameFocusChanged(boolean hasFocus) {
        onFieldChanged(countryName, countryNameError, hasFocus, minLength(3));
    }

    public void onEmailAddressFocusChanged(boolean hasFocus) {
        onFieldChanged(emailAddress, emailAddressError, hasFocus, minLength(3));
    }

    public void onPhoneNumberFocusChanged(boolean hasFocus) {
        onFieldChanged(phoneNumber, phoneNumberError, hasFocus, minLength(5));
    }

    private boolean validateForm() {
        boolean isValid = !(fullNameError.get() && fullName.get() != null
                && streetAddressError.get() && streetAddress.get() != null
                && postalCodeError.get() && postalCode.get() != null
                && cityNameError.get() && cityName.get() != null
                && countryNameError.get() && countryName.get() != null
                && emailAddressError.get() && emailAddress.get() != null
                && phoneNumberError.get()) && phoneNumber.get() != null;
        Log.d(TAG, "Form is valid: " + isValid);
        return isValid;
    }

    public void performSubmit() {
        // Early return in case we're null here
        if (viewController == null) return;
        Log.d(TAG, "performSubmit()");
        dataValid.set(false);
        viewController.onNavigate(R.layout.page_two);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                operationCompleted.set(true);
            }
        }).start();
    }

    public void setViewController(@Nullable ViewController viewController) {
        this.viewController = viewController;
    }

    public interface Validator<T> {
        boolean validate(T value);
    }

    public interface ViewController {
        void onNavigate(@LayoutRes int id);
    }

    public interface ViewModelHolder {
        MainViewModel getViewModel();
    }

}
