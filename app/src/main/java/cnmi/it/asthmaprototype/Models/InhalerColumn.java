package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public class InhalerColumn {
    private InhalerColumn(){


    }

    public static class InhalerEntry implements BaseColumns{
        public static final String TABLE_NAME = "asthma_inhaler";

        public static final String _ID = "id";
        public static final String COLUMN_DID = "drugid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TIMES = "times";
        public static final String COLUMN_INADAY = "inaday";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_MORNING  = "ismorning";
        public static final String COLUMN_EVENING = "isevening";
        public static final String COLUMN_ISACTIVE = "is_active";

    }


}
