package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class UserColumn {

    private UserColumn(){

    }

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "asthma_user";
        public static final String _ID = "id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PATIENTID = "patientid";
        public static final String PASSCODE = "passcode";
    }
}
