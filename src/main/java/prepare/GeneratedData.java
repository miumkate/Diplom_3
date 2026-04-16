package prepare;

import com.github.javafaker.Faker;

public class GeneratedData {

    Faker faker = new Faker();

    public ModelUser getNewUser(){
        return new ModelUser(
                faker.internet().emailAddress(),
                faker.internet().password(6,20),
                faker.name().firstName());
    }

    public ModelUser getIncompleteUser(){
        return new ModelUser(
                faker.internet().emailAddress(),
                faker.internet().password(1,5),
                faker.name().firstName());
    }

    public ModelUser getExistingUser(){
        return new ModelUser(
                "colby.stamm@hotmail.com",
                "oaypp9mexa4ii4q",
                "Alton");
    }
}


