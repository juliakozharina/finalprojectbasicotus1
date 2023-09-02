package data;

import lombok.Getter;

@Getter
public enum CoursesData {
  Testing("Тестирование");

  private String name;

  CoursesData(String name) {
    this.name = name;
  }

}
