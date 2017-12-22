package loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 11/17/2017.
 */
public class DataClasses {
    public static List<Class> dataClasses = new ArrayList<>();
    static {
        dataClasses.addAll(Arrays.asList(
                ItemData.class,
                SkillData.class
                )
        );
    }
}

