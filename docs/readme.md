
# Unit testing

### Introduction to Unit Testing

**Unit testing** is a software testing method where individual components of a software application are tested in isolation. The primary goal of unit testing is to validate that each unit of the software performs as expected. Units can be functions, methods, or classes, depending on the programming language and framework.

### Popular Unit Testing Frameworks

1. **JUnit (Java)**
   - **Overview**: JUnit is one of the most popular unit testing frameworks for Java. It provides annotations to identify test methods, assertions to validate test outcomes, and the ability to run tests automatically.
   - **Key Features**:
     - **Annotations**: `@Test`, `@Before`, `@After`, etc., to define test cases and setup/teardown methods.
     - **Assertions**: Methods like `assertEquals`, `assertTrue`, and `assertThrows` to check expected outcomes.
     - **Test Runners**: Integrated with build tools like Maven and Gradle to run tests automatically.
   - **Example**:
     ```java
     import org.junit.Test;
     import static org.junit.Assert.assertEquals;

     public class CalculatorTest {
         @Test
         public void testAdd() {
             Calculator calc = new Calculator();
             int result = calc.add(2, 3);
             assertEquals(5, result);
         }
     }
     ```

* [Junit tutorial (tutorialspoint)]https://www.tutorialspoint.com/junit/index.htm
* [Lab readme](../unit_test/java_test/readme_java.md)

2. **Jest (JavaScript)**
   - **Overview**: Jest is a widely-used testing framework for JavaScript, particularly in React applications. It comes with a test runner, assertions, and mocking capabilities out of the box, making it easy to set up and use.
   - **Key Features**:
     - **Zero Configuration**: Works out of the box with minimal setup.
     - **Snapshot Testing**: Allows capturing the rendered output of components and comparing them to previous snapshots.
     - **Mocking**: Built-in support for mocking functions and modules.
   - **Example**:
     ```javascript
     const sum = require('./sum');

     test('adds 1 + 2 to equal 3', () => {
         expect(sum(1, 2)).toBe(3);
     });
     ```

