package com.example.homework3;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CalculationPresenter extends MvpPresenter<CalculationView> implements ICalculationPresenter {
    @Override
    public void calculate(double a, double b) {
        CalculationResult result = new CalculationResult();
        result.perimeter = (a+b)*2;
        result.square = a*b;
        getViewState().updateValues(result);
    }

    @Override
    public void clear() {
        getViewState().clear();
    }
}
