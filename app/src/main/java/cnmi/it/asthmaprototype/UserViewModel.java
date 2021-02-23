package cnmi.it.asthmaprototype;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repo;
    private final LiveData<List<CurrentUser>> allUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repo = new UserRepository(application);
        allUser = repo.getAllUsers();
    }

    LiveData<List<CurrentUser>> getAllUsers() { return allUser; }

    public void insert(CurrentUser currentUser){ repo.insert(currentUser); }
}
