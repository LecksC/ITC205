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

public class cgUI 
extends JFrame 
implements IUnitLister,	IStudentLister 
{
	private static final long serialVersionUID = 5712125091485034830L;
	
	private cgCTL checkGradesController_;
	
	private float assignment1Mark_;
	private float assignment2Mark_;
	private float examMark_;	
	private Integer studentId_;	
	
	private DefaultComboBoxModel<String> unitComboBoxModel_;
	private DefaultComboBoxModel<String> studentComboBoxModel_;
	private JButton buttonChange_;
	private JButton buttonCheckGrade_;
	private JButton buttonSave_;
	private JComboBox<String> comboBoxUnit_;
	private JComboBox<String> comboBoxStudent_;
	private JLabel labelTitle_;
	private JLabel labelAssignment1Mark_;
	private JLabel labelAssignment2Mark_;
	private JLabel labelExamMark_;
	private JLabel labelGrade_;
	private JLabel labelError_;
	private JPanel panelUnit_;
	private JPanel panelStudent_;
	private JPanel panelMarks_;
	private JPanel panelGrade_;
	private JTextField textFieldAssignment1Mark_;
	private JTextField textFieldAssignment2Mark_;
	private JTextField textFieldExamMark_;
	
	
	
	public cgUI(cgCTL ctl) 
	{
		checkGradesController_ = ctl;
		unitComboBoxModel_ = new DefaultComboBoxModel<String>(new String[0]);
		studentComboBoxModel_ = new DefaultComboBoxModel<String>(new String[0]);
		initializeComponents();
		comboBoxUnit_.setModel(unitComboBoxModel_);
		comboBoxStudent_.setModel(studentComboBoxModel_);
		labelError_.setText("");
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
        comboBoxUnit_.setEnabled(enabled);
        labelError_.setText("");
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
        comboBoxStudent_.setEnabled(enabled);
        labelError_.setText("");
    }

    
    
    public void setStudentUnitRecord(IStudentUnitRecord record) 
    {
        textFieldAssignment1Mark_.setText(new Float(record.getAssignment1Mark()).toString());
        textFieldAssignment2Mark_.setText(new Float(record.getAssignment2Mark()).toString());
        textFieldExamMark_.setText(new Float(record.getExamMark()).toString());
        labelGrade_.setText("");
    }

    
    
    public void clearStudentUnitRecords() 
    {
        textFieldAssignment1Mark_.setText("");
        textFieldAssignment2Mark_.setText("");
        textFieldExamMark_.setText("");
        labelGrade_.setText("");
        labelError_.setText("");
        textFieldAssignment1Mark_.setEditable(false);
        textFieldAssignment2Mark_.setEditable(false);
        textFieldExamMark_.setEditable(false);
    }

    
    
    public void setCheckGradeButtonEnabled(boolean enabled) 
    {
        buttonCheckGrade_.setEnabled(enabled);
    }

    
    
    public void setChangeButtonEnabled(boolean enabled) 
    {
        buttonChange_.setEnabled(enabled);
    }

    
    
    public void setMarksEditable(boolean enabled) 
    {
        textFieldAssignment1Mark_.setEditable(enabled);
        textFieldAssignment2Mark_.setEditable(enabled);
        textFieldExamMark_.setEditable(enabled);
    }

    
    
    public void setSaveEnabled(boolean enabled) 
    {
        buttonSave_.setEnabled(enabled);
    }
    
    
	
	private void initializeComponents() 
	{
		labelTitle_ = new JLabel();
		panelUnit_ = new JPanel();
		comboBoxUnit_ = new JComboBox<String>();
		panelStudent_ = new JPanel();
		comboBoxStudent_ = new JComboBox<String>();
		panelMarks_ = new JPanel();
		labelAssignment1Mark_ = new JLabel();
		labelAssignment2Mark_ = new JLabel();
		labelExamMark_ = new JLabel();
		textFieldAssignment1Mark_ = new JTextField();
		textFieldAssignment2Mark_ = new JTextField();
		textFieldExamMark_ = new JTextField();
		buttonChange_ = new JButton();
		panelGrade_ = new JPanel();
		labelGrade_ = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelTitle_.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		labelTitle_.setText("Check Grade UI");

		panelUnit_.setBorder(BorderFactory.createTitledBorder("Unit"));

		comboBoxUnit_.setModel(unitComboBoxModel_);
		
		comboBoxUnit_.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				selectedUnitChanged(event);
			}
		});
		
		GroupLayout groupUnit = new GroupLayout(panelUnit_);

		panelUnit_.setLayout(groupUnit);
		
		groupUnit.setHorizontalGroup(groupUnit.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
						groupUnit
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(comboBoxUnit_,
								GroupLayout.PREFERRED_SIZE, 185,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupUnit.setVerticalGroup(groupUnit.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
						groupUnit
						.createSequentialGroup()
						.addComponent(comboBoxUnit_,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		panelStudent_.setBorder(BorderFactory.createTitledBorder("Student"));

		comboBoxStudent_.setModel(studentComboBoxModel_);
		comboBoxStudent_.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				selectedStudentChanged(event);
			}
		});

		GroupLayout groupStudent = new GroupLayout(panelStudent_);
		
		panelStudent_.setLayout(groupStudent);
		groupStudent.setHorizontalGroup(groupStudent.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupStudent
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(comboBoxStudent_,
								GroupLayout.PREFERRED_SIZE, 185,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupStudent.setVerticalGroup(groupStudent.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupStudent
						.createSequentialGroup()
						.addComponent(comboBoxStudent_,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		panelMarks_.setBorder(BorderFactory.createTitledBorder("Marks"));

		labelAssignment1Mark_.setText("Asg1:");

		labelAssignment2Mark_.setText("Asg2:");

		labelExamMark_.setText("Exam:");

		textFieldAssignment1Mark_.setEditable(false);
		textFieldAssignment1Mark_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		textFieldAssignment2Mark_.setEditable(false);
		textFieldAssignment2Mark_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		textFieldExamMark_.setEditable(false);
		textFieldExamMark_.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent event) {
				jTextFieldKeyTyped(event);
			}
		});

		buttonChange_.setText("Change");
		buttonChange_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changePressed(event);
			}
		});
		
		buttonCheckGrade_ = new JButton();
		buttonCheckGrade_.setText("Check Grade");
		buttonCheckGrade_.setActionCommand("checkGrade");
		buttonCheckGrade_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				checkGradePressed(event);
			}
		});

		GroupLayout groupMarks = new GroupLayout(panelMarks_);
		groupMarks.setHorizontalGroup(
			groupMarks.createParallelGroup(Alignment.LEADING)
				.addGroup(groupMarks.createSequentialGroup()
					.addGroup(groupMarks.createParallelGroup(Alignment.LEADING)
						.addGroup(groupMarks.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelAssignment1Mark_)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldAssignment1Mark_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelAssignment2Mark_))
						.addGroup(groupMarks.createSequentialGroup()
							.addGap(85)
							.addComponent(buttonChange_, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupMarks.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupMarks.createSequentialGroup()
							.addComponent(textFieldAssignment2Mark_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelExamMark_))
						.addComponent(buttonCheckGrade_))
					.addGap(18)
					.addComponent(textFieldExamMark_, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		
		groupMarks.setVerticalGroup(
			groupMarks.createParallelGroup(Alignment.LEADING)
				.addGroup(groupMarks.createSequentialGroup()
					.addGroup(groupMarks.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelAssignment1Mark_)
						.addComponent(textFieldAssignment1Mark_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAssignment2Mark_)
						.addComponent(textFieldAssignment2Mark_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelExamMark_)
						.addComponent(textFieldExamMark_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupMarks.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonChange_)
						.addComponent(buttonCheckGrade_))
					.addContainerGap())
		);
		panelMarks_.setLayout(groupMarks);

		panelGrade_.setBorder(BorderFactory.createTitledBorder("Grade"));

		labelGrade_.setFont(new Font("Tahoma", 0, 24)); // NOI18N
		labelGrade_.setForeground(new Color(255, 0, 0));
		labelGrade_.setHorizontalAlignment(SwingConstants.CENTER);
		labelGrade_.setText("grade");

		GroupLayout groupGrade = new GroupLayout(panelGrade_);
		panelGrade_.setLayout(groupGrade);
		groupGrade.setHorizontalGroup(groupGrade.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				labelGrade_, GroupLayout.Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE));
		groupGrade.setVerticalGroup(groupGrade.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupGrade.createSequentialGroup().addGap(34, 34, 34)
						.addComponent(labelGrade_)
						.addContainerGap(43, Short.MAX_VALUE)));
		
		labelError_ = new JLabel();
		labelError_.setText("Error message");
		labelError_.setForeground(Color.RED);
		labelError_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonSave_ = new JButton();
		buttonSave_.setText("Save");
		buttonSave_.addActionListener(new ActionListener() {
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
							.addComponent(labelError_, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panelMarks_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(panelUnit_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelStudent_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(panelGrade_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup()
							.addGap(157)
							.addComponent(labelTitle_))
						.addGroup(layout.createSequentialGroup()
							.addGap(165)
							.addComponent(buttonSave_, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTitle_)
					.addGap(13)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(panelUnit_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelStudent_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelGrade_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelMarks_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSave_)
					.addGap(11)
					.addComponent(labelError_, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(layout);

		pack();
	}

	
	
	private void selectedUnitChanged(ItemEvent event) {
		String selectedUnit = (String) comboBoxUnit_.getSelectedItem();
		clearStudentUnitRecords();
		clearStudents();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (selectedUnit.equals((String) comboBoxUnit_.getItemAt(0))) {
				selectedUnit = "NONE";
			}
			checkGradesController_.unitSelected(selectedUnit);
		}
	}
	
	

	private void selectedStudentChanged(ItemEvent event) 
	{
		clearStudentUnitRecords();
		String selectedStudent = (String) comboBoxStudent_.getSelectedItem();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if (selectedStudent.equals((String) comboBoxStudent_.getItemAt(0))) {
				studentId_ = 0;
				checkGradesController_.studentSelected(studentId_);
			} 
			else {
				studentId_ = new Integer(selectedStudent.split("\\s")[0]);
			}
			checkGradesController_.studentSelected(studentId_);
		}
	}

	
	
	private void checkGradePressed(ActionEvent event) 
	{
		assignment1Mark_ = new Float(textFieldAssignment1Mark_.getText()).floatValue();
		assignment2Mark_ = new Float(textFieldAssignment2Mark_.getText()).floatValue();
		examMark_ = new Float(textFieldExamMark_.getText()).floatValue();
		try {
			String gradeText = checkGradesController_.checkGrade(assignment1Mark_, assignment2Mark_, examMark_);
			labelGrade_.setText(gradeText);
		}
		catch (RuntimeException exception) {
			labelError_.setText(exception.getMessage());
		}
	}

	
	
	private void changePressed(ActionEvent event) 
	{
		checkGradesController_.enableChangeMarks();
		labelGrade_.setText("");
	}

	
	
	private void jTextFieldKeyTyped(KeyEvent event) 
	{
		labelGrade_.setText("");
		labelError_.setText("");
	}

	
	
	private void jButton2ActionPerformed(ActionEvent event) 
	{
		float assignment1MarkInput = new Float(textFieldAssignment1Mark_.getText()).floatValue();
		float assignment2MarkInput = new Float(textFieldAssignment2Mark_.getText()).floatValue();
		float examMarkInput = new Float(textFieldExamMark_.getText()).floatValue();
		labelError_.setText("");
		try {
			checkGradesController_.saveGrade(assignment1MarkInput, assignment2MarkInput, examMarkInput);
		}
		catch (RuntimeException re) {
			labelError_.setText(re.getMessage());
		}
	}
}
