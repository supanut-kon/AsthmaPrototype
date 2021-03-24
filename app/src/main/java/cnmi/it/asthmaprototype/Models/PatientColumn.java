package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class PatientColumn {

    private PatientColumn() {
    }

    public static class PatientEntry implements BaseColumns {
        public static final String TABLE_NAME = "asthma_patient";
        public static final String COLUMN_HN = "hn";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_CONGENITAL = "congenital";
    }
}
