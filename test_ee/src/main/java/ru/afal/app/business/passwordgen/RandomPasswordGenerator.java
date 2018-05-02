package ru.afal.app.business.passwordgen;

import org.apache.commons.lang3.RandomStringUtils;

@PasswordGenType( GeneratorType.ALPHANUMERIC )
public class RandomPasswordGenerator implements PasswordGenerator{

    @Override
    public String generate( int length ) {
        return RandomStringUtils.randomAlphanumeric( length );
    }
}
