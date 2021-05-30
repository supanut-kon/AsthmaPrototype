package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public class InhalerColumn {
    private InhalerColumn(){


    }

    public static class InhalerEntry implements BaseColumns{
        public static final String TABLE_NAME = "asthma_inhaler";

        public static final String _ID = "id";
        public static final String COLUMN_INHALERID = "inhalerid";
        public static final String COLUMN_PRESC = "prescription";
        public static final String COLUMN_EMINHALERID = "emergencyinhalerid";
        public static final String COLUMN_EMPRESC = "emergencyprescription";
        public static final String COLUMN_PATIENT = "patientid";
        public static final String COLUMN_UPDATE_DATE = "update_date";


    }


}
