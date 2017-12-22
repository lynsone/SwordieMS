package loaders;

import client.field.Field;
import util.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/21/2017.
 */
public class FieldData {

    List<Field> fields = new ArrayList<>();

    @Loader(varName = "fields")
    public static void loadFields(File file, boolean exists) {
        if(exists) {
            loadFieldInfoFromWz();
            saveFields(file);
        }
    }

    private static void saveFields(File file) {

    }

    private static void loadFieldInfoFromWz() {

    }
}
