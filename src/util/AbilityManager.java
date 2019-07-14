package util;

import ability.Ability;
import ability.IronMaker;

import java.util.ArrayList;

public class AbilityManager {
    public static ArrayList<Ability> getAbilityList(){
        ArrayList<Ability> ablist = new ArrayList<>();

        ablist.add(new IronMaker());

        return ablist;
    }
}
