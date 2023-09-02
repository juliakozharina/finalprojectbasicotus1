package data;

import lombok.Getter;

@Getter
public enum MainMenuItemsData {
  Courses("Курсы"),
  Events("События");

  private String name;

  MainMenuItemsData(String name) {
    this.name = name;
  }

}
