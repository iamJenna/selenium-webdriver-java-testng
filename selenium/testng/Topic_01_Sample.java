package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_01_Sample {
    @Test
    public void testGetRandomNumber(){
        Topic_01_Sample sample = new Topic_01_Sample();
        int randomNumber = sample.getRandomNumber();
        Assert.assertTrue(randomNumber >= 0 && randomNumber < 1000000);
    }

    private int getRandomNumber(){
        return new Random().nextInt(1000000);
    }
}
