package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class YellowPFColumn {

    private YellowPFColumn(){

    }

    public static class YellowPFEntry implements BaseColumns{
        public static final String TABLE_NAME = "yellow_log";
        public static final String _ID = "id";
        public static final String COLUMN_PEF = "pef";
        public static final String COLUMN_ZONE = "zone";
        public static final String COLUMN_MAX = "pefr_max";
        public static final String COLUMN_80 = "percent80";
        public static final String COLUMN_60 = "percent60";
        public static final String COLUMN_PATIENTID = "patientid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_ENDDATE = "enddate";
        public static final String COLUMN_ISACTIVE = "is_active";
    }
}
