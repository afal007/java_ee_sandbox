package ru.afal.app.business.passwordgen;

import org.apache.commons.lang3.RandomStringUtils;

@PasswordGenType( GeneratorType.NUMERIC )
public class NumericPasswordGenerator implements PasswordGenerator {
    @Override
    public String generate( int length ) {
        return RandomStringUtils.randomNumeric( length );
    }
}
