---
marp: true
size: 16:9
paginate: true
footer: "本教材與 Gemini Pro 共同編輯"
---

## **I. 引言：軟體除錯的挑戰與 AI 的潛力**

### **傳統除錯的挑戰**

* **耗時且複雜**：開發人員近 50% 時間用於除錯 1。  
* **手動且重複**：逐行檢查、變數追蹤、日誌分析等 2。  
* **規模與複雜度**：大型應用程式、並行程式碼除錯難度高 2。  
* **經濟與人力成本**：降低開發效率，推高專案成本 3。

---

### **AI 在軟體開發中的演進**

* **早期**：2000 年代末，僅為「高級自動完成」功能 4。  
* **中期**：2015-2017 年，神經網路進步，AI 能識別複雜程式碼模式 4。  
* **現今**：應用普及，涵蓋測試、除錯、文件生成、架構決策 4。  
* **廣泛採用**：預計 2025 年，97.5% 公司將採用 AI 技術 5。

---

### **AI 在軟體測試與除錯的概覽**

* **核心能力**：透過學習和經驗，提升準確性和效率 3。  
* **測試階段**：  
  * 自動生成測試案例 4。  
  * 預測測試失敗 4。  
  * 自動修復輕微測試錯誤 4。  
  * 提升測試覆蓋率，減少 50% 測試時間 6。  
* **除錯階段**：  
  * 提高除錯準確性，減少人為錯誤 6。  
  * 檢測安全漏洞並修復 3。  
  * 自動化與增強檢測的協同效應 3。

---

## **II. AI 除錯的核心技術與方法**

---

### **A. 機器學習在錯誤預測與檢測中的應用**

#### **監督式學習演算法**

* **目標**：在開發早期預測潛在軟體缺陷，提高品質、可靠性、效率，降低修復成本 8。  
* **常用演算法**：樸素貝葉斯 (NB)、決策樹 (DT)、人工神經網路 (ANNs)、支持向量機 (SVM)、隨機森林 (RF) 3。  
* **應用**：分析歷史錯誤數據和軟體度量指標，學習缺陷模式 8。  
* **效益**：實現主動式品質管理，策略性分配除錯資源 8。

---

#### **非監督式學習演算法**

* **目標**：識別不符合預期模式的數據點、項目或事件 11。  
* **優勢**：處理未標記數據，檢測訓練數據中未出現過的新型異常 12。  
* **常用演算法**：Isolation Forest (孤立森林)、Local Outlier Factor (LOF) 11。  
* **應用**：分析系統日誌，檢測程式執行時間異常增長 12。

---

### **B. 深度學習與自然語言處理在除錯中的應用**

#### **程式碼語義理解與模式識別**

* **能力**：自動發現錯誤並提供潛在解決方案 3。  
* **深度學習進步**：從簡單自動完成到識別複雜程式碼模式 4。  
* **自動程式修復 (APR)**：  
  * 視為神經機器翻譯 (NMT) 任務 13。  
  * 學習從有缺陷程式碼到正確修復程式碼的「翻譯」13。  
  * 程式碼表示為異構圖，捕捉結構與語義關係 15。  
* **效益**：彌合程式碼語法與語義鴻溝，理解程式預期行為 4。

---

#### **錯誤報告的智慧分析**

* **挑戰**：錯誤報告為非結構化自然語言，難以自動化利用。  
* **NLP 解決方案**：分析用戶評論和錯誤報告，提取關鍵信息 3。  
* **機器學習應用**：自動理解、提取和關聯錯誤報告信息 16。  
* **分析技術**：  
  * 句子可信度評估 (SVM 分類器) 17。  
  * 句子間凝聚力評估 (TextRank 演算法、句子嵌入) 17。  
* **大型語言模型 (LLMs)**：  
  * 彌合錯誤報告與原始程式碼之間的「上下文差距」1。  
  * 透過「智能相關性回饋」改進錯誤定位精確性 1。  
* **效益**：從非結構化數據中解鎖人類洞察，加速除錯過程 3。

---

#### **自動程式修復 (APR) 的原理與進展**

* **目標**：自動化軟體錯誤修復過程，減輕開發人員負擔 18。  
* **深度學習 APR**：  
  * 將程式修復視為神經機器翻譯 (NMT) 任務 13。  
  * 學習從海量開源程式碼庫中學習錯誤修復模式 13。  
