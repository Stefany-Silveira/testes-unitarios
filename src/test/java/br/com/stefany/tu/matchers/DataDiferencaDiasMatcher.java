package br.com.stefany.tu.matchers;

import br.com.stefany.tu.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer qtdDias;

    public DataDiferencaDiasMatcher(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    @Override
    public void describeTo(Description description) {
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qtdDias));
    }
}
