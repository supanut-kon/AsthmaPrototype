package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class FlowColumn {

    private FlowColumn() {
    }

    public static class FlowEntry implements BaseColumns {
        public static final String TABLE_NAME = "asthma_flow";
        public static final String COLUMN_FLOW = "flow";
        public static final String COLUMN_MAX = "max";
        public static final String COLUMN_80 = "percent80";
        public static final String COLUMN_60 = "percent60";
        public static final String COLUMN_USER_ID = "userid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";

    }
}
