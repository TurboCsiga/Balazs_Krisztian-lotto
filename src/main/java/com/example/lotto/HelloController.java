package com.example.lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class HelloController {

    @FXML
    private Label txtLotto;

    @FXML
    private Label txtList;

    @FXML
    private Button btnSorsol;

    private Timer timer;
    private List<Integer> numbers = new ArrayList<>();
    private int rand = 0;
    private boolean boolee = false;

    @FXML
    public void sorsolBtnClick() {
        if (!boolee) {
            txtList.setText("");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> { sorsol(); });
                }
            }, 0, 150);
        } else {
            Collections.sort(numbers);
            txtList.setText("");
            for (int num : numbers) {
                addToNumList(num);
            }
            reset();
        }
    }

    private void sorsol() {
        if (rand < 50) {
            int num = (int) (Math.random() * 90 + 1);
            txtLotto.setText(num + "");
            rand++;
            if (rand % 10 == 0) {
                while (numbers.contains(num)) {
                    num = (int) (Math.random() * 90 + 1);
                    txtLotto.setText(num + "");
                }
                numbers.add(num);
                addToNumList(num);
            }
        } else {
            timer.cancel();
            btnSorsol.setText("Rendez");
            boolee = true;
        }
    }

    private void reset() {
        boolee = false;
        btnSorsol.setText("Sorsol");
        rand = 0;
        numbers.clear();
    }

    private void addToNumList(int num) {
        String text = txtList.getText();
        if (text.length() == 0) text += num + "";
        else text += ", " + num;
        txtList.setText(text);
    }
}