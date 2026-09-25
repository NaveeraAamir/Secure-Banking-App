# 🏦 OOP Bank — Secure Bank Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/GUI-Java_Swing-blue?style=for-the-badge)
![Paradigm](https://img.shields.io/badge/Paradigm-OOP-success?style=for-the-badge)
![Status](https://img.shields.io/badge/Academic_Project-3rd_Semester-purple?style=for-the-badge)

## 📌 Overview
**OOP Bank** is a desktop-based Bank Management System developed in Java using **Object-Oriented Programming (OOP)** principles and **Java Swing** for the graphical user interface. The application provides a role-based environment with dedicated portals for **Bank Administrators** and **Customers (Users)**, backed by persistent local file storage.

This project was developed during the **3rd Semester** at the **Department of Computer Engineering, University of Engineering and Technology (UET)**.

---

## 🎓 Academic Details & Credits

* **Institution:** University of Engineering and Technology (UET)
* **Department:** Computer Engineering (CE)
* **Semester:** 3rd Semester
* **Course Instructor:** Dr. Hina
* **Developers:**
  * **Naveera Aamir**
  * **Afnan**

---

## ✨ Key Features

### 🛡️ Admin Portal
* **Secure Authentication:** Dedicated administrator login gateway.
* **Client Registration:** Register new clients with their personal details (Name, CNIC, Phone Number) and automatically open an initial bank account.
* **Dynamic Account & Client IDs:** Automatic generation of unique Client IDs (`CID-1`, `CID-2`, ...) and Account Numbers (`ACC-1001`, `ACC-1002`, ...).
* **Real-Time Search & Filtering:** Search customer records instantaneously using their **CNIC** or **Phone Number**.
* **Client Removal:** Delete client profiles along with all associated bank accounts.
* **Asset Tracking:** Automatically calculates and displays the **Total Bank Assets (PKR)** across all active accounts.

### 👤 User (Customer) Portal
* **Customer Login:** Secure authentication using the registered **CNIC** and **Phone Number**.
* **Interactive Dashboard:** Clean view of current account balance in PKR.
* **Cash Deposit & Withdrawal:** Instant balance updates with overdraft protection (prevents withdrawing more than the available balance).
* **Inter-Account Funds Transfer:** Seamlessly transfer funds to another customer's account using their Account Number (`ACC-XXXX`).
* **Transaction History:** Comprehensive audit log tracking initial deposits, withdrawals, deposits, and incoming/outgoing transfers.

### 💾 Data Persistence
* **Automated File Storage:** All client profiles, account balances, and transaction histories are automatically saved to and loaded from `bank_data.txt` upon every transaction or state change.

---

## 🧠 Core Concepts & Technologies Used

### 1. Object-Oriented Programming (OOP) Concepts
* **Encapsulation:**
  * All entity attributes in `Person`, `Client`, `Account`, and `Bank` are declared `private` and accessed safely through public getter and mutator methods, protecting data integrity.
* **Composition & Aggregation (Has-A Relationships):**
  * `Bank` **has** a collection of `Client` and `Account` objects.
  * `Client` **has** a `Person` object (`personDetails`) and a list of `Account` objects (`acList`).
  * `Account` maintains a reference to its `Client` holder (`acHolder`) and a list of transaction history strings.
* **Abstraction:**
  * Complex operations such as file serialization (`FileHandler.saveAllData()`) and account lookups (`Bank.searchAccount()`) are abstracted away from the GUI layer.
* **Polymorphism (Method Overriding):**
  * Overriding the `toString()` method in `Person` and `Client` classes (`@Override`) to provide formatted string representations of object states.
* **Static Members:**
  * Use of `static` counters (`Client.count` and `Account.count`) shared across all instances to generate sequential, unique IDs for clients and accounts.
* **Inheritance:**
  * `BankSystemGUI` extends `javax.swing.JFrame` to inherit window properties, lifecycle behavior, and container capabilities.

### 2. Data Structures & Java Collections Framework
* **`ArrayList` & `List` Interface:** Used extensively to manage dynamic collections of clients (`List<Client>`), accounts (`List<Account>`), and transaction logs (`List<String>`).

### 3. Graphical User Interface (Java Swing & AWT)
* **Multi-Screen Navigation (`CardLayout`):** Smooth switching between the Landing Page, Admin Login, User Login, Admin Dashboard, and User Dashboard within a single `JFrame`.
* **Responsive Layouts:** Combination of `GridBagLayout`, `BorderLayout`, `GridLayout`, and `FlowLayout` for structured UI design.
* **Tabular Data Rendering:** `JTable` paired with `DefaultTableModel` and `JScrollPane` for dynamic display and filtering of customer records.
* **Event-Driven Programming:** Utilization of `ActionListener` and **Java 8 Lambda Expressions** (`e -> { ... }`) for handling button clicks and user interactions.
* **Thread Safety:** Application launched on the Event Dispatch Thread (EDT) via `SwingUtilities.invokeLater()`.

### 4. File Handling & Exception Handling
* **File I/O (`java.io` & `java.util.Scanner`):**
  * Uses `PrintWriter` and `FileWriter` to serialize object states into a structured pipe-delimited (`|`) and semicolon-delimited (`;`) text format.
  * Uses `File` and `Scanner` to parse `bank_data.txt` and reconstruct objects in memory on startup.
* **Exception Handling & Resource Management:**
  * Implements `try-with-resources` blocks to ensure file streams close automatically and catches `IOException` and parsing exceptions gracefully.

---

## 📂 Project Architecture

```text
📦 OOP-Bank-System
 ┣ 📜 Person.java          # Entity class storing personal details (Name, CNIC, Phone)
 ┣ 📜 Client.java          # Client model linking a Person to their Bank Accounts
 ┣ 📜 Account.java         # Account model handling balance, deposits, withdrawals & history
 ┣ 📜 Bank.java            # Controller managing collections of Clients and Accounts
 ┣ 📜 FileHandler.java     # Utility class for reading/writing data to bank_data.txt
 ┣ 📜 BankSystemGUI.java   # Main class containing the Java Swing GUI and entry point
 ┣ 📜 bank_data.txt        # Auto-generated local database file (created at runtime)
 ┗ 📜 README.md            # Project documentation
```

---

## 🚀 Getting Started

### Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher installed on your machine.

### Installation & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/oop-bank-system.git
   cd oop-bank-system
   ```

2. **Compile the Java source files:**
   ```bash
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java BankSystemGUI
   ```

---

## 🔐 Default Credentials

### Admin Access
* **Admin ID:** `admin`
* **Password:** `admin`

### User Access
* First, log in via the **Admin Access** portal and register a new client with a **CNIC**, **Phone Number**, and **Initial Deposit**.
* Then, navigate to **User Access** and log in using:
  * **CNIC:** *(The CNIC registered by the Admin)*
  * **Phone:** *(The Phone Number registered by the Admin)*

---

## 🤝 Acknowledgements
Special thanks to our course instructor **Dr. Hina** at the **Department of Computer Engineering, UET**, for her guidance and mentorship throughout the 3rd-semester Object-Oriented Programming course.
