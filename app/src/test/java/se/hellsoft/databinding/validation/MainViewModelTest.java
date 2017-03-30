package se.hellsoft.databinding.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by abdellahselassi on 3/30/17.
 */
public class MainViewModelTest {

    private MainViewModel vm;

    @Before
    public void setUp() throws Exception {
        vm = new MainViewModel();
    }

    @Test
    public void testMainViewModel_FormValidation() {
        vm.fullName.set("Jon Snow");
        assertFalse(vm.fullNameError.get());

        vm.streetAddress.set("Knight Ayton Management 29 Gloucester Place");
        assertFalse(vm.streetAddressError.get());

        vm.postalCode.set("9011");
        assertFalse(vm.postalCodeError.get());

        vm.cityName.set("London");
        assertFalse(vm.cityNameError.get());

        vm.countryName.set("Uk");
        assertFalse(vm.countryNameError.get());

        vm.emailAddress.set("john.snow@gameofthrones.com");
        assertFalse(vm.emailAddressError.get());

        vm.phoneNumber.set("00877266276");
        assertFalse(vm.phoneNumberError.get());

        assertTrue(vm.validateForm());
    }

}