* **通用工作流程**：故障定位、補丁生成、補丁排序、補丁驗證、補丁正確性評估 13。  
* **LLMs 興起**：  
  * 直接生成補丁，提供更廣泛的補丁多樣性 19。  
  * 部分採用自主代理概念，自主規劃和執行修復步驟 19。  
* **挑戰**：  
  * 有限的補丁空間，難以生成複雜補丁 20。  
  * 缺乏對程式深層依賴性的全面感知 20。  
  * 開發人員仍需手動檢查每個 AI 生成補丁的正確性，避免「看似合理但未正確修復」的補丁 19。

---

### **C. 大型語言模型 (LLMs) 在 Java 程式碼除錯中的應用**

#### **程式碼生成、自動補全與除錯輔助**

* **加速開發**：自動化常規編程任務 4。  
* **實用工具**：GitHub Copilot、Cursor、Codium AI 提供實時程式碼建議、單元測試生成 4。  
* **Java 除錯**：ChatGPT 等 LLMs 能處理簡單 Java 開發任務，包括除錯 22。  
* **錯誤檢測**：LLMs 在程式碼理解和檢測 Java 命名錯誤方面有潛力 23。  
* **專用工具**：DebugGPT 利用 GPT 模型分析程式碼，識別錯誤，建議優化方案，提供解釋 2。  
* **效益**：提供即時回饋、錯誤高亮顯示、程式碼建議，簡化除錯流程 24。

---

#### **上下文感知與對話式除錯**

* **能力**：提供上下文感知和對話式除錯 2。  
* **上下文敏感建議**：根據程式碼環境、變數作用域調整建議 4。  
* **彌合差距**：LLMs 彌合錯誤報告與程式碼之間的「上下文差距」，提供精確問題定位 1。  
* **協作對話**：ChatDBG 等工具允許開發人員與除錯器進行對話，協助根本原因分析 26。  
* **持續學習**：AI 工具透過學習過去程式碼庫和除錯會話，不斷改進建議和修復能力 26。  
* **效益**：從靜態分析轉變為動態、互動式問題解決，使除錯更直觀高效 4。

---

### **D. Java 特定除錯模型與工具的理論基礎 (JADE)**

* **核心理論**：JADE (AI Support for Debugging Java Programs) 以「模型化診斷」(Model-Based Diagnosis, MBD) 為核心 28。  
* **原理**：將程式編譯成邏輯描述，診斷引擎計算潛在錯誤位置並建議修復 28。  
* **模型構建**：直接從 Java 原始程式碼派生模型，無需額外規範 28。  
* **功能依賴模型**：JADE 第一個原型採用此模型，編碼變數間的因果關係 28。  
* **處理複雜性**：  
  * 靜態分析近似計算依賴集，考慮選擇與循環語句 28。  
  * 為引用類型變數分配輔助記憶體位置，處理遞歸方法採用「不動點計算」28。  
* **診斷引擎**：基於 Reiter 演算法，結合 de Kleer 和 Williams 的測量選擇演算法 28。  
* **與 LLMs 比較**：儘管 LLMs 在簡單 Java 任務中成功 22，但在複雜問題上仍有不足 22，突顯 JADE 等專用工具的優勢。

---

## **III. AI 除錯所需的資料類型與分析基礎**

---

### **A. 關鍵資料來源**

#### **程式碼指標與靜態分析數據**

* **程式碼指標**：衡量程式碼品質、複雜度和結構的量化數據 (如 LOC、圈複雜度、物件導向度量) 8。  
* **靜態分析**：不執行程式碼，檢查原始程式碼檢測問題 29。  
  * 識別安全漏洞、運行時錯誤、邏輯不一致 29。  
  * 例如 FindBugs 識別 Java 程式設計錯誤 29。  
* **效益**：早期錯誤檢測，避免問題複雜化和成本增加 30。

---

#### **動態執行追蹤與日誌**

