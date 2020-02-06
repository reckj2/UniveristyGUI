package org.university.software;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.university.people.Student;

import javax.swing.SwingUtilities;


//import org.hrs.system.HRGUI.MenuListener;

public class UniversityGUI extends JFrame{
	
	private JMenuBar menuBar;		//the horizontal container
	private JMenu adminMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	private JMenu fileMenu;
	private JMenu studentMenu;
	
	private JMenuItem printAll; 		
	private JMenuItem Save;
	private JMenuItem Load;
	private JMenuItem Exit;
	private JMenuItem DropClass;
	private JMenuItem AddClass;
	private JMenuItem PrintSchedule;
	private University univ2;
	
	Font newFont = new Font("", Font.PLAIN, 28);
	static Font pixelMplus;
	
	public UniversityGUI(String title, University univ) throws FontFormatException, IOException {
		super(title);
		univ2 = univ;
		setSize(300, 200);
		
		pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("RANDOM_DM.ttf")).deriveFont(10f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("RANDOM_DM.ttf")));
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel textLabel = new JLabel("<HTML><center>Welcome to the University" +
				"<BR>Choose an action from the above menus.</center></HTML>");
		add(textLabel);
		//textLabel.setFont(pixelMplus);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
	}
	
	public void buildGUI() 
	{
		menuBar = new JMenuBar();
		
		adminMenu = new JMenu("Administrators");
		studentMenu = new JMenu("Student");
		fileMenu = new JMenu("File");
		
		printAll = new JMenuItem("Print All Info");
		Save = new JMenuItem ("Save");
		Load = new JMenuItem ("Load");
		Exit = new JMenuItem ("Exit");
		DropClass = new JMenuItem ("Drop Class");
		AddClass = new JMenuItem ("Add Class");
		PrintSchedule = new JMenuItem ("Print Schedule");
		
		printAll.addActionListener(new MenuListener());
		Save.addActionListener(new MenuListener());
	    Load.addActionListener(new MenuListener());
	    Exit.addActionListener(new ExitListener());
	    DropClass.addActionListener(new MenuListener());
		AddClass.addActionListener(new MenuListener());
		PrintSchedule.addActionListener(new MenuListener());
		
	    
		adminMenu.add(printAll);
		
		fileMenu.add(Save);
		fileMenu.add(Load);
		fileMenu.add(Exit);
		
		studentMenu.add(DropClass);
		studentMenu.add(AddClass);
		studentMenu.add(PrintSchedule);
		
		menuBar.add(fileMenu);
	    menuBar.add(adminMenu);
	    menuBar.add(studentMenu);
	
		setJMenuBar(menuBar);
		
		
	}
	
	private class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JMenuItem source = (JMenuItem)(e.getSource());
				if(source.equals(printAll)){
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(baos);
					// IMPORTANT: Save the old System.out!
					PrintStream old = System.out;
					// Tell Java to use your special stream
					System.setOut(ps);
					// Print some output: goes to your special stream
					univ2.printAll();
					// Put things back
					System.out.flush();
					System.setOut(old);
					
					
					
					JPanel scrollWindow = new JPanel ();
					scrollWindow.setBorder ( new TitledBorder ( new EtchedBorder (), "Printed Information" ) );				  

				    JTextArea display = new JTextArea ( 16, 58 );
				    display.setEditable ( false ); 
				    display.setText(baos.toString());
				    JScrollPane scroll = new JScrollPane ( display );
				    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

				    scrollWindow.add ( scroll );
				    
				    
				    // My code
				    JFrame frame = new JFrame ();
				    frame.add ( scrollWindow );
				    frame.pack ();
				    frame.setLocationRelativeTo ( null );
				    frame.setVisible ( true );	
				}
				
				if(source.equals(AddClass)){
					HandleAddClass();
				}
				
				if(source.equals(DropClass)) {
					HandleDropClass();
				}
				if(source.equals(PrintSchedule)) {
					HandlePrintSchedule();
				}
				if(source.equals(Save)) {
					HandleSave();
				}
				if(source.equals(Load)) {
					HandleLoad();
				}
				
			
			
		}
	}
	
	private void HandleSave() {
		University.saveData(univ2);
	}
	private void HandleLoad() {
		univ2 = University.loadData();
	}
	private void HandlePrintSchedule() {
		JFrame frame;
		JTextField Jname;
		String name;
		String tempName;
		Student std = new Student();
		
		boolean nameChecker = false;
		//boolean departmentChecker = false;
		//boolean courseChecker = false;
		
		frame = new JFrame("Print Schedule");
		JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(0, 2, 2, 2));

        Jname = new JTextField(5);
        pane.add(new JLabel("Student Name:"));
        pane.add(Jname);
        
        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields",
        		JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if(option == JOptionPane.OK_OPTION) { 
	        name = Jname.getText();
	        for(int i = 0; i < univ2.departmentList.size(); i++) {
		    	  for(int j = 0; j < univ2.departmentList.get(i).getStudentList().size(); j++) {
		    		 tempName = univ2.departmentList.get(i).getStudentList().get(j).getName();
		    		  if(tempName.equals(name)) {
		    			  std = univ2.departmentList.get(i).getStudentList().get(j);
		    			  nameChecker = true;
		    		  }
		    	  }	
		      }    
        
        if(!nameChecker) {
        	JOptionPane.showMessageDialog(null, "Student \""
					+ name
					+ "\" doesn't exist.", "Error ",
					JOptionPane.PLAIN_MESSAGE);
        	return;
        }
	        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		std.printSchedule();
		// Put things back
		System.out.flush();
		System.setOut(old);
		
		
		
		JPanel scrollWindow = new JPanel ();
		scrollWindow.setBorder ( new TitledBorder ( new EtchedBorder (), "Student Schedule" ) );				  

	    JTextArea display = new JTextArea ( 16, 58 );
	    display.setEditable ( false ); 
	    display.setText(baos.toString());
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

	    scrollWindow.add ( scroll );
	    
	   
	    
	    
	    // My code
	    JFrame frame2 = new JFrame ();
	    frame2.add ( scrollWindow );
	    
	    frame2.pack ();
	    frame2.setLocationRelativeTo ( null );
	    frame2.setVisible ( true );
	    
        }
        
		
	}

	private void HandleDropClass() {
		JTextField Jname;
		JTextField Jdepartment;
		JTextField JclassNum;
		JFrame frame;
		
		Student std = new Student();
		CampusCourse cC = new CampusCourse();
		CampusCourse cC2 = new CampusCourse();
		OnlineCourse oC = new OnlineCourse();
		
		String name;
		String department;
		String strclassNum;
		
		boolean nameChecker = false;
		boolean departmentChecker = false;
		boolean courseChecker = false;
		String tempDep = "Unknown";
		
		String tempName = "Stupid";
		int classNum;
		
		boolean onRoster = false;
		
		int breakk = 0;
		
		int badSched = 0;
		
		
		frame = new JFrame("Drop Course");
			JPanel pane = new JPanel();
	        pane.setLayout(new GridLayout(0, 2, 2, 2));

	        Jname = new JTextField(5);
	        Jdepartment = new JTextField(5);
	        JclassNum = new JTextField(5);

	        pane.add(new JLabel("Student Name:"));
	        pane.add(Jname);

	        pane.add(new JLabel("Department:"));
	        pane.add(Jdepartment);
	        
	        pane.add(new JLabel("Course Number:"));
	        pane.add(JclassNum);
	        
	        int option = JOptionPane.showConfirmDialog(frame, pane, "Drop Course - Please fill all the fields",
	        		JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	      
	        if(option == JOptionPane.OK_OPTION) { 
		        name = Jname.getText();
		        department = Jdepartment.getText();
		        strclassNum = JclassNum.getText();
		        classNum = Integer.parseInt(strclassNum);
		        
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getStudentList().size(); j++) {
			    		 tempName = univ2.departmentList.get(i).getStudentList().get(j).getName();
			    		  if(tempName.equals(name)) {
			    			  std = univ2.departmentList.get(i).getStudentList().get(j);
			    			  nameChecker = true;
		//	    			  breakk = 1;
		//	    			  break;
			    		  }
			    	  }
		//	    	  if(breakk == 1) {
		//	    		  break;
		//	    	  }
			      }
			      breakk = 0;
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getCampusCourseList().size(); j++) {
			    		  if(univ2.departmentList.get(i).getCampusCourseList().get(j).getCourseNumber() == classNum) {
			    			  cC = univ2.departmentList.get(i).getCampusCourseList().get(j);
			    			  breakk = 1;
			    			  courseChecker = true;
			    			  break;
			    		  }
			    	  }
			    	  if(breakk == 1) {
			    		  break;
			    	  }
			      }
			      breakk = 0;
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getOnlineCourseList().size(); j++) {
			    		  System.out.println(univ2.departmentList.get(i).getOnlineCourseList().get(j).getCourseNumber());
			    		  if(univ2.departmentList.get(i).getOnlineCourseList().get(j).getCourseNumber() == classNum) {
			    			  oC = univ2.departmentList.get(i).getOnlineCourseList().get(j);
			    			  breakk = 1;
			    			  courseChecker = true;
			    			  break;
			    		  }
			    	  }
			    	  if(breakk == 1) {
			    		  break;
			    	  }
			      }
			      
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  tempDep = univ2.departmentList.get(i).getDepartmentName();
			    	  if(tempDep.equals(department)) {
			    		  departmentChecker = true;
			    	  }
			      }
			      
			       if(!nameChecker) {
			        	JOptionPane.showMessageDialog(null, "Student \""
								+ name
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			       if(!departmentChecker) {
			        	JOptionPane.showMessageDialog(null, "Department \""
								+ department
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			       if(!courseChecker) {
			        	JOptionPane.showMessageDialog(null, "Course \""
								+ classNum
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			      
		        for(int i = 0; i<std.getCampusCourselist().size(); i++) {
		        	onRoster = (cC == std.getCampusCourselist().get(i));
		        	if(onRoster) {
		        		break;
		        	}
		        }
		        if(!onRoster) {
		        	for(int i = 0; i<std.getOnlinecourselist().size(); i++) {
			        	onRoster = (oC == std.getOnlinecourselist().get(i));
			        	if(onRoster) {
			        		break;
			        	}
			        }
		        }
		        if((std.getOnlinecourselist().size() > 0 ) && ((std.getThissemesterunits() - cC.getCreditUnits()) < 6)) {
		        	JOptionPane.showMessageDialog(null, std.getName()+" can't drop this CampusCourse,"
							+ " because this student doesn't have enough "
							+ "campus course credit to hold the online course", "Error ",
							JOptionPane.PLAIN_MESSAGE);
			        
		        }
		        else if (!onRoster){
		        	JOptionPane.showMessageDialog(null, std.getName() + " cannot drop the course "
		        			+ "because they are not enrolled in the course", "Error ",
							JOptionPane.PLAIN_MESSAGE);
		        }
		        else if(cC.getName() != "Unknown"){
		        	JOptionPane.showMessageDialog(null, "Success! You have dropped Campus course "+
		        			cC.getName(), "Success",
							JOptionPane.PLAIN_MESSAGE);
		        	std.dropCourse(cC);
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "Success! You have dropped Online course "+
		        			oC.getName(), "Success",
							JOptionPane.PLAIN_MESSAGE);
		        	std.dropCourse(oC);
		        }
		        
		        
		        
	        
	        }
	}
	private void HandleAddClass() {
		JTextField Jname;
		JTextField Jdepartment;
		JTextField JclassNum;
		JFrame frame;
		
		Student std = new Student();
		CampusCourse cC = new CampusCourse();
		CampusCourse cC2 = new CampusCourse();
		OnlineCourse oC = new OnlineCourse();
		
		String name;
		String department;
		String strclassNum;
		
		boolean nameChecker = false;
		boolean departmentChecker = false;
		boolean courseChecker = false;
		String tempDep = "Unknown";
		
		String tempName = "Stupid";
		int classNum;
		
		int breakk = 0;
		
		int badSched = 0;
		
		
		frame = new JFrame("Add Course");
			JPanel pane = new JPanel();
	        pane.setLayout(new GridLayout(0, 2, 2, 2));

	        Jname = new JTextField(5);
	        Jdepartment = new JTextField(5);
	        JclassNum = new JTextField(5);

	        pane.add(new JLabel("Student Name:"));
	        pane.add(Jname);

	        pane.add(new JLabel("Department:"));
	        pane.add(Jdepartment);
	        
	        pane.add(new JLabel("Course Number:"));
	        pane.add(JclassNum);
	        
	        int option = JOptionPane.showConfirmDialog(frame, pane, "Add Course - Please fill all the fields",
	        		JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	        
	        if(option == JOptionPane.OK_OPTION) {
	        
			        name = Jname.getText();
			        department = Jdepartment.getText();
			        strclassNum = JclassNum.getText();
			        classNum = Integer.parseInt(strclassNum);
			        
			        
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getStudentList().size(); j++) {
			    		 tempName = univ2.departmentList.get(i).getStudentList().get(j).getName();
			    		  if(tempName.equals(name)) {
			    			  std = univ2.departmentList.get(i).getStudentList().get(j);
			    			  nameChecker = true;
		//	    			  breakk = 1;
		//	    			  break;
			    		  }
			    	  }
		//	    	  if(breakk == 1) {
		//	    		  break;
		//	    	  }
			      }
			      breakk = 0;
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getCampusCourseList().size(); j++) {
			    		  if(univ2.departmentList.get(i).getCampusCourseList().get(j).getCourseNumber() == classNum) {
			    			  cC = univ2.departmentList.get(i).getCampusCourseList().get(j);
			    			  breakk = 1;
			    			  courseChecker = true;
			    			  break;
			    		  }
			    	  }
			    	  if(breakk == 1) {
			    		  break;
			    	  }
			      }
			      breakk = 0;
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  for(int j = 0; j < univ2.departmentList.get(i).getOnlineCourseList().size(); j++) {
			    		  if(univ2.departmentList.get(i).getOnlineCourseList().get(j).getCourseNumber() == classNum) {
			    			  oC = univ2.departmentList.get(i).getOnlineCourseList().get(j);
			    			  breakk = 1;
			    			  courseChecker = true;
			    			  break;
			    		  }
			    	  }
			    	  if(breakk == 1) {
			    		  break;
			    	  }
			      }
			      
			      for(int i = 0; i < univ2.departmentList.size(); i++) {
			    	  tempDep = univ2.departmentList.get(i).getDepartmentName();
			    	  if(tempDep.equals(department)) {
			    		  departmentChecker = true;
			    	  }
			      }
			      
			       if(!nameChecker) {
			        	JOptionPane.showMessageDialog(null, "Student \""
								+ name
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			       if(!departmentChecker) {
			        	JOptionPane.showMessageDialog(null, "Department \""
								+ department
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			       if(!courseChecker) {
			        	JOptionPane.showMessageDialog(null, "Course \""
								+ classNum
								+ "\" doesn't exist.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			        	return;
			        }  
			      
			      
			      //JOptionPane.showMessageDialog(null, std.getName(), "Error", JOptionPane.PLAIN_MESSAGE);
			    
			    if((cC.getName() != "Unknown"))  {
				    if(std.detectConflict(cC)) { 
				    badSched = std.returnBadSched(cC);
				    cC2 = std.returnBadCC(cC);
				    
				    String[] Week = {"Mon", "Tue", "Wed", "Thu", "Fri"};
					String[] Slot = {	"8:00am to 9:15am",
										"9:30am to 10:45am",
										"11:00am to 12:15pm",
										"12:30pm to 1:45pm",
										"2:00pm to 3:15pm",
										"3:30pm to 4:45pm"};
				    
				    int	schedTemp = badSched;
					int temp2 = schedTemp%100;
					schedTemp = schedTemp - temp2;
					schedTemp = schedTemp/100;
					schedTemp = schedTemp -1;
					temp2 = temp2 -1;
					JOptionPane.showMessageDialog(null,
							department+classNum+
							" course cannot be added to " + std.getName()+ "'s Schedule. " +
							department+classNum +
							" conflicts with " + cC2.getDepartment().getDepartmentName()+ cC2.getCourseNumber() + 
							". Conflicting time slot is " + Week[schedTemp]+ " " + Slot[temp2] + ".", "Error ",
							JOptionPane.PLAIN_MESSAGE);
				    }
				    else if(!cC.availableTo(std)) {
				    	JOptionPane.showMessageDialog(null, name + " can't add Campus Course " 
				    			+ cC.getDepartment().getDepartmentName() + cC.getCourseNumber() + " " + 
				    			cC.getName()+ " because this Campus Course has enough students.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
				    }
				    else {
				    	JOptionPane.showMessageDialog(null, "Success you have added " + cC.getName(), "Success ",
								JOptionPane.PLAIN_MESSAGE);
				    	std.addCourse(cC);
				    }
			    }
			    else {
			    	if(!oC.availableTo(std)) {
			    		JOptionPane.showMessageDialog(null, "Student " + name + " has only " +std.getThissemesterunits()
			    		+ " campus credits enrolled. Should have at least 6 credits registered before registering online "
			    		+ "courses. Cannot enroll in "
				    			+ oC.getDepartment().getDepartmentName() + oC.getCourseNumber() + " " + 
				    			oC.getName()+ " because this Campus Course has enough students.", "Error ",
								JOptionPane.PLAIN_MESSAGE);
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Success you have added " + oC.getName(), "Success ",
								JOptionPane.PLAIN_MESSAGE);
				    	std.addCourse(oC);
			    	}
			    }
	        }
	        
	}
	private class ExitListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);		
		}
		
	}
	

}
