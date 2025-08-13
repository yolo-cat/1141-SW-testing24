import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer; // 引入 Consumer 介面

interface USATariffSubject {
    void addObserver(Consumer<Double> observer);
    void setTariff(double newTariff);
}

class USATariff implements USATariffSubject {
    private double tariff;
    private List<Consumer<Double>> observers;

    public USATariff(double initialTariff) {
        this.tariff = initialTariff;
        this.observers = new ArrayList<>();
        System.out.println("美國總統 Trump 初始關稅設定為: " + String.format("%.2f", tariff) + "%");
    }

    @Override
    public void addObserver(Consumer<Double> observer) {
        observers.add(observer);
        System.out.println("一個新的觀察者加入了關稅監控。");
    }

    private void notifyObservers(double tariffIncrease) {
        System.out.println("\n--- 通知所有觀察者 ---");
        for (Consumer<Double> observer : observers) {
            observer.accept(tariffIncrease); // 調用觀察者的 accept 方法，傳遞關稅漲幅
        }
    }

    public void setTariff(double newTariff) {
        if (newTariff < 0) {
            System.out.println("關稅不能為負值。");
            return;
        }
        if (newTariff != this.tariff) {
            double oldTariff = this.tariff;
            this.tariff = newTariff;
            double tariffIncrease = newTariff - oldTariff;
            System.out.println("\n關稅從 " + String.format("%.2f", oldTariff) + "% 變更為 " + String.format("%.2f", newTariff) + "%。");
            System.out.println("關稅漲幅: " + String.format("%.2f", tariffIncrease) + "%");
            notifyObservers(tariffIncrease);
        } else {
            System.out.println("關稅未改變，仍為 " + String.format("%.2f", this.tariff) + "%。");
        }
    }

    public double getTariff() {
        return tariff;
    }
}

// 3. Concrete Observers (具體觀察者) - 國家
class Country implements Consumer<Double> { // 直接實現 Consumer<Double>
    private String name;

    public Country(String name) {
        this.name = name;
    }

    @Override
    public void accept(Double tariffIncrease) { // 實現 accept 方法
        if (tariffIncrease > 10.0) {
            System.out.println(name + "：關稅漲幅超過 10%！我們抓狂了！");
        } else if (tariffIncrease > 0) {
            System.out.println(name + "：關稅有變動，我們正在密切關注。");
        }
    }

    public String getName() {
        return name;
    }
}

// 4. Concrete Observers (具體觀察者) - 公司
class Company implements Consumer<Double> { // 直接實現 Consumer<Double>
    private String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public void accept(Double tariffIncrease) { // 實現 accept 方法
        if (tariffIncrease > 10.0) {
            System.out.println(name + "公司：關稅漲幅超過 10%！我們超級生氣！");
        } else if (tariffIncrease > 5.0) {
            System.out.println(name + "公司：關稅漲幅超過 5%！我們很生氣！");
        } else if (tariffIncrease > 0) {
            System.out.println(name + "公司：關稅有微幅變動，我們正在評估影響。");
        }
    }

    public String getName() {
        return name;
    }
}

// 5. Client (客戶端程式碼)
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // 創建關稅主題
        USATariff usaTariff = new USATariff(0.0); // 初始關稅 0%

        // 創建國家和公司觀察者
        Country taiwan = new Country("台灣");
        Country japan = new Country("日本");
        Company tsmc = new Company("台積電");
        Company apple = new Company("蘋果");

        // 直接將觀察者實例添加到主題中
        usaTariff.addObserver(taiwan); // taiwan 本身就是 Consumer<Double>
        usaTariff.addObserver(japan);
        usaTariff.addObserver(tsmc);
        usaTariff.addObserver(apple);

        // 模擬關稅變化
        System.out.println("\n===== 關稅變動情境 1 =====");
        usaTariff.setTariff(6.0);

        System.out.println("\n===== 關稅變動情境 2：=====");
        usaTariff.setTariff(16.0);

        System.out.println("\n===== 關稅變動情境 3：=====");
        usaTariff.setTariff(20.0);

        System.out.println("\n===== 關稅變動情境 4： =====");
        usaTariff.setTariff(40.0);

        System.out.println("\n===== 關稅變動情境 5：=====");
        usaTariff.setTariff(8.0);
    }
}