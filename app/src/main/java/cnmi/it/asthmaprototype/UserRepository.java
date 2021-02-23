package cnmi.it.asthmaprototype;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class UserRepository {

    private UserDAO thisuserDAO;
    private LiveData<List<CurrentUser>> allUser;

    UserRepository(Application application) {
        AsthmaDatabase db = AsthmaDatabase.getDatabase(application);
        thisuserDAO = db.userDAO();
        allUser = thisuserDAO.getLiveAll();
    }

    LiveData<List<CurrentUser>> getAllUsers(){
        return allUser;
    }

    void insert(CurrentUser currentuser){
        AsthmaDatabase.databaseWriteExecutor.execute(() -> {
            thisuserDAO.insert(currentuser);
        });
    }
}
