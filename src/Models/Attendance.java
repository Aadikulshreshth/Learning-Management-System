package Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

    private Student student;
    private Course course;

    // Tracks attendance by specific dates to prevent double-counting
    private Map<LocalDate, Boolean> dailyRecords;

    public Attendance(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.dailyRecords = new HashMap<>();
    }

    public void markPresent() {
        // Puts today's date in the map with a value of 'true' (Present)
        // If today already exists, it simply overwrites it.
        dailyRecords.put(LocalDate.now(), true);
    }

    public void markAbsent() {
        // Puts today's date in the map with a value of 'false' (Absent)
        dailyRecords.put(LocalDate.now(), false);
    }

    // Original method updated to calculate from the map
    public int getPercentage() {
        if (dailyRecords.isEmpty()) return 0;

        // Count how many 'true' values are in the map
        long attendedClasses = dailyRecords.values().stream().filter(isPresent -> isPresent).count();
        int totalClasses = dailyRecords.size();

        return (int) ((attendedClasses * 100) / totalClasses);
    }

    public int getAttendancePercentage() {
        return getPercentage();
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    // Added these just in case you need to display the raw numbers in your UI later!
    public int getTotalClasses() {
        return dailyRecords.size();
    }

    public int getAttendedClasses() {
        return (int) dailyRecords.values().stream().filter(isPresent -> isPresent).count();
    }
}