* **動態分析**：程式碼運行時行為信息，診斷運行時錯誤和效能問題 30。  
* **日誌文件**：異常檢測的數據來源，例如程式執行時間異常增長 12。  
* **運行時狀態**：觀察變數值、追蹤函數呼叫堆疊 2。  
* **AI 能力**：分析動態執行路徑和歷史數據，記錄錯誤發生前變數值，進行根本原因分析 27。  
* **版本控制系統**：如 Git，追蹤程式碼變化，提供錯誤引入點和修復模式的上下文 32。  
* **效益**：結合靜態分析與動態執行，實現更全面的問題診斷 2。

---

#### **錯誤報告、版本控制歷史與使用者回饋**

* **用戶評論**：透過 NLP 分析，提取缺陷信息 3。  
* **錯誤報告**：機器學習模型分析，自動理解、提取和關聯錯誤類型、嚴重程度、重現步驟 16。  
* **LLMs 處理**：彌合錯誤報告與原始程式碼之間的「上下文差距」，提供精確除錯信息 1。  
* **版本控制歷史**：記錄程式碼演進軌跡，提供錯誤模式、修復原因、錯誤引入點的上下文 32。  
* **歷史錯誤數據**：訓練錯誤預測模型的基礎 8。  
* **效益**：突顯人機協作和歷史上下文在 AI 除錯中的重要性 3。

---

### **B. 資料預處理與特徵工程的挑戰**

#### **程式碼的有效表示方法**

* **異構圖**：先進的程式碼表示方法，捕捉結構和語義關係 (節點為實體，邊為關係) 15。  
* **傳統 NLP 技術**：Word2Vec、TF-IDF 用於錯誤報告分析，但對程式碼語法語義有局限 16。  
* **深度學習模型**：Transformer 等模型能更好處理程式碼序列性和結構性特徵，但計算複雜度高，需大量訓練數據。  
* **資料預處理**：清理數據、消除不相關信息、格式化，確保數據品質與演算法兼容 33。

---

## **IV. 結論**

---

### **AI 除錯的潛力與挑戰**

* **潛力**：  
  * 提升效率、降低成本 3。  
  * 透過數據分析和模式識別，發現人類難以察覺的問題 3。  
  * 實現錯誤預測、檢測、定位及自動修復 3。  
  * LLMs 提供上下文感知和對話式除錯 2。  
  * JADE 等專用模型提供嚴謹理論基礎 28。  
* **挑戰**：  
  * 數據稀缺性 。  
  * 模型可解釋性 (「黑箱」問題) 。  
  * 真實世界錯誤的複雜性 。  
  * AI 難以進行深層邏輯推斷和上下文理解 。  
  * 「看似合理但未正確修復」的補丁問題，需人工驗證 19。

---

### **未來展望**

* 提升 AI 模型推理能力。  
* 增強模型可解釋性。  
* 開發更魯棒的數據集和訓練方法。  
* 將 AI 視為開發人員的「智能助手」，而非完全替代品 。  
* 透過人機協作，持續提升軟體品質和開發效率。

---

#### **引用的著作**

