package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public class InhalerColumn {
    private InhalerColumn(){


    }

    public static class InhalerEntry implements BaseColumns{
        public static final String TABLE_NAME = "inhaler";

        public static final String _ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_UPDATE_DATE = "update_date";


    }


}
