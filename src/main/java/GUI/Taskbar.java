package main.java.GUI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


import java.io.File;

public class Taskbar {
    public Label timer;
    public Label score;
    public Label liver;
    public Label high;
    private ImageView background;
    public MenuBar menu;
    public Menu file, help;
    public MenuItem saveGame, quit, about;
    public Group createTaskbar() {
        menu = new MenuBar();
        menu.setPrefHeight(26.0);
        menu.setPrefWidth(672.0);

        //Tạo các menu
         file = new Menu("File");
         help = new Menu("Help");

        // tạo các menu item
         saveGame = new MenuItem("Save Game");
         quit = new MenuItem("Quit");
         about = new MenuItem("About");

        // thêm các menuitem vào menu
        file.getItems().addAll(saveGame,quit);
        help.getItems().addAll(about);
        menu.getMenus().addAll(file, help);


        Image image = new Image(new File("res/taskbar.png").toURI().toString());
        background = new ImageView();
        background.setLayoutY(0);
        background.setLayoutY(26);
        background.setFitWidth(672.0);
        background.setFitHeight(80.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);
        background.setImage(image);

        timer = new Label();
        timer.setLayoutX(275.0);
        timer.setLayoutY(40.0);
        timer.setPrefHeight(35.0);
        timer.setPrefWidth(62.0);
        timer.setText("100");
        timer.setTextFill(Paint.valueOf("#fdfafa"));
        timer.setFont(Font.font("Calibri Bold",32.0));

        score = new Label();
        score.setLayoutY(38.0);
        score.setLayoutX(56.0);
        score.setPrefWidth(174.0);
        score.setPrefHeight(35.0);
        score.setText("180");
        score.setTextFill(Paint.valueOf("#fdfafa"));
        score.setFont(Font.font("Calibri Bold",39.0));

        liver = new Label();
        liver.setLayoutY(40.0);
        liver.setLayoutX(401.0);
        liver.setPrefWidth(28);
        liver.setPrefHeight(33);
        liver.setTextFill(Paint.valueOf("#fdfafa"));
        liver.setFont(Font.font("Calibri Bold",35.0));
        liver.setText(">");

        high = new Label();
        high.setLayoutX(480);
        high.setLayoutY(40.0);
        high.setPrefHeight(33);
        high.setTextFill(Paint.valueOf("#fdfafa"));
        high.setFont(Font.font("Calibri Bold",35.0));
        high.setText("2:2");

        Group group = new Group();
        group.getChildren().addAll(menu,background,timer,score,high,liver);

        return group;
    }

    public String getTimer() {
        return timer.getText();
    }

    public void setTimer(String time) {
        this.timer.setText(time);
    }

    public String getScore() {
        return score.getText();
    }

    public void setScore(String score) {
        this.score.setText(score);
    }
}
