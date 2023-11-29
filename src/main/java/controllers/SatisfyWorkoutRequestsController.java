package controllers;

import beans.*;
import javafx.scene.control.ListView;
import model.Athlete;
import model.Exercise;
import model.Request;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class SatisfyWorkoutRequestsController {

    //TODO da risistemare; occhio che da un punto di vista di svolgimento di codice questa classe
    // deve scambiare i dati da bean a model

    private final WorkoutRoutineBean workoutRoutine;
    private final Trainer trainer;
    private final List<Exercise> exerciseList;

    public List<ExerciseBean> getGymExerciseBean() {
        System.out.println(exerciseList);
        return getExerciseBeanList(exerciseList);
    }

    public SatisfyWorkoutRequestsController() {
        this.workoutRoutine = new WorkoutRoutineBean();

        this.trainer = new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("alecortix@gmail.com", "forzanapule1926"))
        /*(Trainer) new LoginController().getLoggedUser()*/;
        System.out.println(this.trainer.getName() + this.trainer.getEmail());
        this.exerciseList = new ArrayList<>();
    }

    public SatisfyWorkoutRequestsController(Trainer trainer, List<Exercise> exerciseList) {
        this.workoutRoutine = new WorkoutRoutineBean();
        this.trainer = trainer;
        this.exerciseList = exerciseList;
    }

    public void addExerciseToWorkoutDay(ExerciseForWorkoutRoutineBean exercise, ListView<ExerciseForWorkoutRoutineBean> RoutineExerciselist)  {
        RoutineExerciselist.getItems().add(exercise);

        // Check if the WorkoutDayBean for the current day already exists
        Optional<WorkoutDayBean> existingWorkoutDay = workoutRoutine.getWorkoutDayList().stream()
                .filter(workoutDay -> workoutDay.getName().equals(exercise.getDay()))
                .findFirst();

        if (existingWorkoutDay.isPresent()) {
            existingWorkoutDay.get().addExerciseBean(exercise);
        } else {
            // If WorkoutDayBean doesn't exist, create a new one and add the exercise to it
            WorkoutDayBean newWorkoutDay = new WorkoutDayBean(exercise.getDay());
            newWorkoutDay.addExerciseBean(exercise);
            workoutRoutine.addWorkoutDayBean(newWorkoutDay);
        }
        // Additional code if needed
    }

    public void setExerciseStatus(){
        //TODO
    }

    public void submitRoutine(RequestBean request) {
        //TODO sistema poi il metodo con atleta in questione e invio scheda
        //Archivia la scheda vecchia
        //salva la nuova scheda
        //elimina la richiesta
        //notifica l'atleta
        for (WorkoutDayBean workoutDay : workoutRoutine.getWorkoutDayList()) {
            String dayName = workoutDay.getName();

            // Iterate through each ExerciseForWorkoutRoutineBean in the WorkoutDayBean
            for (ExerciseForWorkoutRoutineBean exercisePrint : workoutDay.getExerciseList()) {
                ExerciseBean exerciseName = exercisePrint.getExercise();
                int repetitionsP = exercisePrint.getRepetitions();
                int setsP = exercisePrint.getSets();
                String restP = exercisePrint.getRest();
            }
        }
    }

    public List<ExerciseBean> searchExercise(SearchBean searchBean) {
        System.out.println(searchBean.getName());
        List<Exercise> exerciseList = new ArrayList<>();
        for(Exercise exercise: exerciseList) {
            System.out.println(exercise.getName());
            if((exercise.getName().toLowerCase()).contains(searchBean.getName().toLowerCase())) {
                exerciseList.add(exercise);
            }
        }
        return getExerciseBeanList(exerciseList);
    }

    @NotNull
    public List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            ExerciseStatusBean ex = SatisfyWorkoutRequestsController.getExerciseStatusBeanFromExercise(exercise);
            exerciseBeanList.add(new ExerciseBean(exercise.getName(), ex));
        }
        return exerciseBeanList;
    }

    public static ExerciseStatusBean getExerciseStatusBeanFromExercise(Exercise exercise) {
        return switch (exercise.getStatus()) {
            case ACTIVE -> ExerciseStatusBean.ACTIVE;
            case SUSPENDED -> ExerciseStatusBean.SUSPENDED;
            default ->
                // Handle other cases or throw an exception
                    null;
        };
    }

    public WorkoutDayBean getWorkoutDay(WorkoutDayBean day) {
        //ragiona solo con le bean
        for(WorkoutDayBean workoutDay: workoutRoutine.getWorkoutDayList()){
            if(Objects.equals(workoutDay, day)) {
                return workoutDay;
            }
        }
        return null;
    }

    public void removeExerciseFromDayExercisesMap(ExerciseForWorkoutRoutineBean exercise, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        // Iterate through the map and remove the exercise for the corresponding day
        dayExercisesMap.forEach((day, exercises) -> exercises.removeIf(e -> e.equals(exercise)));
    }

    public void removeExerciseToWorkoutDay(ExerciseBean selectedExercise, ListView<ExerciseForWorkoutRoutineBean> routineExerciselist, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        // Create a copy of the routineExerciselist items to avoid ConcurrentModificationException
        List<ExerciseForWorkoutRoutineBean> copyList = new ArrayList<>(routineExerciselist.getItems());

        for (ExerciseForWorkoutRoutineBean item : copyList) {
            if (selectedExercise.equals(item.getExercise())) {
                routineExerciselist.getItems().remove(item);

                // Remove the exercise from the dayExercisesMap
                removeExerciseFromDayExercisesMap(item,dayExercisesMap);

                break; // Exit the loop once the item is removed
            }
        }
    }

    public void setStatus(ExerciseBean exercise, String status){
        if(status.equals("active")){
            exercise.setStatusExercise(ExerciseStatusBean.ACTIVE);
        } else {
            exercise.setStatusExercise(ExerciseStatusBean.SUSPENDED);
        }
    }

    public List<RequestBean> getTrainerRequests() /*throws SQLException, DBUnreachableException*/ {
        List<Request> requestList = new ArrayList<>()/*new RequestDAO().loadTrainerRequests(trainer)*/;

        List<RequestBean> requestBeanList = new ArrayList<>();
        for(Request request: requestList) {
            Athlete usr = request.getAthlete();
            AthleteBean athleteBean;
            /*try {*/
                athleteBean = new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFC(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ));
           /* } catch (NoCardInsertedException e) {
                athleteBean = new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                null,
                                (YearMonth) null
                        ));
            }*/
            requestBeanList.add(new RequestBean(
                    request.getId(),
                    request.getRequestDate(),
                    request.getInfo(),
                    athleteBean,
                    request.getTrainer().getFC()
            ));

        }
        return requestBeanList;
    }
}
