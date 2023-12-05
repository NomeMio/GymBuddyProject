package model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private final String name;
    private final Gym gym;
    private ExerciseStatus status;

    public Exercise(String name, Gym gym, ExerciseStatus status){
        //ideato SOLO per passare ora i parametri (che non ho il DB)
        this.name = name;
        this.gym = gym;
        this.status = status;
    }

    public Exercise(String name, Gym gym){
        //ideato per essere usato alla creazione di un esercizio
        this.name = name;
        this.gym = gym;
        this.status=ExerciseStatus.ACTIVE;
    }

    public String getName() {
        return name;
    }
    public Gym getGym() {
        return gym;
    }

    public ExerciseStatus getStatus() {
        return status;
    }

    public void setStatus(ExerciseStatus status) {
        this.status = status;
    }
}
