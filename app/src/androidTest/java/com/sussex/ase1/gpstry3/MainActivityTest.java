
package com.sussex.ase1.gpstry3;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertTrue;

//import static org.mockito.Mockito.*;



/**
 * Created by Steve Dixon on 09/11/2016.
 */



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void validPostcode() throws Exception {
        //  valid postcode formats    AA9A 9AA  |  A9A 9AA   |  A9 9AA  |  A99 9AA   |  AA9 9AA   |  AA99 9AA

        // (GIR 0AA)|((([A-Z-[QVX]][0-9][0-9]?)|(([A-Z-[QVX]][A-Z-[IJZ]][0-9][0-9]?)|(([A-Z-[QVX]][0-9][A-HJKPSTUW])|([A-Z-[QVX]][A-Z-[IJZ]][0-9][ABEHMNPRVWXY])))) [0-9][A-Z-[CIKMOV]]{2})

        MainActivity ma = mActivityRule.getActivity();
        Random rand = new Random();

        char[] charAPos1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'Y', 'Z'};
        char[] charAPos2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};
        char[] charDPos3 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'P', 'S', 'T', 'U', 'W'};
        char[] charDPos4 = {'A', 'B', 'E', 'H', 'M', 'N', 'P', 'R', 'V', 'W', 'X', 'Y'};

        char[] charUPos1 = {'A', 'B', 'D', 'E', 'F', 'G', 'H', 'J', 'L', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};

        for (int i = 0; i <= 100; i++) {
            String postcode = "";
            int lengthAreaDistrict = rand.nextInt(3) + 2;
            postcode += charAPos1[rand.nextInt(23)];                    // Set first position of postcode to a character

            // The postcode area and district can be either 2,3 or 4 characters in length.
            switch (lengthAreaDistrict) {
                case 2:
                    postcode += rand.nextInt(9);                            // Set second position of postcode to a digit. B1, A4, D3
                    break;
                case 3:
                    int area3Format = rand.nextInt(3) + 1;                  // Three formats for postcode. AA9, A9A, A99
                    switch (area3Format) {
                        case 1:
                            postcode += rand.nextInt(10);
                            postcode += rand.nextInt(10);
                            break;
                        case 2:
                            postcode += charAPos2[rand.nextInt(23)];
                            postcode += rand.nextInt(10);
                            break;
                        case 3:
                            postcode += rand.nextInt(10);
                            postcode += charDPos3[rand.nextInt(15)];
                            break;
                        default:
                            ;
                    }
                    break;
                case 4:                                                     // Two formats for postcode AA9A, AA99
                    int area4Format = rand.nextInt(2) + 1;
                    postcode += charAPos2[rand.nextInt(23)];
                    switch (area4Format) {
                        case 1:
                            postcode += rand.nextInt(10);
                            postcode += charDPos4[rand.nextInt(12)];
                            break;
                        case 2:
                            postcode += rand.nextInt(10);
                            postcode += rand.nextInt(10);
                            break;
                        default:
                            ;
                    }
                    break;
                default: ;
            }
            postcode += " ";
            postcode += rand.nextInt(10);                                    // Set the Sector and Unit of the postcode
            postcode += charUPos1[rand.nextInt(20)];
            postcode += charUPos1[rand.nextInt(20)];

            if (ma.validPostcode(postcode)) {
                Log.e("ASE1_VALID_POSTCODE", postcode += " : true");
            }else {
                Log.e("ASE1_VALID_POSTCODE", postcode += " : false");
            }
            

            assertTrue(ma.validPostcode(postcode));                         // test to see if postcode generated passes the valid postcode test
        }
    }
}