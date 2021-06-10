package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class TransColumn {

    public TransColumn(){

    }

    public static class TransEntry implements BaseColumns {
        public static final String TABLE_NAME = "trans";
        public static final String COLUMN_TID = "tid";
        public static final String COLUMN_PID = "pid";
        public static final String COLUMN_DID = "did";
        public static final String COLUMN_MORNING = "morining";
        public static final String COLUMN_EVENING = "evening";

    }
}
