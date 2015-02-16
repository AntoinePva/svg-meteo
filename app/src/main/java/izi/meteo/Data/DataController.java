package izi.meteo.Data;

import java.util.ArrayList;

/**
 * Created by matthieu on 06/02/2015.
 */
public class DataController {

    private DataModel currentLocation;
    private ArrayList<DataModel> listLocation;
    private DataAsync syncLocation;


    DataModel findLocation(String name){
        DataModel newModel=new DataModel(name);
        return newModel;
    }

}
