package datamanagement;

public class ListStudentsControl
{
    private StudentManager studentManager_;

    public ListStudentsControl()
    {
        studentManager_ = StudentManager.getInstance();
    }



    public void listStudents(IStudentLister studentLister, String unitCode)
    {
        studentLister.clearStudents();
        StudentMap students = studentManager_.getStudentsByUnit(unitCode);
        for (Integer id : students.keySet()) {
            studentLister.addStudent(students.get(id));
        }
    }
}
