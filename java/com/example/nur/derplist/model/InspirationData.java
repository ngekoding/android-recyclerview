package com.example.nur.derplist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy Data
 * Created by Nur on 2/7/2017.
 */

public class InspirationData {
    private static String title[] = {"My first title", "My second title", "My third title", "My four title"};
    private static String subTitle[] = {"Subtitle 1", "Subtitle 2", "Subtitle 3", "Subtitle 4"};

    private List<Inspiration> inspirationList;

    public static List<Inspiration> getInspirationList() {
        List<Inspiration> dummyData = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Inspiration data = new Inspiration(title[i], subTitle[i]);
            dummyData.add(data);
        }
        return dummyData;
    }
}
