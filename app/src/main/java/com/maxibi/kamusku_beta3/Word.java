package com.maxibi.kamusku_beta3;

import java.util.ArrayList;

/**
 * Created by User on 8/11/2017.
 */

public class Word {

    int id;
    String word_EN, word_MS;

    //Normal Constructor
    public Word(String ms, ArrayList<String> en)
    {
        word_MS = ms;

        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0; i< en.size(); i++)
        {
            stringBuilder.append(i);
        }

        word_EN = stringBuilder.toString();

    }

    //Normal Constructor
    public Word(String ms, String en)
    {
        word_EN = en;
        word_MS = ms;
    }


}
