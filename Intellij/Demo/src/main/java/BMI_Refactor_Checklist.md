# 📝 修正建議清單（BMI.java）

## 1. 封裝與結構
- [ ] 加入 package 宣告：所有類別必須屬於一個命名的 package（目前缺少）。
- [ ] Utility class 建構子設為 private：若此類別僅提供靜態方法，應避免被實例化。

## 2. 註解與文件化
- [ ] 類別註解：在類別上方補充 Javadoc，描述用途與功能。
- [ ] 公開方法/建構子註解：為 main 方法與建構子補上 Javadoc，說明參數與行為。

## 3. 變數與參數修飾
- [ ] 方法參數宣告為 final：weight、heightCm。
- [ ] 區域變數宣告為 final：heightM、scanner、weight、heightCm、bmi。

## 4. 輸出處理
- [ ] 避免使用 System.out/err：第 13、15、19 行。 建議改用 logging framework（如 java.util.logging 或 SLF4J），以便更好地控制輸出層級與格式。

---

## 🔧 建議修改順序
1. 結構性修正 → package 與 utility class 建構子
2. 文件化 → 類別與方法註解
3. 程式碼品質 → final 修飾符
4. 最佳實務 → 改用 logging 取代 System.out.println

