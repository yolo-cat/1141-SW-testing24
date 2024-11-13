package demo;

/**
 * 設計一個貸款利率計算程式，根據貸款年限、貸款金額、是否為青年及婚姻狀況計算利率；規則如下：
 * 基礎利率為 5%，貸款超過 10 年折扣 0.5%，超過 20 年折扣 1%；若申請者為青年則減少 1%，
 * 若已婚則再減少 0.5%；利率最低為 1%。
 */

public class LoanCalculator {

    public static double calculateInterestRate(int years, double amount, boolean isYouth, boolean isMarried) {
        double baseRate = 5.0; // 基礎利率為 5%

        // 年限折扣
        if (years > 20) {
            baseRate -= 1.0; // 貸款超過 20 年，折扣 1%
        } else if (years > 10) {
            baseRate -= 0.5; // 貸款超過 10 年，折扣 0.5%
        }

        // 青年優惠
        if (isYouth) {
            baseRate -= 1.0; // 青年，折扣 1%
        }

        // 已婚優惠
        if (isMarried) {
            baseRate -= 0.5; // 已婚，折扣 0.5%
        }

        // 確保利率不低於 1%
        return Math.max(baseRate, 1.0);
    }

    public static void main(String[] args) {
        int years = 15; // 貸款年限
        double amount = 500000; // 貸款金額
        boolean isYouth = true; // 是否為青年
        boolean isMarried = false; // 是否已婚

        double interestRate = calculateInterestRate(years, amount, isYouth, isMarried);
        System.out.println("最終貸款利率為: " + interestRate + "%");
    }
}
