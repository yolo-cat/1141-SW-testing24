
// Component Interface (組件介面)
interface SnowmanComponent {
    String sing();
}

// Concrete Component (具體組件)
class ConcreteSnowman implements SnowmanComponent {
    @Override
    public String sing() {
        return "Hello, 我是快樂的雪人";
    }
}

// Abstract Decorator (抽象裝飾器)
abstract class SnowmanDecorator implements SnowmanComponent {
    protected SnowmanComponent decoratedSnowman;

    public SnowmanDecorator(SnowmanComponent decoratedSnowman) {
        this.decoratedSnowman = decoratedSnowman;
    }

    @Override
    public String sing() {
        return decoratedSnowman.sing();
    }
}

// Concrete Decorators (具體裝飾器)
class ScarfDecorator extends SnowmanDecorator {
    public ScarfDecorator(SnowmanComponent decoratedSnowman) {
        super(decoratedSnowman);
    }

    @Override
    public String sing() {
        return super.sing() + "，我有溫暖的圍巾";
    }
}

class HatDecorator extends SnowmanDecorator {
    public HatDecorator(SnowmanComponent decoratedSnowman) {
        super(decoratedSnowman);
    }

    @Override
    public String sing() {
        return super.sing() + "，我戴著漂亮的帽子";
    }
}

class GlovesDecorator extends SnowmanDecorator {
    public GlovesDecorator(SnowmanComponent decoratedSnowman) {
        super(decoratedSnowman);
    }

    @Override
    public String sing() {
        return super.sing() + "，我的手套很暖和";
    }
}

public class SnowmanDecoratorDemo {
    public static void main(String[] args) {
        // 創建一個基本雪人
        SnowmanComponent basicSnowman = new ConcreteSnowman();
        System.out.println("基本雪人唱歌: " + basicSnowman.sing());

        System.out.println("--------------------");

        // 創建一個戴圍巾的雪人
        SnowmanComponent snowmanWithScarf = new ScarfDecorator(basicSnowman);
        System.out.println("戴圍巾的雪人唱歌: " + snowmanWithScarf.sing());

        System.out.println("--------------------");

        // 創建一個戴帽子和圍巾的雪人 (多個裝飾器疊加)
        SnowmanComponent snowmanWithHatAndScarf = new HatDecorator(new ScarfDecorator(basicSnowman));
        System.out.println("戴帽子和圍巾的雪人唱歌: " + snowmanWithHatAndScarf.sing());

        System.out.println("--------------------");

        // 創建一個戴帽子、圍巾和手套的雪人
        SnowmanComponent snowmanFullyDressed = new GlovesDecorator(new HatDecorator(new ScarfDecorator(basicSnowman)));
        System.out.println("全副武裝的雪人唱歌: " + snowmanFullyDressed.sing());

        System.out.println("--------------------");

        // 也可以先創建一個戴帽子的雪人，然後再給他加手套
        SnowmanComponent snowmanWithHat = new HatDecorator(basicSnowman);
        SnowmanComponent snowmanWithHatAndGloves = new GlovesDecorator(snowmanWithHat);
        System.out.println("先戴帽子再戴手套的雪人唱歌: " + snowmanWithHatAndGloves.sing());
    }
}