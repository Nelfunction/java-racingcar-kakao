package racing_car.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class Racing {

    private Car[] cars;
    private int round;

    public Racing(){}
    public Racing(String names, int round) {
        createCars(names);
        setRound(round);
    }

    public void createCars(String testNames) {
        createCars(testNames, new int[testNames.split(",").length]);
    }

    public void createCars(String testNames, int[] distances) {
        String[] names = testNames.split(",");
        cars = new Car[names.length];
        for (int i = 0; i < names.length; i++) {
            cars[i] = new Car(names[i], distances[i]);
        }
    }

    public void setRound(int round) {
        this.round = round;
    }


    public Car[] getCars() {
        return cars;
    }
    public boolean isEnd() {
        return this.round == 0;
    }

    public Car[] getWinners() {
        Car maxDistance = Arrays.stream(cars).max(Comparator.naturalOrder()).get();
        return Arrays.stream(cars)
                .filter(car -> car.compareTo(maxDistance) == 0)
                .collect(Collectors.toList())
                .toArray(Car[]::new);
    }

    public int generateValue() {
        return new Random().nextInt(10);
    }

    public void race() {
        if (round == 0){
            return;
        }

        for (Car car: cars) {
            car.proceed(generateValue());
        }

        round--;
    }
}
