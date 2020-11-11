package racing.controller;

import racing.model.RacingCars;

import racing.view.InputView;
import racing.view.ResultView;

import java.util.Scanner;

public class RacingController {
    private static final String ILLEGAL_RETRY_COUNT = "유효하지 않은 시도 횟수 입니다.";

    private Scanner scanner = new Scanner(System.in);

    private InputView inputView = new InputView();
    private ResultView resultView = new ResultView();

    private RacingCars cars;

    private int retryCount;

    public void start() {
        inputCandidates();
        inputRetryCount();
        run();
        scanner.close();
    }

    private void inputCandidates() {
        inputView.printCandidates();
        cars = new RacingCars(scanner.nextLine());
    }

    private void inputRetryCount() {
        inputView.printRetryCount();
        retryCount = getRetryCount();
    }

    private void run() {
        resultView.printResultMessage();
        for (int retry = 0; retry < retryCount; retry++) {
            cars.racing();
            resultView.printRacingResult(cars);
        }
        resultView.printWinner(cars);
    }

    private int getRetryCount() {
        return inputIntValue(ILLEGAL_RETRY_COUNT);
    }

    private int inputIntValue(String errorMessage) {
        int input = scanner.nextInt();

        if (isNotPositiveInt(input)) {
            throw new IllegalArgumentException(errorMessage);
        }

        return input;
    }

    private Boolean isNotPositiveInt(int input) {
        return 0 > input;
    }

}
