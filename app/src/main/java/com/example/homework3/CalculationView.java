package com.example.homework3;

import com.arellomobile.mvp.MvpView;

public interface CalculationView extends MvpView {
    void updateValues(CalculationResult result);
    void clear();
}
