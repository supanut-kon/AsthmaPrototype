package cnmi.it.asthmaprototype;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CurrentUser.class}, version = 1)
public abstract class AsthmaDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    private static volatile AsthmaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AsthmaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AsthmaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AsthmaDatabase.class, "asthma-db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
}
