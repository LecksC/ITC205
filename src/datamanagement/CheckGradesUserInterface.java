package datamanagement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CheckGradesUserInterface 
extends JFrame 
implements IUnitLister,	IStudentLister 
{
	private static final long serialVersionUID = 5712125091485034830L;
	
	private cgCTL checkGradesControl_;
	
	private float assignment1Mark_;
	private float assignment2Mark_;
	private float examMark_;	
	private Integer studentId_;	
	
	private DefaultComboBoxModel<String> unitComboBoxModel_;
	private DefaultComboBoxModel<String> studentComboBoxModel_;
	private JButton changeButton_;
	private JButton checkGradeButton_;
	private JButton saveButton_;
	private JComboBox<String> unitComboBox_;
	private JComboBox<String> studentComboBox_;
	private JLabel titleLabel_;
	private JLabel assignment1MarkLabel_;
	private JLabel assignment2MarkLabel_;
	private JLabel examMarkLabel_;
	private JLabel gradeLabel_;
	private JLabel errorLabel_;
	private JPanel unitPanel_;
	private JPanel studentPanel_;
	private JPanel marksPanel_;
	private JPanel gradePanel_;
	private JTextField assignment1MarkTextField_;
	private JTextField assignment2MarkTextField_;
	private JTextField examMarkTextField_;
	
	
	
	public CheckGradesUserInterface(cgCTL control) 
	{
		checkGradesControl_ = control;
		unitComboBoxModel_ = new DefaultComboBoxModel<String>(new String[0]);
		studentComboBoxModel_ = new DefaultComboBoxModel<String>(new String[0]);
		initializeComponents();
		unitComboBox_.setModel(unitComboBoxModel_);
		studentComboBox_.setModel(studentComboBoxModel_);
		errorLabel_.setText("");
	}

	
	
    public void clearUnits() 
    {
        unitComboBoxModel_.removeAllElements();
        unitComboBoxModel_.addElement("<none selected>");
        clearStudents();
    }
    
    

    public void addUnit(IUnit unit) 
    {
        unitComboBoxModel_.addElement(unit.getUnitCode());
    }

    
    
    public void setUnitComboBoxEnabledAndClearError(boolean enabled) 
    {
        unitComboBox_.setEnabled(enabled);
        errorLabel_.setText("");
    }

    
    
    public void clearStudents() 
    {
        studentComboBoxModel_.removeAllElements();
        studentComboBoxModel_.addElement("<none selected>");
    }

    
    
    public void addStudent(IStudent student) 
    {
        studentComboBoxModel_.addElement(student.getID().toString() + 
                                         " : " + student.getFirstName() + " " + student.getLastName());
    }

    
    
    public void setStudentComboEnabledAndClearError(boolean enabled)
    {
        studentComboBox_.setEnabled(enabled);
        errorLabel_.setText("");
    }

    
    
    public void setStudentUnitRecord(IStudentUnitRecord record) 
    {
        assignment1MarkTextField_.setText(new Float(record.getAssignment1Mark()).toString());
        assignment2MarkTextField_.setText(new Float(record.getAssignment2Mark()).toString());
        examMarkTextField_.setText(new Float(record.getExamMark()).toString());
        gradeLabel_.setText("");
    }

    
    
    public void clearStudentUnitRecords() 
    {
        assignment1MarkTextField_.setText("");
        assignment2MarkTextField_.setText("");
        examMarkTextField_.setText("");
        gradeLabel_.setText("");
        errorLabel_.setText("");
        assignment1MarkTextField_.setEditable(false);
        assignment2MarkTextField_.setEditable(false);
        examMarkTextField_.setEditable(false);
    }

    
    
    public void setCheckGradeButtonEnabled(boolean enabled) 
    {
        checkGradeButton_.setEnabled(enabled);
    }

    
    
    public void setChangeButtonEnabled(boolean enabled) 
    {
        changeButton_.setEnabled(enabled);
    }

    
    
    public void setMarksEditable(boolean enabled) 
    {
        assignment1MarkTextField_.setEditable(enabled);
        assignment2MarkTextField_.setEditable(enabled);
        examMarkTextField_.setEditable(enabled);
    }

    
    
    public void setSaveEnabled(boolean enabled) 
    {
        saveButton_.setEnabled(enabled);
    }
    
    
	
	private void initializeComponents() 
	{
		titleLabel_ = new JLabel();
		unitPanel_ = new JPanel();
		unitComboBox_ = new JComboBox<String>();
		studentPanel_ = new JPanel();
		studentComboBox_ = new JComboBox<String>();
		marksPanel_ = new JPanel();
		assignment1MarkLabel_ = new JLabel();
		assignment2MarkLabel_ = new JLabel();
		examMarkLabel_ = new JLabel();
		assignment1MarkTextField_ = new JTextField();
		assignment2MarkTextField_ = new JTextField();
		examMarkTextField_ = new JTextField();
		changeButton_ = new JButton();
		gradePanel_ = new JPanel();
		gradeLabel_ = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		titleLabel_.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		titleLabel_.setText("Check Grade UI");

		unitPanel_.setBorder(BorderFactory.createTitledBorder("Unit"));

		unitComboBox_.setModel(unitComboBoxModel_);
		
		unitComboBox_.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				selectedUnitChanged(event);
			}
		});
		
		GroupLayout groupUnit = new GroupLayout(unitPanel_);

		unitPanel_.setLayout(groupUnit);
		
		groupUnit.setHorizontalGroup(groupUnit.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
						groupUnit
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(unitComboBox_,
								GroupLayout.PREFERRED_SIZE, 185,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupUnit.setVerticalGroup(groupUnit.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
						groupUnit
						.createSequentialGroup()
						.addComponent(unitComboBox_,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		studentPanel_.setBorder(BorderFactory.createTitledBorder("Student"));

		studentComboBox_.setModel(studentComboBoxModel_);
		studentComboBox_.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				selectedStudentChanged(event);
			}
		});

		GroupLayout groupStudent = new GroupLayout(studentPanel_);
		
		studentPanel_.setLayout(groupStudent);
		groupStudent.setHorizontalGroup(groupStudent.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupStudent
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(studentComboBox_,
								GroupLayout.PREFERRED_SIZE, 185,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupStudent.setVerticalGroup(groupStudent.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupStudent
						.createSequentialGroup()
						.addComponent(studentComboBox_,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		marksPanel_.setBorder(BorderFactory.createTitledBorder("Marks"));

		assignment1MarkLabel_.setText("Asg1:");

		assignment2MarkLabel_.setText("Asg2:");

		examMarkLabel_.setText("Exam:");

		assignment1MarkTextField_.setEditable(false);
		assignment1MarkTextField_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		assignment2MarkTextField_.setEditable(false);
		assignment2MarkTextField_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		examMarkTextField_.setEditable(false);
		examMarkTextField_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		changeButton_.setText("Change");
		changeButton_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changePressed(event);
			}
		});
		
		checkGradeButton_ = new JButton();
		checkGradeButton_.setText("Check Grade");
		checkGradeButton_.setActionCommand("checkGrade");
		checkGradeButton_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkGradePressed(event);
			}
		});

		GroupLayout groupMarks = new GroupLayout(marksPanel_);
		groupMarks.setHorizontalGroup(
			groupMarks.createParallelGroup(Alignment.LEADING)
				.addGroup(groupMarks.createSequentialGroup()
					.addGroup(groupMarks.createParallelGroup(Alignment.LEADING)
						.addGroup(groupMarks.createSequentialGroup()
							.addContainerGap()
							.addComponent(assignment1MarkLabel_)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(assignment1MarkTextField_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(assignment2MarkLabel_))
						.addGroup(groupMarks.createSequentialGroup()
							.addGap(85)
							.addComponent(changeButton_, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupMarks.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupMarks.createSequentialGroup()
							.addComponent(assignment2MarkTextField_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(examMarkLabel_))
						.addComponent(checkGradeButton_))
					.addGap(18)
					.addComponent(examMarkTextField_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		
		groupMarks.setVerticalGroup(
			groupMarks.createParallelGroup(Alignment.LEADING)
				.addGroup(groupMarks.createSequentialGroup()
					.addGroup(groupMarks.createParallelGroup(Alignment.BASELINE)
						.addComponent(assignment1MarkLabel_)
						.addComponent(assignment1MarkTextField_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignment2MarkLabel_)
						.addComponent(assignment2MarkTextField_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(examMarkLabel_)
						.addComponent(examMarkTextField_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupMarks.createParallelGroup(Alignment.BASELINE)
						.addComponent(changeButton_)
						.addComponent(checkGradeButton_))
					.addContainerGap())
		);
		marksPanel_.setLayout(groupMarks);

		gradePanel_.setBorder(BorderFactory.createTitledBorder("Grade"));

		gradeLabel_.setFont(new Font("Tahoma", 0, 24)); // NOI18N
		gradeLabel_.setForeground(new Color(255, 0, 0));
		gradeLabel_.setHorizontalAlignment(SwingConstants.CENTER);
		gradeLabel_.setText("grade");

		GroupLayout groupGrade = new GroupLayout(gradePanel_);
		gradePanel_.setLayout(groupGrade);
		groupGrade.setHorizontalGroup(groupGrade.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				gradeLabel_, GroupLayout.Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE));
		groupGrade.setVerticalGroup(groupGrade.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupGrade.createSequentialGroup().addGap(34, 34, 34)
						.addComponent(gradeLabel_)
						.addContainerGap(43, Short.MAX_VALUE)));
		
		errorLabel_ = new JLabel();
		errorLabel_.setText("Error message");
		errorLabel_.setForeground(Color.RED);
		errorLabel_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		saveButton_ = new JButton();
		saveButton_.setText("Save");
		saveButton_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				jButton2ActionPerformed(event);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(errorLabel_, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(marksPanel_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(unitPanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(studentPanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(gradePanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup()
							.addGap(157)
							.addComponent(titleLabel_))
						.addGroup(layout.createSequentialGroup()
							.addGap(165)
							.addComponent(saveButton_, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel_)
					.addGap(13)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(unitPanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentPanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(gradePanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(marksPanel_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveButton_)
					.addGap(11)
					.addComponent(errorLabel_, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(layout);

		pack();
	}

	
	
	private void selectedUnitChanged(ItemEvent event) {
		String selectedUnit = (String) unitComboBox_.getSelectedItem();
		clearStudentUnitRecords();
		clearStudents();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (selectedUnit.equals((String) unitComboBox_.getItemAt(0))) {
				selectedUnit = "NONE";
			}
			checkGradesControl_.unitSelected(selectedUnit);
		}
	}
	
	

	private void selectedStudentChanged(ItemEvent event) 
	{
		clearStudentUnitRecords();
		String selectedStudent = (String) studentComboBox_.getSelectedItem();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (selectedStudent.equals((String) studentComboBox_.getItemAt(0))) {
				studentId_ = 0;
				checkGradesControl_.studentSelected(studentId_);
			} 
			else {
				studentId_ = new Integer(selectedStudent.split("\\s")[0]);
			}
			checkGradesControl_.studentSelected(studentId_);
		}
	}

	
	
	private void checkGradePressed(ActionEvent event) 
	{
		assignment1Mark_ = new Float(assignment1MarkTextField_.getText()).floatValue();
		assignment2Mark_ = new Float(assignment2MarkTextField_.getText()).floatValue();
		examMark_ = new Float(examMarkTextField_.getText()).floatValue();
		try {
			String gradeText = checkGradesControl_.checkGrade(assignment1Mark_, assignment2Mark_, examMark_);
			gradeLabel_.setText(gradeText);
		}
		catch (RuntimeException exception) {
			errorLabel_.setText(exception.getMessage());
		}
	}

	
	
	private void changePressed(ActionEvent event) 
	{
		checkGradesControl_.enableChangeMarks();
		gradeLabel_.setText("");
	}

	
	
	private void jTextFieldKeyTyped(KeyEvent event) 
	{
		gradeLabel_.setText("");
		errorLabel_.setText("");
	}

	
	
	private void jButton2ActionPerformed(ActionEvent event) 
	{
		float assignment1MarkInput = new Float(assignment1MarkTextField_.getText()).floatValue();
		float assignment2MarkInput = new Float(assignment2MarkTextField_.getText()).floatValue();
		float examMarkInput = new Float(examMarkTextField_.getText()).floatValue();
		errorLabel_.setText("");
		try {
			checkGradesControl_.saveGrade(assignment1MarkInput, assignment2MarkInput, examMarkInput);
		}
		catch (RuntimeException re) {
			errorLabel_.setText(re.getMessage());
		}
	}
}