1. \[2501.10542\] Improved IR-based Bug Localization with Intelligent Relevance Feedback, 檢索日期：8月 13, 2025， [https://arxiv.org/abs/2501.10542](https://arxiv.org/abs/2501.10542)  
2. DebugGPT: AI-Powered Code Debugger and Optimizer using RAG – IJERT, 檢索日期：8月 13, 2025， [https://www.ijert.org/debuggpt-ai-powered-code-debugger-and-optimizer-using-rag](https://www.ijert.org/debuggpt-ai-powered-code-debugger-and-optimizer-using-rag)  
3. (PDF) Exploring the Use of Artificial Intelligence for Software Testing and Debugging \- ResearchGate, 檢索日期：8月 13, 2025， [https://www.researchgate.net/publication/377816057\_Exploring\_the\_Use\_of\_Artificial\_Intelligence\_for\_Software\_Testing\_and\_Debugging](https://www.researchgate.net/publication/377816057_Exploring_the_Use_of_Artificial_Intelligence_for_Software_Testing_and_Debugging)  
4. AI for Software Development: Benefits, Challenges, and Tools to Know \- Shakuro, 檢索日期：8月 13, 2025， [https://shakuro.com/blog/ai-for-software-development](https://shakuro.com/blog/ai-for-software-development)  

---

5. AI in Software Development 2025: From Exploration to Accountability – Survey-Based Analysis | Techreviewer Blog, 檢索日期：8月 13, 2025， [https://techreviewer.co/blog/ai-in-software-development-2025-from-exploration-to-accountability-a-global-survey-analysis](https://techreviewer.co/blog/ai-in-software-development-2025-from-exploration-to-accountability-a-global-survey-analysis)  
6. The Impact of AI in Software Testing \- Aspire Systems, 檢索日期：8月 13, 2025， [https://www.aspiresys.com/impact-of-ai-in-software-testing/](https://www.aspiresys.com/impact-of-ai-in-software-testing/)  
7. AI in Software Testing: Advantages, Disadvantages, Contributions & the Future \- Magnitia, 檢索日期：8月 13, 2025， [https://magnitia.com/blog/ai-in-software-testing-advantages-disadvantages-contributions-the-future/](https://magnitia.com/blog/ai-in-software-testing-advantages-disadvantages-contributions-the-future/)  
8. (PDF) Software Bug Prediction using Machine Learning Approach \- ResearchGate, 檢索日期：8月 13, 2025， [https://www.researchgate.net/publication/323536716\_Software\_Bug\_Prediction\_using\_Machine\_Learning\_Approach](https://www.researchgate.net/publication/323536716_Software_Bug_Prediction_using_Machine_Learning_Approach)  

---

9.  An Empirical Study of Bugs in Machine Learning Systems \- ResearchGate, 檢索日期：8月 13, 2025， [https://www.researchgate.net/publication/239831975\_An\_Empirical\_Study\_of\_Bugs\_in\_Machine\_Learning\_Systems](https://www.researchgate.net/publication/239831975_An_Empirical_Study_of_Bugs_in_Machine_Learning_Systems)  
10. Software Bug Prediction Using Machine Learning Approach \- JAC : A JOURNAL OF COMPOSITION THEORY(JCT), 檢索日期：8月 13, 2025， [https://jctjournal.com/wp-content/uploads/24-aug2023.pdf](https://jctjournal.com/wp-content/uploads/24-aug2023.pdf)  
11. Anomaly Detection using Unsupervised Techniques \- Kaggle, 檢索日期：8月 13, 2025， [https://www.kaggle.com/code/sabanasimbutt/anomaly-detection-using-unsupervised-techniques](https://www.kaggle.com/code/sabanasimbutt/anomaly-detection-using-unsupervised-techniques)  
12. ANOMALY DETECTION TECHNIQUES FOR UNSUPERVISED MACHINE LEARNING \- DiVA portal, 檢索日期：8月 13, 2025， [https://www.diva-portal.org/smash/get/diva2:1674371/FULLTEXT01.pdf](https://www.diva-portal.org/smash/get/diva2:1674371/FULLTEXT01.pdf)  

---

13. \[2301.03270\] A Survey of Learning-based Automated Program Repair \- arXiv, 檢索日期：8月 13, 2025， [https://arxiv.org/abs/2301.03270](https://arxiv.org/abs/2301.03270)  
14. A Survey of Learning-based Automated Program Repair \- ResearchGate, 檢索日期：8月 13, 2025， [https://www.researchgate.net/publication/366983828\_A\_Survey\_of\_Learning-based\_Automated\_Program\_Repair](https://www.researchgate.net/publication/366983828_A_Survey_of_Learning-based_Automated_Program_Repair)  
15. Self-Supervised Bug Detection and Repair \- OpenReview, 檢索日期：8月 13, 2025， [https://openreview.net/pdf?id=zOngaSKrElL](https://openreview.net/pdf?id=zOngaSKrElL)  
16. \[2507.04422\] Learning Software Bug Reports: A Systematic Literature Review \- arXiv, 檢索日期：8月 13, 2025， [https://arxiv.org/abs/2507.04422](https://arxiv.org/abs/2507.04422)  

---

17. Deep Learning-Based Bug Report Summarization Using Sentence Significance Factors, 檢索日期：8月 13, 2025， [https://www.mdpi.com/2076-3417/12/12/5854](https://www.mdpi.com/2076-3417/12/12/5854)  
18. Automated Program Repair \- squaresLab, 檢索日期：8月 13, 2025， [https://squareslab.github.io/materials/legoues-cacm2019.pdf](https://squareslab.github.io/materials/legoues-cacm2019.pdf)  
19. CodeLLMPaper/data/papers/labels/program\_repair.md at main \- GitHub, 檢索日期：8月 13, 2025， [https://github.com/PurCL/CodeLLMPaper/blob/main/data/papers/labels/program\_repair.md](https://github.com/PurCL/CodeLLMPaper/blob/main/data/papers/labels/program_repair.md)  
20. Automated Repair of Programs from Large Language Models, 檢索日期：8月 13, 2025， [https://people.cs.umass.edu/\~brun/class/2024Fall/CS692P/aprllm.pdf](https://people.cs.umass.edu/~brun/class/2024Fall/CS692P/aprllm.pdf)  

---

21. 20 Best AI Coding Assistant Tools in 2025 \- Qodo, 檢索日期：8月 13, 2025， [https://www.qodo.ai/blog/best-ai-coding-assistant-tools/](https://www.qodo.ai/blog/best-ai-coding-assistant-tools/)  
22. Java coding using artificial intelligence \- Frontiers, 檢索日期：8月 13, 2025， [https://www.frontiersin.org/journals/computer-science/articles/10.3389/fcomp.2024.1473870/full](https://www.frontiersin.org/journals/computer-science/articles/10.3389/fcomp.2024.1473870/full)  
23. Evaluating ChatGPT's Ability to Detect Naming Bugs in Java Methods \- SciTePress, 檢索日期：8月 13, 2025， [https://www.scitepress.org/Papers/2025/133544/133544.pdf](https://www.scitepress.org/Papers/2025/133544/133544.pdf)  
24. Debug AI Code: 10 Tips to Solve Common Errors \- Phaedra Solutions, 檢索日期：8月 13, 2025， [https://www.phaedrasolutions.com/blog/debug-ai-code](https://www.phaedrasolutions.com/blog/debug-ai-code)  

---

25. Top 20 AI Testing and Debugging Tools | BrowserStack, 檢索日期：8月 13, 2025， [https://www.browserstack.com/guide/ai-debugging-tools](https://www.browserstack.com/guide/ai-debugging-tools)  
26. AI-powered debugging tools \- Graphite, 檢索日期：8月 13, 2025， [https://graphite.dev/guides/ai-powered-debugging-tools](https://graphite.dev/guides/ai-powered-debugging-tools)  
27. AI Bug Fixing Tools That Help You Catch and Fix Errors Faster \- Ful.io, 檢索日期：8月 13, 2025， [https://ful.io/blog/ai-bug-fixing-tools](https://ful.io/blog/ai-bug-fixing-tools)  
28. (PDF) JADE \- AI support for debugging Java programs \- ResearchGate, 檢索日期：8月 13, 2025， [https://www.researchgate.net/publication/2597676\_JADE\_-\_AI\_support\_for\_debugging\_Java\_programs](https://www.researchgate.net/publication/2597676_JADE_-_AI_support_for_debugging_Java_programs)  

---

29. Experiences Using Static Analysis to Find Bugs \- Google Research, 檢索日期：8月 13, 2025， [https://research.google.com/pubs/archive/34339.pdf](https://research.google.com/pubs/archive/34339.pdf)  
30. Static vs. dynamic code analysis: A comprehensive guide \- vFunction, 檢索日期：8月 13, 2025， [https://vfunction.com/blog/static-vs-dynamic-code-analysis/](https://vfunction.com/blog/static-vs-dynamic-code-analysis/)  
31. Why You Need Static Analysis, Dynamic Analysis, and Machine Learning?, 檢索日期：8月 13, 2025， [https://www.paloaltonetworks.com/cyberpedia/why-you-need-static-analysis-dynamic-analysis-machine-learning](https://www.paloaltonetworks.com/cyberpedia/why-you-need-static-analysis-dynamic-analysis-machine-learning)  
32. Version Control for Machine Learning and Data Science \- Neptune.ai, 檢索日期：8月 13, 2025， [https://neptune.ai/blog/version-control](https://neptune.ai/blog/version-control)  

---

33. How Automatic AI Test Case Generation is Revolutionizing Software Testing, 檢索日期：8月 13, 2025， [https://www.aufieroinformatica.com/en/automatic-ai-test-case-generation/](https://www.aufieroinformatica.com/en/automatic-ai-test-case-generation/)