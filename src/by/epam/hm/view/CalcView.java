package by.epam.hm.view;

import by.epam.hm.logic.CalcLogic;

public class CalcView {
    public static void printResultOfCalculation(String str) {
        System.out.println(CalcLogic.calculation(str));
    }
}
