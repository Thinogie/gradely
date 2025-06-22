# 🎓 Gradely – A Student Results Analyzing CLI Tool

**Gradely** is a clean,simple yet effective **Java-based command-line application** for managing and analyzing student performance data. 

---

## 🚀 Features

- ✅ **Add** student records manually or via `.txt` input
- 📊 **Sort** student records
- 🏅 **Top Performers** and complete results analysis
- 💾 **Export** individual reports as `.txt` files
- 🎨 **Styled CLI** with ANSI colors and clean interface design

---

## 🛠️ Tech Stack

| Category         | Technology              | Description                                                             |
|------------------|--------------------------|-------------------------------------------------------------------------|
| **Language**     | Java (8+)               | Core programming language used for CLI logic and OOP structure          |
| **Interface**    | Command Line (Terminal) | User interface powered by styled ANSI output in the terminal            |
| **Compilation**  | `javac`                 | Java compiler for compiling `.java` source files                        |
| **Execution**    | `java`                  | JVM runtime environment to execute the compiled class files             |
| **File Handling**| Java IO (`File`, `Scanner`, `PrintWriter`) | For reading `.txt` input files and writing export reports       |
| **Project Type** | Console Application     | Lightweight, cross-platform, no external libraries or dependencies      |
| **Build Tool**   | None                    | Manual build and execution (no Maven/Gradle needed)                     |


---

## 📂 Project Structure

```plaintext
Gradely/
│
├── src/
│   ├── Gradely_Results_Analyzer.java       # Main class (entry point)
│   ├── command_line_interface/
│   │   ├── CLI_Components.java             # Menus and user prompts
│   │   └── CLI_Styling.java                # ANSI styling and UI elements
│   ├── model/
│   │   └── Student.java                    # Student data model
│   └── service/
│       └── ResultsAnalyzer.java           # Logic for analysis, sorting, exporting
│
├── analysis_report_*.txt                  # Generated report files (on runtime)
├── README.md
└── .gitignore
