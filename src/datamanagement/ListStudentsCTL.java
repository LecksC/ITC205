package datamanagement;

import java.util.HashMap;

public class ListStudentsCTL
{
    private StudentManager studentManager_;

    public ListStudentsCTL()
    {
        studentManager_ = StudentManager.getInstance();
    }



    public void listStudents(IStudentLister studentLister, String unitCode)
    {
        studentLister.clearStudents();
        HashMap<Integer, IStudent> students = studentManager_.getStudentsByUnit(unitCode);
        for (Integer studentId : students.keySet()) {
            studentLister.addStudent(students.get(studentId));
        }
    }
}
