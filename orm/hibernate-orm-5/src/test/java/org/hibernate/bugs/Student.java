/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sukhvir
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User implements Serializable, Comparable<Student> {

    @Column(name = "roll_number")
    private int rollNumber;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "mac_id")
    private String macId;

    @Column(name = "unaccounted")
    private boolean unaccounted;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_fid", foreignKey = @ForeignKey(name = "student_foreign_key"))
    @Getter
    @Setter
    private ClassRoom classRoom;*/

    @Column(name = "verified")
    private boolean verified;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();


   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subject_link",
            joinColumns = @JoinColumn(name = "student_fid"),
            inverseJoinColumns = @JoinColumn(name = "subject_fid"),
            foreignKey = @ForeignKey(name = "student_subject_link_student_foreign_key"),
            inverseForeignKey = @ForeignKey(name = "student_subject_link_subject_foreign_key"))
    @Getter
    @Setter
    private Set<Subject> subjects = new HashSet<>();*/

    public Student() {
    }

    public Student(int rollNumber, String fName, String lName, String username, String password, String email, long number) {
        // super(username, password, email, number);
        this.rollNumber = rollNumber;
        this.fName = fName;
        this.lName = lName;
       // addClassRoom(classRoom);
        setVerified(false);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public boolean isUnaccounted() {
        return unaccounted;
    }

    public void setUnaccounted(boolean unaccounted) {
        this.unaccounted = unaccounted;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void unaccount() {
        if (!verified) {
            unaccounted = true;
        }
    }

    /**
     * this method adds the subject to the student the subject to the student
     *
     * @param subject subject to be added
     */
   /* public void addSubject(Subject subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
            subject.getStudents().add(this);
        }
    }

    *//**
     * this methods adds the student to the classroom the classroom to the
     * student
     *
     * @param classRoom classroom to be added
     *//*
    final public void addClassRoom(ClassRoom classRoom) {
        classRoom.addStudent(this);
    }
*/
    @Override
    public String toString() {
        return fName + " " + lName;
    }

    @Override
    public int compareTo(Student student) {
        return this.getRollNumber() < student.getRollNumber() ? -1 : (this.getRollNumber() == student.getRollNumber()) ? 0 : 1;
    }


    // @Override
    public final UserType getUserType() {
        return UserType.Student;
    }


}
