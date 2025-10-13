package demo;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 一個用於計算身體質量指數 (BMI) 的工具類別。
 * 此類別提供一個靜態方法來計算 BMI，以及一個主方法可透過使用者輸入從命令列執行計算。
 */
public class BmiAfterPMD {
  private static final Logger LOGGER = Logger.getLogger(BmiAfterPMD.class.getName());

  /**
   * 私有建構子，以防止此工具類別被實例化。
   */
  private BmiAfterPMD() {}

  /**
   * 根據體重和身高計算身體質量指數 (BMI)。
   *
   * @param weight 體重（公斤）。
   * @param heightCm 身高（公分）。
   * @return 計算出的 BMI 值。
   */
  public static double calculate(final double weight, final double heightCm) {
    final double heightM = heightCm / 100.0;
    return weight / (heightM * heightM);
  }

  /**
   * 應用程式的主要進入點。
   * 此方法會提示使用者輸入體重和身高，計算 BMI，並記錄結果。
   *
   * @param args 命令列參數（未使用）。
   */
  public static void main(final String[] args) {
    // 使用 try-with-resources 語句以確保 scanner 能自動關閉
    try (final Scanner scanner = new Scanner(System.in)) {

      LOGGER.info("請輸入體重(公斤): ");
      final double weight = scanner.nextDouble();
      LOGGER.info("請輸入身高(公分): ");
      final double heightCm = scanner.nextDouble();

      final double bmi = BmiAfterPMD.calculate(weight, heightCm);
      // 使用 String.format 來準備要記錄的訊息
      LOGGER.info(String.format("您的BMI值為: %.2f", bmi));
    }
  }
}
