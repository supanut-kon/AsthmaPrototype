package cnmi.it.asthmaprototype.Models;

import android.provider.BaseColumns;

public final class TransactionColumn {

    private TransactionColumn(){

    }

    private static class TransactionEntry implements BaseColumns {
        private final String COLUMN_TID = "tid";
        private final String COLUMN_PID = "pid";
        private final String COLUMN_DID = "did";
        private final String COLUMN_MORNING = "morining";
        private final String COLUMN_EVENING = "evening";

    }
}
