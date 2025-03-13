package za.co.moson.utils;

import org.springframework.stereotype.Component;

import java.util.SplittableRandom;

@Component
public class GenerateRefNumbers {


    public String generateRef() {
        SplittableRandom splittableRandom = new SplittableRandom();
        StringBuilder builder = new StringBuilder(Constants.PREF);

        for (int a = 0; a < 8; a++) {
            builder.append(splittableRandom.nextInt(0, 9));
        }
        return builder.toString();
    }
}
