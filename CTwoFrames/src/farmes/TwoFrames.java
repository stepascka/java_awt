package farmes;
import java.awt.*; 
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*; 

class TwoFrames extends Frame { 
	
	int ch = 0; //���-�� �����
	int maxNumberOfObjects = 10;
	int numbers[];	//id	
	
	List<Figure> figures = new ArrayList<Figure>(); //������ �����
	
	Frame childFrame ; // �������� �����
	Canvas cnv;
	Label numberLab, objectLab, speedLab, objectLabForChange, speedLabForChange, colorLab;
	TextField numberTB, speedTB, colorTB; 
	Button addBut, changeBut;  
	Choice objectChForAdd, objectChForChange, speedCh;
	Color color; 
	
	
	public TwoFrames() {  
		
		this.setTitle("������������ ������ 6 (�����)");  
		this.setSize (270, 320); 
				
		numbers = new int[maxNumberOfObjects];
		for (int i = 0; i < maxNumberOfObjects; i ++) 
			numbers[i] = -1;					
		
		numberLab = new Label("�����: ");
		objectLab = new Label("������: ");
		speedLab = new Label("��������: ");
		objectLabForChange = new Label("������: ");
		speedLabForChange = new Label("��������: ");
		colorLab = new Label("����: ");
		
		numberTB = new TextField();
		numberTB.setText("1");
		speedTB = new TextField();
		speedTB.setText("3");
		colorTB = new TextField();
		colorTB.setText("�������");
		
		addBut = new Button("��������");
		changeBut = new Button("��������");
		
		objectChForAdd = new Choice();  
		objectChForAdd.addItem("����");  
		objectChForAdd.addItem("�������");  
		
		objectChForChange = new Choice();  
		
		speedCh = new Choice(); 
		speedCh.addItem("1");  
		speedCh.addItem("2");   
		speedCh.addItem("3"); 
		speedCh.addItem("4");  
		speedCh.addItem("5");  		
		
		cnv = new Canvas() {
			public void paint(Graphics g) {
				//
		    }
		};
		cnv.setBackground(Color.pink);
		
		//��������� ��				
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout (new GridBagLayout());  
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
		this.add (numberLab, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 0;
        this.add (numberTB, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1;
        this.add (objectLab, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 1;
        this.add (objectChForAdd, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 2;
        this.add (colorLab, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 2;
        this.add (colorTB, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 3;
        this.add (speedLab, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 3;
        this.add (speedTB, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;  gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add (addBut, gbc);
				
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        this.add (new Label(""), gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 6;
        this.add (objectLabForChange, gbc);
				
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 6;
        this.add (objectChForChange, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 7;
        this.add (speedLabForChange, gbc);
				
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 7;
        this.add (speedCh, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        this.add (changeBut, gbc);
        
        addBut.addActionListener(new ActionListener() {   
			public void actionPerformed (ActionEvent aE) {    
				AddButFunc();   
			}  
		});  
		
		changeBut.addActionListener(new ActionListener() {   
			public void actionPerformed (ActionEvent aE) {    
				ChangeButFunc();   
			}  
		});  

		this.addWindowListener (new OurWindowAdapter());
		
		//��
		childFrame= new Frame(); // ������� �������� ����� �  
		childFrame.setSize (600, 600); // � ��������  
		childFrame.setLocation (350, 0);  		
		childFrame.add(cnv);		
		childFrame.show(); // ������������ ������� ������� ���� 
		childFrame.addWindowListener (new OurWindowAdapter());
	} 
		
	public void AddButFunc() {			
		
		if (IsInteger(numberTB.getText())) {	//�������� ������
			int num = Integer.parseInt(numberTB.getText());
			if (CheckNumbers(num)) {
				if (IsInteger(speedTB.getText())) { //�������� ��������
					int speed = Integer.parseInt(speedTB.getText());
					if (CheckSpeed(speed)) {
						if (CheckColor(colorTB.getText())) { //�������� �����
														
							numbers[ch] = num;
							ch ++;
							
							objectChForChange.addItem(num + "");
							
							if (objectChForAdd.getSelectedItem() == "����") {
								figures.add(new Circle(this.cnv, color, speed, num));
								figures.get(ch-1).start();
							}
							else {
								figures.add(new Quadrangle(this.cnv, color, speed, num));
								figures.get(ch-1).start();	
							}
						} 
					} 
				}
			}
		}
	}
	
	public void ChangeButFunc() {
		
		if (IsInteger(objectChForChange.getSelectedItem())) { //�������� ������ �������
			int num = Integer.parseInt(objectChForChange.getSelectedItem());
			
			int foundNumber = -1;
			for (int i = 0; i < ch; i ++) { //����� �������
				if (numbers[i] == num) 
					foundNumber = i;
			}
			
			figures.get(foundNumber).speed = Integer.parseInt(speedCh.getSelectedItem()); // ��������� ��������
		}
	}
	
	public boolean IsInteger(String string) {
		try {
			Integer.parseInt(string);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean CheckNumbers(int num) {		
		for (int i = 0; i < ch; i ++) {
			if (numbers[i] == num) 
				return false;
		}
		return true;
	}
	
	public boolean CheckSpeed(int speed) {		
		if ((speed == 1) || (speed == 2) || (speed == 3) || (speed == 4) || (speed == 5))
			return true;
		else 
			return false;
	}
	
	public boolean CheckColor(String clr) {				
		if (clr.equalsIgnoreCase("�������")) {			
			this.color = Color.red;			
		}
		else if (clr.equalsIgnoreCase("�������")) {
			this.color = Color.green;
		}
		else if (clr.equalsIgnoreCase("�����")) {
			this.color = Color.blue;
		}
		else if (clr.equalsIgnoreCase("������")) {
			this.color = Color.yellow;
		}
		else if (clr.equalsIgnoreCase("�����")) {
			this.color = Color.white;
		}
		else if (clr.equalsIgnoreCase("������")) {
			this.color = Color.black;
		}
		else 
			return false;
		
		return true;
	}

	public static void main (String[] args) {  
		TwoFrames TF = new TwoFrames();  
		TF.show(); 
	}
}