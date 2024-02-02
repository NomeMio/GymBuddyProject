package database.dao;

import database.SingletonConnection;
import exceptions.logger.CostumeLogger;
import model.WorkoutDay;
import model.WorkoutRoutine;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.query.Queries;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class WorkoutRoutineDAO {
    public void saveWorkoutRoutine(WorkoutRoutine workoutRoutine, String athleteFc) {
        try (PreparedStatement preparedStatement1 = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.INSERT_WORKOUT_ROUTINE_QUERY)) {
            Queries.insertWorkoutRoutine(preparedStatement1, workoutRoutine.getName(), workoutRoutine.getComment(), athleteFc);
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            //todo handle exception
        }
        for (WorkoutDay workoutDay : workoutRoutine.getWorkoutDayList()) {
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, athleteFc);
        }
    }

    public WorkoutRoutine loadWorkoutRoutine(String athleteFC) {
        try (PreparedStatement preparedStatement1 = SingletonConnection.getInstance().getConnection().prepareStatement(
                Queries.LOAD_WORKOUT_ROUTINE_QUERY);
                ResultSet rs = Queries.loadWorkoutRoutine(preparedStatement1, athleteFC)){
            if (rs.next()) {
                WorkoutRoutine workoutRoutine = new WorkoutRoutine(
                        rs.getString("name"),
                        rs.getString("comment"),
                        getLocalDateTime(rs.getString("initDate")));
                workoutRoutine.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(athleteFC, workoutRoutine));
                return workoutRoutine;
            }
            else {
                return null;
                //todo gestisci sto null
            }
        } catch (SQLException e) {
            CostumeLogger.getInstance().logError(e);
            return null;
        }
    }
    private LocalDateTime getLocalDateTime(String dateString){
        return LocalDateTime.parse(dateString);
    }

}