* [Jest tutorial (jestjs)](https://jestjs.io/)
* [Lab readme](../unit_test/js_test/readme_jest.md)

1. **Pytest (Python)**
   - **Overview**: Pytest is a powerful testing framework for Python that supports simple unit tests as well as complex functional testing. It’s known for its simple syntax, detailed reporting, and powerful fixture system.
   - **Key Features**:
     - **Fixtures**: Reusable setup/teardown code that can be shared across tests.
     - **Plugins**: Extensible via plugins, including those for Django, Flask, and more.
     - **Parameterization**: Run a test function multiple times with different arguments.
   - **Example**:
     ```python
     def add(a, b):
         return a + b

     def test_add():
         assert add(2, 3) == 5
     ```
* [Pytest tutorial (tutorialspoint)](https://www.tutorialspoint.com/pytest/index.htm)
* [Lab readme](../unit_test/python_test/readme_pytest.md)

### Summary

Unit testing is an essential practice in software development, ensuring that individual components work correctly. JUnit, Jest, and Pytest are three popular frameworks across Java, JavaScript, and Python, respectively. Each framework offers unique features tailored to the needs of its language and ecosystem, making it easier for developers to write, run, and maintain tests.

# Integration testing

# End to End testing

# Paper study


* "Software testing with large language models: Survey, landscape, and vision." IEEE Transactions on Software Engineering (2024).
  * 20 pages, 
  * [IEEE link](https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=10440574) | 
  * [local](./paper%20study/T12%20LLM.pdf)



### **Thota2020** 

Thota, Mahesh Kumar, Francis H. Shajin, and P. Rajesh. "Survey on software defect prediction techniques." International Journal of Applied Science and Engineering 17.4 (2020): 331-344.

Recent advancements in technology have emerged the requirements of hardware and software applications. Along with this technical growth, software industries also have faced drastic growth in the demand for software for several applications. For any software industry, developing good quality software and maintaining its eminence for the user end is considered the most important task for software industrial growth. In order to achieve this, software engineering plays an important role for software industries. Software applications are developed with the help of computer programming where codes are written for the desired task. Generally, these codes contain some faulty instances which may lead to buggy software development caused by software defects. In the field of software engineering, software defect prediction is considered the most important task, which can be used for maintaining the quality of software. Defect prediction results provide the list of defect-prone source code artifacts so that the quality assurance team can effectively allocate limited resources for validating software products by putting more effort into the defect-prone source code. As the size of software projects becomes larger, defect prediction techniques will play an important role in supporting developers as well as speeding up time to market with more reliable software products. One of the most exhaustive and pricey parts of embedded software development is considered the process of finding and fixing the defects. Due to complex infrastructure, magnitude, cost, and time limitations, monitoring and fulfilling the quality is a big challenge, especially in automotive embedded systems. However, meeting superior product quality and reliability is mandatory. Hence, higher importance is given to V&V (Verification & Validation). Software testing is an integral part of software V&V, which is focused on promising accurate functionality and long-term reliability of software systems. Simultaneously, software testing requires as much effort, cost, infrastructure, and expertise as development. The costs and efforts elevate in safety-critical software systems. Therefore, it is essential to have a good testing strategy for any industry with high software development costs. In this work, we are planning to develop an efficient approach for software defect prediction by using soft computing-based machine learning techniques that help to optimize the features and efficiently learn the features.

---

### Baltes2022
Baltes, Sebastian, and Paul Ralph. "Sampling in software engineering research: A critical review and guidelines." Empirical Software Engineering 27.4 (2022): 94.

Representative sampling appears rare in empirical software engineering research. Not all studies need representative samples, but a general lack of representative sampling undermines a scientific field. This article therefore reports a critical review of the state of sampling in recent, high-quality software engineering research. The key findings are: (1) random sampling is
rare; (2) sophisticated sampling strategies are very rare; (3) sampling, representativeness and randomness often appear misunderstood. These findings suggest that software engineering research has a generalizability crisis. To address these problems, this paper synthesizes existing knowledge of sampling into a succinct primer and proposes extensive guidelines for improving the conduct, presentation and evaluation of sampling in software engineering research. It is further recommended that while researchers should strive for more representative samples, disparaging non-probability sampling is generally capricious and particularly misguided for predominately qualitative research.

### Barr2014
Barr, Earl T., et al. "The oracle problem in software testing: A survey." IEEE transactions on software engineering 41.5 (2014): 507-525.

### McMinn2011
McMinn, Phil. "Search-based software testing: Past, present and future." 2011 IEEE Fourth International Conference on Software Testing, Verification and Validation Workshops. IEEE, 2011.

**Search-Based Software Testing** is the use of a meta-heuristic optimizing search technique, such as a Genetic Algorithm, to automate or partially automate a testing task; for example the automatic generation of test data. Key to the optimization process is a problem-specific fitness function. The role of the fitness function is to guide the search to good solutions from a potentially infinite search space, within a practical time limit. Work on Search-Based Software Testing dates back to 1976, with interest in the area beginning to gather pace in the 1990s. More recently there has been an explosion of the amount of work. This paper reviews past work and the current state of the art, and discusses potential future research areas and open problems that remain in the field.

### Jalil2023

Jalil, Sajed, et al. "Chatgpt and software testing education: Promises & perils." 2023 IEEE international conference on software testing, verification and validation workshops (ICSTW). IEEE, 2023.

[local](./paper%20study/Jalil2023.pdf)

Over the past decade, predictive language modeling for code has proven to be a valuable tool for enabling new forms of automation for developers. More recently, we have seen the advent of general purpose “large language models”, based on neural transformer architectures, that have been trained on massive datasets of human written text, which includes code and natural language. However, despite the demonstrated representational power of such models, interacting with them has historically been constrained to specific task settings, limiting their general applicability. Many of these limitations were recently overcome with the introduction of ChatGPT, a language model created by OpenAI and trained to operate as a conversational agent, enabling it to answer questions and respond to a wide variety of commands from end users. The introduction of models, such as ChatGPT, has already spurred fervent discussion from educators, ranging from fear that students could use these AI tools to circumvent learning, to excitement about the new types of learning opportunities that they might unlock. However, given the nascent nature of these tools, we currently lack fundamental knowledge related to how well they perform in different educational settings, and the potential promise (or danger) that they might pose to traditional forms of instruction. As such, in this paper, we examine **how well ChatGPT performs when tasked with answering common questions in a popular software testing curriculum**. We found that given its current capabilities, ChatGPT is able to respond to **77.5%** of the questions we examined and that, of these questions, it is able to provide correct or partially correct answers in 55.6% of cases, provide correct or partially correct explanations of answers in 53.0% of cases, and that prompting the tool in a shared question context leads to a marginally higher rate of correct answers and explanations. Based on these findings, we discuss the potential promises and perils related to the use of ChatGPT by students and instructor

### T12 Software testing with large language models: Survey, landscape, and vision

Pre-trained large language models (LLMs) have recently emerged as a breakthrough technology in natural language processing and artificial intelligence, with the ability to handle large-scale datasets and exhibit remarkable performance across a wide range of tasks. Meanwhile, software testing is a crucial undertaking that serves as a cornerstone for ensuring the quality and reliability of software products. As the scope and complexity of software systems continue to grow, the need for more effective software testing techniques becomes increasingly urgent, and making it an area ripe for innovative approaches such as the use of LLMs. This paper provides a comprehensive review of the utilization of LLMs in software testing. It analyzes 52 relevant studies that have used LLMs for software testing, from both the software testing and LLMs perspectives. The paper presents a detailed discussion of the software testing tasks for which LLMs are commonly used, among which test case preparation and program repair are the most representative ones. It also analyzes the commonly used LLMs, the types of prompt engineering that are employed, as well as the accompanied techniques with these LLMs. It also summarizes the key challenges and potential opportunities in this direction. This work can serve as a roadmap for future research in this area, highlighting potential avenues for exploration, and identifying gaps in our current understanding of the use of LLMs in software testing
