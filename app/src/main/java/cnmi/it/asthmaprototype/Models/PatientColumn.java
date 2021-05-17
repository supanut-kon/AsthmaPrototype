package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class PatientColumn {

    private PatientColumn() {
    }

    public static class PatientEntry implements BaseColumns {
        public static final String TABLE_NAME = "asthma_patient";
        public static final String COLUMN_HN = "hn";//1
        public static final String COLUMN_NAME = "name";//2
        public static final String COLUMN_AGE = "age";//3
        public static final String COLUMN_BD = "birthdate";//4
        public static final String COLUMN_PEFR = "peakflow";//5
        public static final String COLUMN_HEIGHT = "height";//6
        public static final String COLUMN_GENDER = "gender";//7
        public static final String COLUMN_WEIGHT = "weight";//8
        public static final String COLUMN_CONGENITAL = "congenital";//9
        public static final String COLUMN_USER = "userid";//10
    }
}
