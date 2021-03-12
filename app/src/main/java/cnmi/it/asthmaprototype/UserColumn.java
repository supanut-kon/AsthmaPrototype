package cnmi.it.asthmaprototype;

import android.provider.BaseColumns;

public final class UserColumn {

    private UserColumn() {
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "asthma_patient";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_GENDER = "gender";
    }
}
