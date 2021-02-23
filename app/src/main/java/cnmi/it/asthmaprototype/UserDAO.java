package cnmi.it.asthmaprototype;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM currentuser")
    List<CurrentUser> getAll();

    @Query("SELECT * FROM currentuser WHERE id IN (:userIds)")
    List<CurrentUser> loadUserByIds(int[] userIds);

    @Query("SELECT * FROM currentuser")
    LiveData<List<CurrentUser>> getLiveAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CurrentUser info);

    @Delete
    void delete(CurrentUser info);

    @Update
    void update(CurrentUser info);

    @Query("DELETE FROM currentuser")
    void clear();

}
