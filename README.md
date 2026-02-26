# 🎓 Learning Management System (LMS)

---

## 📌 About the Project

This project aims to build a simple Learning Management System using Java and Object-Oriented Programming.

The goal is to create a structured digital environment where:

- Instructors can manage courses and assignments
- Students can participate in academic activities
- Academic performance can be tracked beyond just exams

Instead of treating learning as isolated tasks, the system models how academic processes actually work — including submissions, attendance, and multiple forms of evaluation.

---

## 🎯 Why This System?

In many academic settings, student performance and course activities are often tracked using disconnected tools such as spreadsheets or manual records.

These methods:

- Do not offer centralized tracking
- Focus mostly on exam scores
- Make it difficult to monitor participation

This LMS provides a more structured and integrated approach to managing academic workflows.

---

## 👥 Target Users

- 👨‍🏫 **Instructors** – Manage courses and evaluate students
- 👨‍🎓 **Students** – Enroll, submit work, and track performance

---

## ✨ Key Features (Phase 1)

- Role-based academic system
- Course participation workflow
- Assignment submission tracking
- Attendance monitoring
- Multi-component performance evaluation

This version is a console-based prototype focused on system design.

---

## 🧠 System Design

The LMS is built using Object-Oriented Programming to reflect real academic roles and relationships.

### 🔹 User Roles

Base class:

`User`

Derived classes:

- `Student`
- `Instructor`

This allows shared properties while enabling role-specific behavior.

---

### 🔹 Academic Components

The system includes:

- Courses
- Assignments
- Submissions
- Attendance

Each represents a different part of academic participation.

---

### 🔹 Evaluation Approach

Instead of relying on a single score, student performance is evaluated across:

- Tests
- Assignments
- Exams
- Attendance

This allows performance to reflect both participation and outcomes.

---

## 📂 Project Structure

```text
LMS/
│
├── models/
│   ├── User.java
│   ├── Student.java
│   ├── Instructor.java
│   ├── Course.java
│   ├── Assignment.java
│   ├── Submission.java
│   └── Attendance.java
│
├── grading/
│   ├── Evaluator.java
│   ├── TestGrading.java
│   ├── AssignmentGrading.java
│   ├── ExamGrading.java
│   └── AttendanceGrading.java
│
├── core/
│   └── LMSManager.java
│
├── exceptions/
│   ├── LMSException.java
│   ├── UserNotFoundException.java
│   ├── CourseNotFoundException.java
│   ├── DeadlineException.java
│   └── UnauthorizedAccessException.java
│
├── main/
│   └── LMSApp.java
│
└── README.md

---

## 📍 Current Phase

**Phase 1 – System Design**

Focus areas:

- Defining system structure
- Designing role relationships
- Planning evaluation model

Implementation will follow in later phases.

---

## 🚀 Future Direction

Possible future additions include:

- Database support
- Interface-based deployment
- More detailed analytics

---

## 🎓 Purpose

This project demonstrates:

- Application of OOP concepts
- Modeling of real academic workflows
- Designing systems with future scalability in mind
